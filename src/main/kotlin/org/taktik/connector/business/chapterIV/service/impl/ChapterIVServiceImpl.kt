package org.taktik.connector.business.chapterIV.service.impl

import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues
import org.taktik.connector.business.chapterIV.service.ChapterIVService
import org.taktik.connector.business.chapterIV.service.ServiceFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.validator.EhealthReplyValidator
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse
import java.net.MalformedURLException
import javax.xml.soap.SOAPException

class ChapterIVServiceImpl(private val replyValidator: EhealthReplyValidator) : ChapterIVService {

    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    override fun consultChap4MedicalAdvisorAgreement(token: SAMLToken,
                                                     request: ConsultChap4MedicalAdvisorAgreementRequest): ConsultChap4MedicalAdvisorAgreementResponse {
        try {
            val service = ServiceFactory.getConsultationService(token)
            service.setPayload(request)
            service.setSoapAction("urn:be:fgov:ehealth:chap4:protocol:v1:ConsultChap4MedicalAdvisorAgreement")
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)

            val response = xmlResponse.asObject(ConsultChap4MedicalAdvisorAgreementResponse::class.java)

            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            this.replyValidator.validateReplyStatus(response)
            return response
        } catch (malformedUrlException: MalformedURLException) {
            val fileTypeDescription = "Chapter IV Consultation file"
            throw ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.MALFORMED_URL, malformedUrlException, fileTypeDescription)
        } catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, soapException.message)
        }

    }

    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    override fun askChap4MedicalAdvisorAgreement(token: SAMLToken,
                                                 request: AskChap4MedicalAdvisorAgreementRequest): AskChap4MedicalAdvisorAgreementResponse {
        try {
            val service = ServiceFactory.getAdmissionService(token)
            service.setPayload(request)
            service.setSoapAction("urn:be:fgov:ehealth:chap4:protocol:v1:AskChap4MedicalAdvisorAgreement")
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val response = xmlResponse.asObject(AskChap4MedicalAdvisorAgreementResponse::class.java)

            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            this.replyValidator.validateReplyStatus(response)
            return response
        } catch (malformedUrlException: MalformedURLException) {
            val fileTypeDescription = "Chapter IV Admission file"
            throw ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.MALFORMED_URL, malformedUrlException, fileTypeDescription)
        } catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, soapException.message)
        }

    }
}
