package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoRequest
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import oasis.names.tc.saml._2_0.assertion.AttributeType
import oasis.names.tc.saml._2_0.assertion.NameIDType
import oasis.names.tc.saml._2_0.assertion.ObjectFactory
import oasis.names.tc.saml._2_0.assertion.SubjectConfirmationDataType
import oasis.names.tc.saml._2_0.assertion.SubjectConfirmationType
import oasis.names.tc.saml._2_0.assertion.SubjectType
import oasis.names.tc.saml._2_0.protocol.AttributeQueryType
import oasis.names.tc.saml._2_0.protocol.ResponseType
import org.springframework.stereotype.Service
import org.taktik.connector.business.daas.impl.AttributeServiceImpl
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.dto.common.ErrorDto
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.DataAttributeService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.utils.FuzzyValues
import java.lang.IllegalArgumentException
import java.util.*
import javax.xml.bind.JAXBElement
import javax.xml.datatype.XMLGregorianCalendar
import javax.xml.ws.soap.SOAPFaultException

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
    ) : ResponseType {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")

        val dob = "${patientDateOfBirth/10000}/${(patientDateOfBirth/100)%100}/${patientDateOfBirth%100}"

        return try {
            val response = attributeService.getAttribute(samlToken, AttributeQueryType().apply {
                this.consent = "urn:oasis:names:tc:SAML:2.0:consent:current-implicit"
                this.id = "DAAS_${UUID.randomUUID()}"
                this.issueInstant = XMLGregorianCalendarImpl(GregorianCalendar())
                this.issuer = NameIDType().apply {
                    format = "urn:oasis:names:tc:SAML:2.0:nameid-format:entity"
                    value = "urn:be:fgov:person:ssin:ehealth:1.0:doctor:nihii11:$nihii"
                }
                this.subject = SubjectType().apply {
                    val of = ObjectFactory()
                    content.add(of.createNameID(NameIDType().apply { format = "urn:oasis:names:tc:SAML:2.0:nameid-format:transient"; value=System.currentTimeMillis().toString() }))
                    content.add(of.createSubjectConfirmation(SubjectConfirmationType().apply {
                        method = "urn:oasis:names:tc:SAML:2.0:cm:sender-vouches"
                        subjectConfirmationData = SubjectConfirmationDataType().apply {
                            notBefore = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(from)
                            notOnOrAfter = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(to)
                        }
                    }))
                }
                this.attribute.add(AttributeType().apply { name = "urn:be:fgov:ehealth:1.0:service-name"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValue.add("urn:be:fgov:ehealth:admin:simplification:multemediatt") })
                this.attribute.add(AttributeType().apply { name = "urn:be:fgov:person:ssin:multemediatt:routing"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri" })
                this.attribute.add(AttributeType().apply { name = "urn:be:fgov:person:ssin"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValue.add(patientSsin) })
                this.attribute.add(AttributeType().apply { name = "urn:be:fgov:person:ssin:birthdate"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValue.add(dob) })
                this.attribute.add(AttributeType().apply { name = "urn:be:fgov:person:ssin:incapacity:prolongation"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValue.add(prolongation.toString()) })
                this.attribute.add(AttributeType().apply { name = "urn:be:fgov:person:ssin:incapacity:cause"; nameFormat = "urn:oasis:names:tc:SAML:2.0:attrname-format:uri"; attributeValue.add(cause) })
            })
            response
        } catch (e: TechnicalConnectorException) {
            throw IllegalArgumentException(e)
        }

    }
}
