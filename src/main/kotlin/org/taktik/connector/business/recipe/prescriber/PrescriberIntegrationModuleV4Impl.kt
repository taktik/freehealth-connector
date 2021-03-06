package org.taktik.connector.business.recipe.prescriber


import be.fgov.ehealth.commons.core.v1.IdentifierType
import be.fgov.ehealth.commons.core.v1.StatusType
import be.fgov.ehealth.commons.core.v2.Status
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.recipe.core.v4.CreatePrescriptionAdministrativeInformationType
import be.fgov.ehealth.recipe.core.v4.SecuredContentType
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckRequest
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesResponse
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksRequest
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksResponse
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberResponse
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationRequest
import be.recipe.services.prescriber.CreatePrescription
import be.recipe.services.prescriber.CreatePrescriptionParam
import be.recipe.services.prescriber.CreatePrescriptionResult
import be.recipe.services.prescriber.GetPrescriptionStatusParam
import be.recipe.services.prescriber.GetPrescriptionStatusResult
import be.recipe.services.prescriber.ListFeedbackItem
import be.recipe.services.prescriber.ListFeedbacksParam
import be.recipe.services.prescriber.ListFeedbacksResult
import be.recipe.services.prescriber.ListOpenRidsParam
import be.recipe.services.prescriber.ListOpenRidsResult
import be.recipe.services.prescriber.ListRidsHistoryParam
import be.recipe.services.prescriber.ListRidsHistoryResult
import be.recipe.services.prescriber.PutVisionParam
import be.recipe.services.prescriber.PutVisionResult
import be.recipe.services.prescriber.RevokePrescriptionParam
import be.recipe.services.prescriber.RevokePrescriptionResult
import be.recipe.services.prescriber.SendNotificationParam
import be.recipe.services.prescriber.SendNotificationResult
import be.recipe.services.prescriber.ValidationPropertiesParam
import be.recipe.services.prescriber.ValidationPropertiesResult
import com.sun.xml.internal.ws.client.ClientTransportException
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.taktik.connector.business.recipe.common.AbstractIntegrationModule
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceImpl
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceV4Impl
import org.taktik.connector.business.recipe.utils.KmehrHelper
import org.taktik.connector.business.recipe.utils.RidValidator
import org.taktik.connector.business.recipe.utils.ValidationUtils
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleValidationException
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.ByteArrayInputStream
import java.io.IOException
import java.security.KeyStore
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory
import kotlin.collections.ArrayList


class PrescriberIntegrationModuleV4Impl(val stsService: STSService, keyDepotService: KeyDepotService) : AbstractIntegrationModule(keyDepotService), PrescriberIntegrationModuleV4 {
    private val log = LoggerFactory.getLogger(PrescriberIntegrationModuleV4Impl::class.java)
    private val recipePrescriberServiceV4 = RecipePrescriberServiceV4Impl()
    private val useNewCall = false
    private val keyCache = HashMap<String, KeyResult>()
    private val recipePrescriberService = RecipePrescriberServiceImpl()
    private val kmehrHelper = KmehrHelper(Properties().apply { load(this::class.java.getResourceAsStream("/org/taktik/connector/business/recipe/validation.properties")) })

