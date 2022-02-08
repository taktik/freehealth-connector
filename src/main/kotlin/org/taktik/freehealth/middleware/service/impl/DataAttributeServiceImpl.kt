package org.taktik.freehealth.middleware.service.impl

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import oasis.names.tc.saml._2_0.assertion.Assertion
import oasis.names.tc.saml._2_0.assertion.Attribute
import oasis.names.tc.saml._2_0.assertion.AttributeStatement
import oasis.names.tc.saml._2_0.assertion.NameIDType
import oasis.names.tc.saml._2_0.assertion.ObjectFactory
import oasis.names.tc.saml._2_0.assertion.Subject
import oasis.names.tc.saml._2_0.assertion.SubjectConfirmation
import oasis.names.tc.saml._2_0.assertion.SubjectConfirmationDataType
import oasis.names.tc.saml._2_0.protocol.AttributeQuery
import oasis.names.tc.saml._2_0.protocol.Response
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.daas.impl.AttributeServiceImpl
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.dto.daas.DaasResponse
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.DataAttributeService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.utils.FuzzyValues
import org.w3c.dom.Node
import java.text.DecimalFormat
import java.util.*

@Service
class DataAttributeServiceImpl(private val stsService: STSService) : DataAttributeService {
    private val attributeService = AttributeServiceImpl()
    override fun getDinRoutingInfo(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        nihii: String,
        patientSsin: String,
        patientDateOfBirth: Int,
        cause: String,
        from: Long,
        to: Long,
        total: Boolean,
        prolongation: Boolean
    ) : DaasResponse? {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")

        val snf = DecimalFormat("00")
        val dob = "${snf.format(patientDateOfBirth%100)}/${snf.format((patientDateOfBirth/100)%100)}/${(patientDateOfBirth/10000)}"

        return try {
            val response = attributeService.getAttribute(samlToken, AttributeQuery().apply {
                this.version = "2.0"
                this.consent = "urn:oasis:names:tc:SAML:2.0:consent:current-implicit"
                this.id = "DAAS_${UUID.randomUUID()}"
                this.issueInstant = DateTime.now()
                this.issuer = NameIDType().apply {
                    format = "urn:oasis:names:tc:SAML:2.0:nameid-format:entity"
                    value = "urn:be:fgov:person:ssin:ehealth:1.0:doctor:nihii11:$nihii"
                }
                this.subject = Subject().apply {
                    val of = ObjectFactory()
                    nameID = NameIDType().apply { format = "urn:oasis:names:tc:SAML:2.0:nameid-format:transient"; value=System.currentTimeMillis().toString() }
                    subjectConfirmations.add(SubjectConfirmation().apply {
                        method = "urn:oasis:names:tc:SAML:2.0:cm:sender-vouches"
                        subjectConfirmationData = SubjectConfirmationDataType().apply {
                            notBefore = FuzzyValues.getJodaDateTime(from)
                            notOnOrAfter = FuzzyValues.getJodaDateTime(to)
                        }
                    })
                }
                this.attributes.add(Attribute().apply { name = "urn:be:fgov:ehealth:1.0:service-name"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValues.add("urn:be:fgov:ehealth:admin:simplification:multemediatt") })
                this.attributes.add(Attribute().apply { name = "urn:be:fgov:person:ssin:multemediatt:routing"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri" })
                this.attributes.add(Attribute().apply { name = "urn:be:fgov:person:ssin"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValues.add(patientSsin) })
                this.attributes.add(Attribute().apply { name = "urn:be:fgov:person:ssin:birthdate"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValues.add(dob) })
                this.attributes.add(Attribute().apply { name = "urn:be:fgov:person:ssin:incapacity:prolongation"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValues.add(prolongation.toString()) })
                this.attributes.add(Attribute().apply { name = "urn:be:fgov:person:ssin:incapacity:totalincapacity"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValues.add(total.toString()) })
                this.attributes.add(Attribute().apply { name = "urn:be:fgov:person:ssin:incapacity:cause"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValues.add(cause) })
            })
            val attributes = response.assertionsAndEncryptedAssertions
                .flatMap { (it as? Assertion)?.statementsAndAuthnStatementsAndAuthzDecisionStatements ?: listOf() }
                .flatMap { (it as? AttributeStatement)?.attributesAndEncryptedAttributes ?: listOf() }
                .mapNotNull { (it as? Attribute) }
                .filter { it.name == "urn:be:fgov:person:ssin:multemediatt:routing" }
            attributes
                .firstOrNull()?.let { attribute ->
                    fun collectSiblings(acc: Collection<Node>, node: Node): Collection<Node> = (acc + node).let { nodes -> node.nextSibling?.let { collectSiblings(nodes, it)} ?: nodes }
                    val data = collectSiblings(listOf(), (attribute.attributeValues.first() as ElementNSImpl).firstChild.firstChild)
                    val destinations = data.firstOrNull { it.localName == "Destinations" }?.let { collectSiblings(listOf(), it.firstChild) }?.map { dest ->
                        (dest.firstChild?.let { collectSiblings(listOf(), it) }?.map {
                            (it.attributes.getNamedItemNS("http://www.w3.org/XML/1998/namespace", "lang")?.textContent?.let { lng -> "${it.nodeName}:$lng" } ?: it.nodeName) to it.textContent
                        } ?: listOf()) + listOfNotNull(
                            dest.attributes.getNamedItem("Channel")?.let { it.nodeName to it.textContent },
                            dest.attributes.getNamedItem("Dataset")?.let { it.nodeName to it.textContent }
                        )
                    } ?: listOf()
                    val context = data.firstOrNull { it.localName == "Context" }?.let { collectSiblings(listOf(), it.firstChild) }?.mapNotNull { it.attributes.getNamedItem("Name")?.textContent?.let {key -> key to it.firstChild?.textContent } } ?: listOf()
                    return DaasResponse(destinations.map { it.toMap() }, context.toMap())
                }
        } catch (e: TechnicalConnectorException) {
            throw IllegalArgumentException(e)
        }

    }
}
