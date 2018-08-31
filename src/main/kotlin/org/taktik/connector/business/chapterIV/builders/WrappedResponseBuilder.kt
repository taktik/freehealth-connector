package org.taktik.connector.business.chapterIV.builders

import be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementResponseWrapper
import org.taktik.connector.business.chapterIV.wrapper.UnsealedResponseWrapper
import org.taktik.connector.business.chapterIV.wrapper.impl.AskChap4MedicalAdvisorAgreementResponseWrapperImpl
import org.taktik.connector.business.chapterIV.wrapper.impl.AskUnsealedResponseWrapperImpl
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultChap4MedicalAdvisorAgreementResponseWrapperImpl
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultUnsealedResponseWrapperImpl
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse

object WrappedResponseBuilder {
    fun wrap(agreementResponse: ConsultChap4MedicalAdvisorAgreementResponse): Chap4MedicalAdvisorAgreementResponseWrapper<ConsultChap4MedicalAdvisorAgreementResponse> =
        ConsultChap4MedicalAdvisorAgreementResponseWrapperImpl(agreementResponse)

    fun wrap(response: AskChap4MedicalAdvisorAgreementResponse): Chap4MedicalAdvisorAgreementResponseWrapper<AskChap4MedicalAdvisorAgreementResponse> =
        AskChap4MedicalAdvisorAgreementResponseWrapperImpl(response)

    fun wrap(response: Response): UnsealedResponseWrapper<Response> = ConsultUnsealedResponseWrapperImpl(response)

    fun wrap(response: be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response): UnsealedResponseWrapper<be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response> =
        AskUnsealedResponseWrapperImpl(response)
}
