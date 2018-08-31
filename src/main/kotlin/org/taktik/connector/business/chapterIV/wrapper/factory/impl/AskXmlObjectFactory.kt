package org.taktik.connector.business.chapterIV.wrapper.factory.impl

import be.cin.io.sealed.medicaladvisoragreement.ask.v1.Request
import org.taktik.connector.business.chapterIV.common.ConversationType
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory
import org.taktik.connector.business.chapterIV.wrapper.impl.AskChap4MedicalAdvisorAgreementRequestWrapperImpl
import org.taktik.connector.business.chapterIV.wrapper.impl.AskSealedRequestWrapperImpl
import org.taktik.connector.business.chapterIV.wrapper.impl.AskUnsealedRequestWrapperImpl
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest

class AskXmlObjectFactory : XmlObjectFactory {
    override fun createSealedRequest(): SealedRequestWrapper<Request> {
        return AskSealedRequestWrapperImpl()
    }

    override fun createUnsealedRequest(): UnsealedRequestWrapper<be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Request> {
        return AskUnsealedRequestWrapperImpl()
    }

    override fun createChap4MedicalAdvisorAgreementRequest(): Chap4MedicalAdvisorAgreementRequestWrapper<AskChap4MedicalAdvisorAgreementRequest> {
        return AskChap4MedicalAdvisorAgreementRequestWrapperImpl()
    }

    override fun getSubtypeNameToRetrieveCredentialTypeProperties(): String {
        return ConversationType.ADMISSION.propertyString
    }
}
