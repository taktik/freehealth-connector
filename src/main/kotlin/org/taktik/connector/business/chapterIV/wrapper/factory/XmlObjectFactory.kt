package org.taktik.connector.business.chapterIV.wrapper.factory

import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper

interface XmlObjectFactory {
    fun createSealedRequest(): SealedRequestWrapper<*>
    fun createUnsealedRequest(): UnsealedRequestWrapper<*>
    fun createChap4MedicalAdvisorAgreementRequest(): Chap4MedicalAdvisorAgreementRequestWrapper<*>
    fun getSubtypeNameToRetrieveCredentialTypeProperties(): String
}
