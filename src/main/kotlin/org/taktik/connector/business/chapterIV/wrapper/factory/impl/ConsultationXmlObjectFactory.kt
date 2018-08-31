package org.taktik.connector.business.chapterIV.wrapper.factory.impl

import be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request
import org.taktik.connector.business.chapterIV.common.ConversationType
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultSealedRequestWrapperImpl
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultUnsealedRequestWrapperImpl
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest

class ConsultationXmlObjectFactory : XmlObjectFactory {
    override fun createSealedRequest(): SealedRequestWrapper<Request> {
        return ConsultSealedRequestWrapperImpl()
    }

    override fun createUnsealedRequest(): UnsealedRequestWrapper<be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Request> {
        return ConsultUnsealedRequestWrapperImpl()
    }

    override fun createChap4MedicalAdvisorAgreementRequest(): Chap4MedicalAdvisorAgreementRequestWrapper<ConsultChap4MedicalAdvisorAgreementRequest> {
        return ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl()
    }

    override fun getSubtypeNameToRetrieveCredentialTypeProperties(): String {
        return ConversationType.CONSULT.propertyString
    }
}
