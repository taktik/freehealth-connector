package org.taktik.connector.business.chapterIV.builders.impl

import be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response
import org.taktik.connector.business.chapterIV.builders.ResponseBuilder
import org.taktik.connector.business.chapterIV.builders.WrappedResponseBuilder
import org.taktik.connector.business.chapterIV.common.ConversationType
import org.taktik.connector.business.chapterIV.domain.ChapterIVKmehrResponseWithTimeStampInfo
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException
import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorExceptionValues
import org.taktik.connector.business.chapterIV.validators.Chapter4XmlValidator
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementResponseWrapper
import org.taktik.connector.business.chapterIV.wrapper.UnsealedResponseWrapper
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.InvalidTimeStampException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.exception.UnsealConnectorException
import org.taktik.connector.technical.exception.UnsealConnectorExceptionValues
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.utils.ConnectorCryptoUtils
import org.taktik.connector.technical.utils.ConnectorExceptionUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.validator.impl.TimeStampValidatorFactory
import be.fgov.ehealth.chap4.core.v1.FaultType
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse
import org.apache.juli.logging.LogFactory
import java.io.IOException
import java.util.ArrayList
import org.bouncycastle.tsp.TSPAlgorithms
import org.bouncycastle.tsp.TSPException
import org.bouncycastle.tsp.TimeStampRequest
import org.bouncycastle.tsp.TimeStampRequestGenerator
import org.bouncycastle.tsp.TimeStampResponse
import org.taktik.connector.technical.service.sts.security.Credential