    private fun getNewKey(credential: KeyStoreCredential,
                            nihii: String,
                            patientId: String,
                            prescriptionType: String): KeyResult? {
        var key: KeyResult? = null

        val cacheId = "($patientId#$prescriptionType)"
        if (keyCache.containsKey(cacheId)) {
            key = keyCache[cacheId]
            keyCache.remove(cacheId)
        } else {
            key = getNewKeyFromKgss(credential, prescriptionType, nihii, null, patientId, stsService.getHolderOfKeysEtk(credential, nihii)!!.encoded)
        }
        return key
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun ping(samlToken: SAMLToken, credential: KeyStoreCredential): AliveCheckResponse {
        var response =  try {
            recipePrescriberService.aliveCheck(samlToken, credential, AliveCheckRequest())
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
        }
        checkStatus(response)

        return response
    }


    override fun createPrescription(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        nihii: String,
        feedbackRequested: Boolean,
        patientId: String,
        prescription: ByteArray,
        prescriptionType: String,
        vision: String?,
        vendorName: String?,
        packageName: String?,
        packageVersion: String?,
        expirationDate: LocalDateTime
                                   ): String? {
        ValidationUtils.validatePatientIdNotBlank(patientId)
        ValidationUtils.validatePatientId(patientId)
        ValidationUtils.validateVisi(vision, false)

        return try {
            val propertyHandler: PropertyHandler = PropertyHandler.getInstance()
            val expDateAsString = expirationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            ValidationUtils.validateExpirationDate(expDateAsString)
            //performValidation(prescription, prescriptionType, expDateAsString)
            val helper = MarshallerHelper(CreatePrescriptionResult::class.java, CreatePrescriptionParam::class.java)
            val etkRecipes: List<*> = etkHelper.recipe_ETK
            val key: KeyResult = getNewKey(credential, nihii, patientId, prescriptionType) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_KGSS, "Cannot obtain key from KGSS for patient $patientId")
            val message = getCrypto(credential).seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, null, KeyResult(key.secretKey, key.keyId), IOUtils.compress(prescription))
            val params = CreatePrescriptionParam()
            params.prescription = message
            params.prescriptionType = prescriptionType
            params.isFeedbackRequested = feedbackRequested
            params.symmKey = symmKey.encoded
            params.keyId = key.keyId
            params.patientId = patientId
            params.expirationDate = expDateAsString
            params.vision = vision
            params.prescriberId = nihii

            log.info("Recip-e v4 prescription is {}", prescription.toString(Charsets.UTF_8))
            log.info("Recip-e v4 message is {}", ConnectorXmlUtils.toString(params))

            val response: CreatePrescriptionResponse? = if (useNewCall) {
                val request = CreatePrescription()
                request.createPrescriptionParamSealed = sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(params))

                request.keyId = key.keyId
                request.prescriptionType = prescriptionType
                request.documentId = generateRid(prescriptionType)
                request.prescriptionVersion = extractPrescriptionVersionFromKmehr(prescription)
                request.referenceSourceVersion = extractReferenceSourceVersionFromKmehr(prescription)
                request.programIdentification = "${vendorName?:"freehealth-connector"}/${packageVersion?:""}"
                request.mguid = UUID.randomUUID().toString()

                log.info("Recip-e v4 request is {}", ConnectorXmlUtils.toString(request))

                recipePrescriberServiceV4.createPrescriptionV4(samlToken, credential, request) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
            } else {
                val request = CreatePrescriptionRequest()
                request.securedCreatePrescriptionRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(params)))

                request.programId = "${vendorName?:"freehealth-connector"}/${packageVersion?:""}"
                request.id = "id" + UUID.randomUUID().toString()
                request.issueInstant = DateTime.now()

                val adminValue = CreatePrescriptionAdministrativeInformationType()
                adminValue.keyIdentifier = key.keyId.toByteArray()
                adminValue.prescriptionVersion = extractPrescriptionVersionFromKmehr(prescription)
                adminValue.referenceSourceVersion = extractReferenceSourceVersionFromKmehr(prescription)
                adminValue.prescriptionType = prescriptionType
                request.administrativeInformation = adminValue

                log.info("Recip-e v4 request is {}", ConnectorXmlUtils.toString(request))

                recipePrescriberServiceV4.createPrescription(samlToken, credential, request) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
            }

            response?.let { r ->
                helper.unsealWithSymmKey(r.securedCreatePrescriptionResponse.securedContent, symmKey)?.also {
                    checkStatus(response)
                }
            }?.rid
        } catch (var29: Throwable) {
            Exceptionutils.errorHandler(var29)
            null
        }
    }

    private fun generateRid(prescriptionType: String) =
        ("BE" + prescriptionType + "JNT" + RandomStringUtils.random(5, true, false).toUpperCase()).replace('I', 'J').replace('O', 'A').replace('U', 'V')

    override fun getPrescriptionStatus(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String
                             ): GetPrescriptionStatusResult? {
        RidValidator.validateRid(rid)
        val param = GetPrescriptionStatusParam()
        param.rid = rid
        param.prescriberId = nihii
        param.symmKey = symmKey.encoded

        return try {
            val request = getGetPrescriptionStatus(credential, param) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
            try {
                val response: GetPrescriptionStatusResponse? = recipePrescriberServiceV4.getPrescriptionStatus(samlToken, credential, request)
                response?.let {
                    unsealGetPrescriptionStatusResponse(it).also {
                        checkStatus(it)
                    }
                }
            } catch (ex: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), ex)
            }
        } catch (err: Throwable) {
            Exceptionutils.errorHandler(err)
            null
        }
    }

    fun listFeedback(
        samlToken: SAMLToken, credential: KeyStoreCredential,
        readFlag: Boolean
    ) : List<ListFeedbackItem>? {
        return try {
            val helper = MarshallerHelper(ListFeedbacksResult::class.java, ListFeedbacksParam::class.java)
            val etkRecipes = etkHelper.recipe_ETK
            val param = ListFeedbacksParam()
            param.readFlag = readFlag
            param.symmKey = this.symmKey.encoded
            val request = ListFeedbacksRequest()
            request.securedListFeedbacksRequest = this.createSecuredContentType(
                this.sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param))
            )
            request.programId = PropertyHandler.getInstance().getProperty("programIdentification")
            request.issueInstant = DateTime()
            request.id =  "id" + UUID.randomUUID().toString()
            var response: ListFeedbacksResponse? = null
            response = try {
                recipePrescriberServiceV4.listFeedbacks(samlToken, credential, request)
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
            }
            // unseal WS response

            val feedbacks = helper.unsealWithSymmKey(response!!.securedListFeedbacksResponse.securedContent, symmKey).feedbacks

            for (i in feedbacks.indices) {
                val item = org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem(feedbacks[i])
                item.content = try { unsealFeedback(getCrypto(credential), item.content)?.let {IOUtils.decompress(it)} ?: item.content } catch (t: Throwable) {item.linkedException = t; item.content}

                feedbacks[i] = item
            }
            feedbacks
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
            null
        }
    }

    override fun listRidsHistory(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        param: ListRidsHistoryParam
                                ): ListRidsHistoryResult? {
        ValidationUtils.validatePatientId(param.patientId)

        return try {
            val listRidsHistory: ListRidsHistoryRequest = getListPrescriptionHistoryRequest(credential, param) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
            try {
                val response = recipePrescriberServiceV4.listRidsHistory(samlToken, credential, listRidsHistory)
                response?.let {
                    unsealListPrescriptionHistoryResponse(it).also {
                        checkStatus(it)
                    }
                }
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte)
            }
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
            null
        }
    }

    override fun listOpenRids(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ListOpenRidsParam): ListOpenRidsResult? {
        ValidationUtils.validatePatientId(param.patientId)

        return try {
            val listOpenRids = getListOpenRids(credential, param) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
            try {
                val response =
                    recipePrescriberServiceV4.listOpenRids(samlToken, credential, listOpenRids)
                response?.let {
                    unsealListOpenRidsResponse(it).also {
                        checkStatus(it)
                    }
                }
            } catch (var8: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8)
            }
        } catch (var9: Throwable) {
            Exceptionutils.errorHandler(var9)
            null
        }
    }

    override fun getValidationProperties(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ValidationPropertiesParam): ValidationPropertiesResult? {
        return try {
            val validationProperties: GetValidationPropertiesRequest = getValidationProperties(credential, param)
                ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
            val response = recipePrescriberServiceV4.getValidationProperties(samlToken, credential, validationProperties)

            response?.let {
                unsealValidationPropertiesResponse(it).also {
                    checkStatus(it)
                }
            }
        } catch (var9: Throwable) {
            Exceptionutils.errorHandler(var9)
            null
        }
    }

    override fun setVision(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: PutVisionParam
                        ): PutVisionResult? {
        RidValidator.validateRid(param.rid)
        ValidationUtils.validateVisi(param.vision, false)

        return try {
            val putVision: PutVisionForPrescriberRequest = putVisionRequest(credential, param) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
                val response = recipePrescriberServiceV4.putVisionForPrescriber(samlToken, credential, putVision)
                response?.let {
                    unsealPutVisionResponse(it).also {
                        checkStatus(it)
                    }
                }
        } catch (var9: Throwable) {
            Exceptionutils.errorHandler(var9)
            null
        }
    }

    @Throws(IntegrationModuleException::class)
    protected fun putVisionRequest(
        credential: KeyStoreCredential,
        param: PutVisionParam
                                  ): PutVisionForPrescriberRequest? {
        val helper = MarshallerHelper(PutVisionParam::class.java, PutVisionParam::class.java)
        val etkRecipes: List<*> = etkHelper.recipe_ETK

        param.symmKey = symmKey.encoded
        val request = PutVisionForPrescriberRequest()
        request.securedPutVisionForPrescriberRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(param)))
        request.programId = PropertyHandler.getInstance().getProperty("programIdentification") ?: "freehealth-connector"
        request.issueInstant = DateTime()
        request.id = "id" + UUID.randomUUID().toString()
        return request
    }

    private fun unsealPutVisionResponse(response: PutVisionForPrescriberResponse): PutVisionResult {
        val marshaller: MarshallerHelper<PutVisionResult, Any> =
            MarshallerHelper(PutVisionResult::class.java, Any::class.java)
        return marshaller.unsealWithSymmKey(response.securedPutVisionForPrescriberResponse.securedContent, symmKey)
    }


    protected fun getValidationProperties(
        credential: KeyStoreCredential,
        param: ValidationPropertiesParam
                                         ): GetValidationPropertiesRequest? {
        val helper = MarshallerHelper(ValidationPropertiesParam::class.java, ValidationPropertiesParam::class.java)
        val etkRecipes: List<*> = etkHelper.recipe_ETK

        param.symmKey = symmKey.encoded
        val request = GetValidationPropertiesRequest()
        request.securedGetValidationPropertiesRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(param)))
        request.programId = PropertyHandler.getInstance().getProperty("programIdentification") ?: "freehealth-connector"
        request.issueInstant = DateTime()
        request.id = "id" + UUID.randomUUID().toString()
        return request
    }

    private fun unsealValidationPropertiesResponse(response: GetValidationPropertiesResponse): ValidationPropertiesResult {
        val marshaller: MarshallerHelper<ValidationPropertiesResult, Any> =
            MarshallerHelper(ValidationPropertiesResult::class.java, Any::class.java)
        return marshaller.unsealWithSymmKey(response.securedGetValidationPropertiesResponse.securedContent, symmKey)
    }

    @Throws(IntegrationModuleException::class)
    protected fun getListOpenRids(
        credential: KeyStoreCredential,
        param: ListOpenRidsParam
                                 ): ListOpenRidsRequest? {
        val helper = MarshallerHelper(ListOpenRidsParam::class.java, ListOpenRidsParam::class.java)
        val etkRecipes: List<*> = etkHelper.recipe_ETK

        param.symmKey = symmKey.encoded

        val request = ListOpenRidsRequest()
        request.securedListOpenRidsRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(param)))
        request.programId = PropertyHandler.getInstance().getProperty("programIdentification") ?: "freehealth-connector"
        request.issueInstant = DateTime()
        request.id = "id" + UUID.randomUUID().toString()
        return request
    }

    private fun unsealListOpenRidsResponse(response: ListOpenRidsResponse): ListOpenRidsResult {
        val marshaller: MarshallerHelper<ListOpenRidsResult, Any> =
            MarshallerHelper(ListOpenRidsResult::class.java, Any::class.java)
        return marshaller.unsealWithSymmKey(response.securedListOpenRidsResponse.securedContent, symmKey)
    }

    private fun getListPrescriptionHistoryRequest(
        credential: KeyStoreCredential,
        listRidHistoryParam: ListRidsHistoryParam
                                                 ): ListRidsHistoryRequest? {
        val helper = MarshallerHelper(ListRidsHistoryParam::class.java, ListRidsHistoryParam::class.java)
        val etkRecipes: List<*> = etkHelper.recipe_ETK

        listRidHistoryParam.symmKey = symmKey.encoded
        val listRidsHistory = ListRidsHistoryRequest()
        listRidsHistory.securedListRidsHistoryRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(listRidHistoryParam)))
        listRidsHistory.programId = PropertyHandler.getInstance().getProperty("programIdentification")
        listRidsHistory.id = "id" + UUID.randomUUID().toString()
        listRidsHistory.issueInstant = DateTime()
        return listRidsHistory
    }

    private fun unsealListPrescriptionHistoryResponse(response: ListRidsHistoryResponse): ListRidsHistoryResult {
        val marshaller: MarshallerHelper<ListRidsHistoryResult, Any> =
            MarshallerHelper(ListRidsHistoryResult::class.java, Any::class.java)
        return marshaller.unsealWithSymmKey(response.securedListRidsHistoryResponse.securedContent, symmKey)
    }

    protected fun getGetPrescriptionStatus(
        credential: KeyStoreCredential,
        param: GetPrescriptionStatusParam
                                          ): GetPrescriptionStatusRequest? {

        val helper = MarshallerHelper(GetPrescriptionStatusParam::class.java, GetPrescriptionStatusParam::class.java)
        val etkRecipes: List<*> = etkHelper.recipe_ETK

        param.symmKey = symmKey.encoded
        val request = GetPrescriptionStatusRequest()
        request.securedGetPrescriptionStatusRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(param)))
        request.programId = PropertyHandler.getInstance().getProperty("programIdentification") ?: "freehealth-connector"
        request.issueInstant = DateTime()
        request.id = "id" + UUID.randomUUID().toString()
        return request
    }

    private fun unsealGetPrescriptionStatusResponse(response: GetPrescriptionStatusResponse): GetPrescriptionStatusResult {
        val marshaller: MarshallerHelper<GetPrescriptionStatusResult, Any> =
            MarshallerHelper(GetPrescriptionStatusResult::class.java, Any::class.java)
        return marshaller.unsealWithSymmKey(response.securedGetPrescriptionStatusResponse.securedContent, symmKey)
    }


    private fun getDefaultExpirationDate(): String? {
        return this.getDefaultExpirationDate(null as String?)
    }

    private fun getDefaultExpirationDate(expirationDate: String?): String? {
        return if (StringUtils.isBlank(expirationDate)) {
            val defaultExpirationDate: Calendar = Calendar.getInstance()
            defaultExpirationDate.add(2, 3)
            val var10000 = "yyyy-MM-dd"
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sdf.format(defaultExpirationDate.time)
        } else {
            expirationDate
        }
    }

    private fun createSecuredContentType(content: ByteArray): SecuredContentType? {
        val secured = SecuredContentType()
        secured.securedContent = content
        return secured
    }

    @Throws(IntegrationModuleException::class)
    private fun performValidation(prescription: ByteArray,
        prescriptionType: String,
        expirationDateFromRequest: String) {
        val errors = ArrayList<String>()
        try {
            kmehrHelper.assertValidKmehrPrescription(prescription, prescriptionType)
        } catch (var7: IntegrationModuleValidationException) {
            errors.addAll(var7.validationErrors)
        }
        validateExpirationDateFromKmehr(prescription, errors, expirationDateFromRequest)
        if (errors.isNotEmpty()) {
            log.info("******************************************************")
            errors.forEach { log.info("Errors found in the kmehr:$it") }
            log.info("******************************************************")
            throw IntegrationModuleValidationException(I18nHelper.getLabel("error.xml.invalid"), errors)
        }
    }

    @Throws(IntegrationModuleException::class)
    private fun validateExpirationDateFromKmehr(xmlDocument: ByteArray,
        errors: MutableList<String>,
        expirationDateFromRequest: String) {
        try {
            val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = false
            val builder: DocumentBuilder = factory.newDocumentBuilder()
            val kmehrDocument: Document = builder.parse(ByteArrayInputStream(xmlDocument))
            val propertyHandler =
                PropertyHandler.getInstance()
            val xpath: XPath = XPathFactory.newInstance().newXPath()
            val xpathStr = propertyHandler.getProperty("expirationdate.xpath")
            val expirationDateNodeList: NodeList =
                xpath.evaluate(xpathStr, kmehrDocument, XPathConstants.NODESET) as NodeList
            if (expirationDateNodeList.item(0) != null) {
                val expirationDateFromKmehr: String = expirationDateNodeList.item(0).textContent
                if (!expirationDateFromKmehr.contentEquals(expirationDateFromRequest)) {
                    errors.add(I18nHelper.getLabel("error.validation.expirationdate.different.message", arrayOf<Any>(expirationDateFromRequest, expirationDateFromKmehr)))
                }
            } else {
                errors.add(I18nHelper.getLabel("error.validation.expirationdate.kmehr"));
            }
        } catch (ex: ParserConfigurationException) {
            Exceptionutils.errorHandler(ex)
        } catch (ex: SAXException) {
            Exceptionutils.errorHandler(ex)
        } catch (ex: IOException) {
            Exceptionutils.errorHandler(ex)
        } catch (ex: XPathExpressionException) {
            Exceptionutils.errorHandler(ex)
        }
    }

    @Throws(IntegrationModuleException::class)
    private fun extractPrescriptionVersionFromKmehr(xmlDocument: ByteArray): String? {
        return try {
            val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = false
            val builder: DocumentBuilder = factory.newDocumentBuilder()
            val kmehrDocument: Document = builder.parse(ByteArrayInputStream(xmlDocument))
            val propertyHandler =
                PropertyHandler.getInstance()
            val xpath: XPath = XPathFactory.newInstance().newXPath()
            val xpathStr = propertyHandler.getProperty("prescriptionVersion.xpath")
            val prescriptionVersionNodeList: NodeList =
                xpath.evaluate(xpathStr, kmehrDocument, XPathConstants.NODESET) as NodeList
            if (prescriptionVersionNodeList.item(0) != null) {
                val prescriptionVersion: String = prescriptionVersionNodeList.item(0).textContent
                "kmehr_$prescriptionVersion"
            } else {
                "Unknown"
            }
        } catch (var10: ParserConfigurationException) {
            Exceptionutils.errorHandler(var10)
            null
        } catch (var10: SAXException) {
            Exceptionutils.errorHandler(var10)
            null
        } catch (var10: IOException) {
            Exceptionutils.errorHandler(var10)
            null
        } catch (var10: XPathExpressionException) {
            Exceptionutils.errorHandler(var10)
            null
        }
    }

    @Throws(IntegrationModuleException::class)
    private fun extractReferenceSourceVersionFromKmehr(xmlDocument: ByteArray): String? {
        return try {
            val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance().apply { isNamespaceAware = false }
            val builder: DocumentBuilder = factory.newDocumentBuilder()
            val kmehrDocument: Document = builder.parse(ByteArrayInputStream(xmlDocument))
            val propertyHandler = PropertyHandler.getInstance()
            val xpath: XPath = XPathFactory.newInstance().newXPath()
            val xpathStr1 = propertyHandler.getProperty("referenceSourceVersion.xpath1")
            val referenceSourceVersionNodeList1: NodeList = xpath.evaluate(xpathStr1, kmehrDocument, XPathConstants.NODESET) as NodeList
            val referenceSourceVersionPart1 = referenceSourceVersionNodeList1.item(0)?.let { it.textContent } ?: ""
            val xpathStr2 = propertyHandler.getProperty("referenceSourceVersion.xpath2")
            val referenceSourceVersionNodeList2: NodeList = xpath.evaluate(xpathStr2, kmehrDocument, XPathConstants.NODESET) as NodeList
            val referenceSourceVersionPart2 = referenceSourceVersionNodeList2.item(0)?.let { it.textContent } ?: ""
            val referenceSourceVersion = "$referenceSourceVersionPart1:$referenceSourceVersionPart2"
            if (StringUtils.isNotBlank(referenceSourceVersion)) referenceSourceVersion else "Unknown"
        } catch (var14: ParserConfigurationException) {
            Exceptionutils.errorHandler(var14)
            null
        } catch (var14: SAXException) {
            Exceptionutils.errorHandler(var14)
            null
        } catch (var14: IOException) {
            Exceptionutils.errorHandler(var14)
            null
        } catch (var14: XPathExpressionException) {
            Exceptionutils.errorHandler(var14)
            null
        }
    }

    fun sendNotification(
        samlToken: SAMLToken, credential: KeyStoreCredential,
        notificationText: ByteArray,
        patientId: String,
        executorId: String
    ) {
        try {
            val helper = MarshallerHelper(Any::class.java, SendNotificationParam::class.java)
            val etkRecipes = etkHelper.recipe_ETK
            val etkRecipients = etkHelper.getEtks(KgssIdentifierType.NIHII_PHARMACY, executorId)
            val notificationZip = IOUtils.compress(notificationText)
            for (i in etkRecipients.indices) {
                val etkRecipient = etkRecipients[0]
                val notificationSealed = sealNotification(getCrypto(credential), etkRecipient, notificationZip)
                val param = SendNotificationParam()
                param.content = notificationSealed
                param.executorId = executorId
                param.patientId = patientId
                param.symmKey = this.symmKey.encoded
                val request = SendNotificationRequest()
                request.securedSendNotificationRequest = this.createSecuredContentType(
                    this.sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param))
                )
                request.programId = PropertyHandler.getInstance().getProperty("programIdentification")
                request.issueInstant = DateTime()
                request.id = "id" + UUID.randomUUID().toString()
                try {
                    val response = recipePrescriberServiceV4.sendNotification(samlToken, credential, request)
                    val marshaller = MarshallerHelper(SendNotificationResult::class.java, SendNotificationResult::class.java)
                    this.checkStatus(
                        marshaller.unsealWithSymmKey(response!!.securedSendNotificationResponse.securedContent, this.symmKey)
                    )
                } catch (cte: ClientTransportException) {
                    throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
                }
            }
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }
    }


    /**
     * Cancel prescription.
     *
     *
     *
     *
     * @param samlToken
     * @param credential
     * @param nihii
     * @param rid    the rid
     * @param reason the reason
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    override fun revokePrescription(samlToken: SAMLToken, credential: KeyStoreCredential, nihii: String, rid: String, reason: String) {
        validateRid(rid)

        try {
            // init helper
            val helper = MarshallerHelper(Any::class.java, RevokePrescriptionParam::class.java)

            // get Recipe ETK
            val etkRecipes = etkHelper.recipe_ETK

            // create params
            val params = RevokePrescriptionParam()
            params.rid = rid
            params.reason = reason
            params.prescriberId = nihii
            params.symmKey = symmKey.encoded

            // create request
            val request = be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest()
            request.securedRevokePrescriptionRequest = createSecuredContentType(sealRequest(getCrypto(credential),etkRecipes[0], helper.toXMLByteArray(params)))
            request.programId = PropertyHandler.getInstance().getProperty("programIdentification") ?: "freehealth-connector"
            request.issueInstant = DateTime()
            request.id = "id" + UUID.randomUUID().toString()

            // call WS
            try {
                val response = recipePrescriberServiceV4.revokePrescription(samlToken, credential, request)
                val marshaller = MarshallerHelper(RevokePrescriptionResult::class.java, Any::class.java)
                checkStatus(marshaller.unsealWithSymmKey(response!!.securedRevokePrescriptionResponse.securedContent, symmKey))
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
            }

        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

    }

    private fun getLocalisedMsg(status: Status): String {
        return if (status.statusMessage.isNotBlank()) status.statusMessage else status.statusCode.value
    }

    protected fun checkStatus(response: StatusResponseType) {
        if (EHEALTH_SUCCESS_CODE_100 != response.status.statusCode.value && EHEALTH_SUCCESS_CODE_200 != response.status.statusCode.value && EHEALTH_SUCCESS_URN != response.status.statusCode.value) {
            log.error("Error Status received : " + response.status.statusCode.value)
            throw IntegrationModuleException(getLocalisedMsg(response.status))
        }
    }

    protected fun checkStatus(response: ResponseType) {
        if (AbstractIntegrationModule.EHEALTH_SUCCESS_CODE_100 != response.status.code && AbstractIntegrationModule.EHEALTH_SUCCESS_CODE_200 != response.status.code) {
            log.error("Error Status received : " + response.status.code)
            throw IntegrationModuleException(getLocalisedMsg(response.status))
        }
    }
    protected fun checkStatus(response: be.recipe.services.core.ResponseType) {
        if (AbstractIntegrationModule.EHEALTH_SUCCESS_CODE_100 != response.status.code && AbstractIntegrationModule.EHEALTH_SUCCESS_CODE_200 != response.status.code) {
            log.error("Error Status received : " + response.status.code)
            throw IntegrationModuleException(getLocalisedMsg(response.status))
        }
    }
    private fun getLocalisedMsg(status: StatusType): String {
        val locale = IntegrationModuleException.getUserLocale()
        for (msg in status.messages) {
            if (msg.lang != null && locale.equals(msg.lang.value(), ignoreCase = true)) {
                return msg.value
            }
        }
        return if (status.messages.size > 0) {
            status.messages[0].value
        } else status.code
    }
    private fun getLocalisedMsg(status: be.recipe.services.core.StatusType): String {
        val locale = IntegrationModuleException.getUserLocale()
        for (msg in status.messages) {
            if (msg.lang != null && locale.equals(msg.lang.value(), ignoreCase = true)) {
                return msg.value
            }
        }
        return if (status.messages.size > 0) {
            status.messages[0].value
        } else status.code
    }

    protected fun createIdentifierType(id: String, type: String): IdentifierType {
        val ident = IdentifierType()
        ident.id = id + ""
        ident.type = type
        return ident
    }

    @Throws(IntegrationModuleException::class)
    protected fun unsealFeedback(crypto: Crypto, message: ByteArray?): ByteArray? {
        return message?.let { unsealNotiffeed(crypto, it) }
    }

    @Throws(IntegrationModuleException::class)
    protected fun getNewKeyFromKgss(credential: KeyStoreCredential, prescriptionType: String, prescriberId: String, executorId: String?, patientId: String, myEtk: ByteArray): KeyResult? {
        val etkKgss = etkHelper.kgsS_ETK[0]
        val credentialTypes = propertyHandler.getMatchingProperties("kgss.createPrescription.ACL.$prescriptionType")?.map { it.replace(Regex("\\{saml.quality}"), credential.quality) }

        var keyResult: KeyResult? = null
        try {
            keyResult = kgssService.retrieveNewKey(credential, etkKgss.encoded, credentialTypes, prescriberId, executorId, patientId, myEtk);
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

        return keyResult
    }


    @Throws(IntegrationModuleException::class)
    protected fun sealNotification(crypto: Crypto, paramEncryptionToken: EncryptionToken, paramArrayOfByte: ByteArray): ByteArray {
        return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, paramEncryptionToken, paramArrayOfByte)
    }

    protected fun getCrypto(credential: KeyStoreCredential) : Crypto {
        val hokPrivateKeys = KeyManager.getDecryptionKeys(credential.keyStore, credential.password)
        return CryptoFactory.getCrypto(credential, hokPrivateKeys)
    }

}
