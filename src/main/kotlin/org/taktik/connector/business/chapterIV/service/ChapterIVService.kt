package org.taktik.connector.business.chapterIV.service

import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException
import org.taktik.connector.technical.exception.SessionManagementException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse

interface ChapterIVService {
    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class, SessionManagementException::class)
    fun consultChap4MedicalAdvisorAgreement(token: SAMLToken,
                                            request: ConsultChap4MedicalAdvisorAgreementRequest): ConsultChap4MedicalAdvisorAgreementResponse

    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class, SessionManagementException::class)
    fun askChap4MedicalAdvisorAgreement(token: SAMLToken,
                                        request: AskChap4MedicalAdvisorAgreementRequest): AskChap4MedicalAdvisorAgreementResponse
}