class ResponseBuilderImpl(private val crypto: Crypto, private val credential: Credential,
                          private val validator: Chapter4XmlValidator) : ResponseBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
    override fun bootstrap() {}

    private val config = ConfigFactory.getConfigValidator(ArrayList())
    private val log = LogFactory.getLog(ResponseBuilderImpl::class.java)

    override fun retrieveReturnInfo(response: ResponseType): FaultType {
        return when (response) {
            is AskChap4MedicalAdvisorAgreementResponse -> this.retrieveReturnInfo(WrappedResponseBuilder.wrap(response))
            is ConsultChap4MedicalAdvisorAgreementResponse -> this.retrieveReturnInfo(WrappedResponseBuilder.wrap(response))
            else -> throw UnsupportedOperationException("ResponseType subtype of " + response.javaClass + "not supported")
        }
    }

    private fun retrieveReturnInfo(wrap: Chap4MedicalAdvisorAgreementResponseWrapper<*>) = wrap.returnInfo

    @Throws(UnsealConnectorException::class, ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    override fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: AskChap4MedicalAdvisorAgreementResponse) =
        this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.ADMISSION, false)

    @Throws(UnsealConnectorException::class, ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    override fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: AskChap4MedicalAdvisorAgreementResponse,
                                                                                     ignoreWarnings: Boolean) =
        this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.ADMISSION, ignoreWarnings)

    @Throws(TechnicalConnectorException::class)
    override fun convertToTimeStampResponse(bytes: ByteArray): TimeStampResponse {
        try {
            return TimeStampResponse(bytes)
        } catch (tspException: TSPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, tspException, *arrayOfNulls(0))
        } catch (var5ioException: IOException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, var5ioException, *arrayOfNulls(0))
        }
    }

    @Throws(ChapterIVBusinessConnectorException::class)
    override fun convertToKmehrResKmehrresponse(bytes: ByteArray): Kmehrresponse? {
        return if (bytes.isNotEmpty()) MarshallerHelper(Kmehrresponse::class.java, Kmehrresponse::class.java).toObject(bytes) else null
    }

    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    override fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: ConsultChap4MedicalAdvisorAgreementResponse) =
        this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.CONSULT, false)

    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    override fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(response: ConsultChap4MedicalAdvisorAgreementResponse,
                                                                                     ignoreWarnings: Boolean) =
        this.validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(WrappedResponseBuilder.wrap(response), ConversationType.CONSULT, ignoreWarnings)

    @Throws(ChapterIVBusinessConnectorException::class, UnsealConnectorException::class, TechnicalConnectorException::class)
    private fun validateTimestampAndretrieveChapterIVKmehrResponseWithTimeStampInfo(agreementResponse: Chap4MedicalAdvisorAgreementResponseWrapper<*>,
                                                                                    responseType: ConversationType,
                                                                                    ignoreWarnings: Boolean): ChapterIVKmehrResponseWithTimeStampInfo {
        val unsealedSecuredContent = this.unsealSecuredContent(agreementResponse, ignoreWarnings)
        log.debug("unsealedSecuredContent : " + String(unsealedSecuredContent!!))
        val unsealedResponse = this.getUnsealedResponse(unsealedSecuredContent, responseType)
        if (this.isValidationNeeded(responseType)) {
            //this.validator!!.validate(unsealedResponse.xmlObject)
        }

        val tsRequest = this.generateTimeStampRequest(unsealedResponse.kmehrResponseBytes)
        val timeStampResponse = this.convertToTimeStampResponse(unsealedResponse.timestampReplyBytes)
        this.validateTimeStamp(tsRequest, timeStampResponse)
        val kmehrResponse = this.convertToKmehrResKmehrresponse(unsealedResponse.kmehrResponseBytes)
        if (kmehrResponse != null && this.isValidationNeeded(responseType)) {
            //this.validator!!.validate(kmehrResponse)
        }

        return ChapterIVKmehrResponseWithTimeStampInfo(unsealedResponse.kmehrResponseBytes)
    }

    private fun isValidationNeeded(conversationType: ConversationType) =
        when (conversationType) {
            ConversationType.ADMISSION -> this.getBooleanPropertyDefaultingToTrue("validation.incoming.chapterIV.admission.message")
            ConversationType.CONSULT -> this.getBooleanPropertyDefaultingToTrue("validation.incoming.chapterIV.consultation.message")
            else -> throw IllegalArgumentException("unhandled converstationType + [$conversationType]")
        }

    private fun getBooleanPropertyDefaultingToTrue(configProperty: String): Boolean {
        return "true".equals(config!!.getProperty(configProperty, "true"), ignoreCase = true)
    }

    @Throws(UnsealConnectorException::class, ChapterIVBusinessConnectorException::class)
    private fun validateTimeStamp(tsRequest: TimeStampRequest, timeStampResponse: TimeStampResponse) {
        try {
            log.trace("validating timestamp response")
            timeStampResponse.validate(tsRequest)
            log.trace(" timestamp response validated , now validating timestamp token")
            this.validateTimeStampToken(timeStampResponse)
            log.trace(" timestamp token validated")
        } catch (tspException: TSPException) {
            throw UnsealConnectorException(UnsealConnectorExceptionValues.ERROR_CRYPTO, tspException, "time stamp was not valid :${tspException.message}")
        } catch (techException: TechnicalConnectorException) {
            throw UnsealConnectorException(UnsealConnectorExceptionValues.ERROR_CRYPTO, techException, "error while validating timestamptoken :${techException.message}")
        }
    }

    @Throws(ChapterIVBusinessConnectorException::class, TechnicalConnectorException::class)
    private fun validateTimeStampToken(timeStampResponse: TimeStampResponse) {
        try {
            TimeStampValidatorFactory.instance.validateTimeStampToken(timeStampResponse.timeStampToken)
        } catch (ex: InvalidTimeStampException) {
            throw ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.TIMESTAMP_NOT_CORRECT, ex)
        }
    }

    @Throws(TechnicalConnectorException::class)
    private fun generateTimeStampRequest(bs: ByteArray) =
        TimeStampRequestGenerator().generate(TSPAlgorithms.SHA256, ConnectorCryptoUtils.calculateDigest("SHA-256", bs))

    private fun getUnsealedResponse(unsealedSecuredContent: ByteArray,
                                    type: ConversationType): UnsealedResponseWrapper<*> {
        val helper: MarshallerHelper<*, *>
        if (ConversationType.ADMISSION == type) {
            helper = MarshallerHelper(Response::class.java, Response::class.java)
            return WrappedResponseBuilder.wrap(helper.toObject(unsealedSecuredContent)!!)
        } else if (ConversationType.CONSULT == type) {
            helper =
                MarshallerHelper(be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response::class.java, be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response::class.java)
            return WrappedResponseBuilder.wrap(helper.toObject(unsealedSecuredContent)!!)
        } else {
            throw IllegalArgumentException("unexpected error : code called with unsupported type $type")
        }
    }

    @Throws(ChapterIVBusinessConnectorException::class, UnsealConnectorException::class, TechnicalConnectorException::class)
    protected fun unsealSecuredContent(agreementResponse: Chap4MedicalAdvisorAgreementResponseWrapper<*>,
                                       ignoreWarnings: Boolean): ByteArray? {
        val securedContent = this.getSecuredContent(agreementResponse)

        try {
            val unsealedData =
                this.crypto
                    .unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, securedContent)
            return unsealedData?.contentAsByte
        } catch (var5: UnsealConnectorException) {
            return if (ignoreWarnings) {
                ConnectorExceptionUtils.processUnsealConnectorException(var5)
            } else {
                throw var5
            }
        }
    }

    @Throws(ChapterIVBusinessConnectorException::class)
    protected fun getSecuredContent(agreementResponse: Chap4MedicalAdvisorAgreementResponseWrapper<*>?): ByteArray {
        var result: ByteArray? = null
        if (agreementResponse != null && agreementResponse.response != null) {
            result = agreementResponse.response.securedContent
        }

        return result
            ?: throw ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues.ERROR_RESPONSE_XML, "the AgreementResponse did not contain a securedContent")
    }

}
