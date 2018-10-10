package org.taktik.connector.business.chapterIV.domain

import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper
import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType
import be.fgov.ehealth.chap4.core.v1.CommonInputType
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import java.io.Serializable

class ChapterIVBuilderResponse(private val result: Map<String, Serializable>?) : Serializable {

    val kmerhMessage: Kmehrmessage?
        get() = this.transform("kmehrmessage", Kmehrmessage::class.java)

    val chapterIVReferences: ChapterIVReferences?
        get() = this.transform("references", ChapterIVReferences::class.java)

    val folder: FolderType?
        get() = this.transform("folder", FolderType::class.java)

    val careReceiver: CareReceiverIdType?
        get() = this.transform("carereceiver", CareReceiverIdType::class.java)

    val recordCommonInput: RecordCommonInputType?
        get() = this.transform("recordcommoninput", RecordCommonInputType::class.java)

    val commonInput: CommonInputType?
        get() = this.transform("commoninput", CommonInputType::class.java)

    val sealedRequest: SealedRequestWrapper<*>?
        get() = this.transform("sealedrequest", SealedRequestWrapper::class.java)

    val requestWrapper: Chap4MedicalAdvisorAgreementRequestWrapper<*>?
        get() = this.transform("result", Chap4MedicalAdvisorAgreementRequestWrapper::class.java)

    val askChap4MedicalAdvisorAgreementRequest: AskChap4MedicalAdvisorAgreementRequest?
        get() = this.requestWrapper?.xmlObject?.let { it as? AskChap4MedicalAdvisorAgreementRequest }

    val consultChap4MedicalAdvisorAgreementRequest: ConsultChap4MedicalAdvisorAgreementRequest?
        get()  = this.requestWrapper?.xmlObject?.let { it as? ConsultChap4MedicalAdvisorAgreementRequest }

    private fun <T> transform(key: String, clazz: Class<T>): T? {
        if (this.result!!.containsKey(key)) {
            val resultObj = this.result[key]
            if (clazz.isInstance(resultObj)) {
                return resultObj as T
            }
        }
        return null
    }

    override fun hashCode(): Int {
        var resultFunction = 1
        resultFunction = 31 * resultFunction + if (this.result == null) 0 else this.result.hashCode()
        return resultFunction
    }

    override fun equals(obj: Any?): Boolean {
        when {
            this === obj -> return true
            obj == null -> return false
            this.javaClass != obj.javaClass -> return false
            else -> {
                val other = obj as ChapterIVBuilderResponse?
                if (this.result == null) {
                    if (other!!.result != null) {
                        return false
                    }
                } else if (this.result != other!!.result) {
                    return false
                }
                return true
            }
        }
    }
}
