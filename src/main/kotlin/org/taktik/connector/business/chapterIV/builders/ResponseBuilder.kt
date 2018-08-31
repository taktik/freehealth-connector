package org.taktik.connector.business.chapterIV.builders

import org.taktik.connector.business.chapterIV.domain.ChapterIVKmehrResponseWithTimeStampInfo
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.UnsealConnectorException
import be.fgov.ehealth.chap4.core.v1.FaultType
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse
import java.io.Serializable
import org.bouncycastle.tsp.TimeStampResponse

interface ResponseBuilder : Serializable {

    fun retrieveReturnInfo(response: ResponseType): FaultType
    @Throws(UnsealConnectorException::class, org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException::class, org.taktik.connector.technical.exception.TechnicalConnectorException::class)
    fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: AskChap4MedicalAdvisorAgreementResponse): ChapterIVKmehrResponseWithTimeStampInfo

    @Throws(UnsealConnectorException::class, org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException::class, org.taktik.connector.technical.exception.TechnicalConnectorException::class)
    fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: AskChap4MedicalAdvisorAgreementResponse,
                                                                            ignoreWarnings: Boolean): ChapterIVKmehrResponseWithTimeStampInfo

    @Throws(TechnicalConnectorException::class)
    fun convertToTimeStampResponse(bytes: ByteArray): TimeStampResponse

    @Throws(ChapterIVBusinessConnectorException::class)
    fun convertToKmehrResKmehrresponse(bytes: ByteArray): Kmehrresponse?

    @Throws(ChapterIVBusinessConnectorException::class, org.taktik.connector.technical.exception.TechnicalConnectorException::class)
    fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: ConsultChap4MedicalAdvisorAgreementResponse): ChapterIVKmehrResponseWithTimeStampInfo

    @Throws(ChapterIVBusinessConnectorException::class, org.taktik.connector.technical.exception.TechnicalConnectorException::class)
    fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: ConsultChap4MedicalAdvisorAgreementResponse,
                                                                            ignoreWarnings: Boolean): ChapterIVKmehrResponseWithTimeStampInfo
}
