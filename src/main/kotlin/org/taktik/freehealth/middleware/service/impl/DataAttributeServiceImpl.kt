package org.taktik.freehealth.middleware.service.impl

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import oasis.names.tc.saml._2_0.assertion.Attribute
import oasis.names.tc.saml._2_0.assertion.NameIDType
import oasis.names.tc.saml._2_0.assertion.ObjectFactory
import oasis.names.tc.saml._2_0.assertion.Subject
import oasis.names.tc.saml._2_0.assertion.SubjectConfirmation
import oasis.names.tc.saml._2_0.assertion.SubjectConfirmationDataType
import oasis.names.tc.saml._2_0.protocol.AttributeQuery
import oasis.names.tc.saml._2_0.protocol.Response
import org.springframework.stereotype.Service
import org.taktik.connector.business.daas.impl.AttributeServiceImpl
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.DataAttributeService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.utils.FuzzyValues
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
    ) : Response {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")

        val snf = DecimalFormat("00")
        val dob = "${snf.format(patientDateOfBirth%100)}/${snf.format((patientDateOfBirth/100)%100)}/${(patientDateOfBirth/10000)}"

        return try {
            val response = attributeService.getAttribute(samlToken, AttributeQuery().apply {
                this.version = "2.0"
                this.consent = "urn:oasis:names:tc:SAML:2.0:consent:current-implicit"
                this.id = "DAAS_${UUID.randomUUID()}"
                this.issueInstant = XMLGregorianCalendarImpl(GregorianCalendar())
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
            response
        } catch (e: TechnicalConnectorException) {
            throw IllegalArgumentException(e)
        }

    }
}
