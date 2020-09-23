package org.taktik.connector.business.recipe.prescriber


import be.fgov.ehealth.commons.core.v2.Status
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType
import be.fgov.ehealth.recipe.core.v4.CreatePrescriptionAdministrativeInformationType
import be.fgov.ehealth.recipe.core.v4.SecuredContentType
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesResponse
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberResponse
import be.recipe.services.prescriber.CreatePrescriptionParam
import be.recipe.services.prescriber.CreatePrescriptionResult
import be.recipe.services.prescriber.GetPrescriptionStatusParam
import be.recipe.services.prescriber.GetPrescriptionStatusResult
import be.recipe.services.prescriber.ListOpenRidsParam
import be.recipe.services.prescriber.ListOpenRidsResult
import be.recipe.services.prescriber.ListRidsHistoryParam
import be.recipe.services.prescriber.ListRidsHistoryResult
import be.recipe.services.prescriber.PutVisionParam
import be.recipe.services.prescriber.PutVisionResult
import be.recipe.services.prescriber.ValidationPropertiesParam
import be.recipe.services.prescriber.ValidationPropertiesResult
import com.sun.xml.internal.ws.client.ClientTransportException
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceV4Impl
import org.taktik.connector.business.recipe.utils.RidValidator
import org.taktik.connector.business.recipe.utils.ValidationUtils
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleValidationException
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.etee.Crypto
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
import java.util.Calendar
import java.util.UUID
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory


class PrescriberIntegrationModuleV4Impl(stsService: STSService, keyDepotService: KeyDepotService) : PrescriberIntegrationModuleImpl(stsService, keyDepotService), PrescriberIntegrationModuleV4 {
    private val log = LoggerFactory.getLogger(PrescriberIntegrationModuleV4Impl::class.java)
    private val recipePrescriberServiceV4 = RecipePrescriberServiceV4Impl()

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
        expirationDate: LocalDateTime
                                   ): String? {
        ValidationUtils.validatePatientIdNotBlank(patientId)
        ValidationUtils.validatePatientId(patientId)
        ValidationUtils.validateVisi(vision, false)

        return try {
            val propertyHandler: PropertyHandler = PropertyHandler.getInstance()
            val expDateAsString = expirationDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))
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

            val request = CreatePrescriptionRequest()
            request.securedCreatePrescriptionRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0] as EncryptionToken, helper.toXMLByteArray(params)))

            //request.programId = vendorName ?: "freehealth-connector"
            request.programId = propertyHandler.getProperty("programIdentification") ?: "freehealth-connector"
            request.id = "id" + UUID.randomUUID().toString()
            request.issueInstant = DateTime.now()

            val adminValue = CreatePrescriptionAdministrativeInformationType()
            adminValue.keyIdentifier = key.keyId.toByteArray()
            adminValue.prescriptionVersion = extractPrescriptionVersionFromKmehr(prescription)
            adminValue.referenceSourceVersion = extractReferenceSourceVersionFromKmehr(prescription)
            adminValue.prescriptionType = prescriptionType
            request.administrativeInformation = adminValue

            log.info("Recip-e v4 request is {}", ConnectorXmlUtils.toString(request))

            val response: CreatePrescriptionResponse? = recipePrescriberServiceV4.createPrescription(samlToken, credential, request) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")

            response?.let { r ->
                helper.unsealWithSymmKey(r.securedCreatePrescriptionResponse.securedContent, symmKey)?.also {
                    checkStatus(response)
                    setPatientId(it.rid, patientId)
                }
            }?.rid
        } catch (var29: Throwable) {
            Exceptionutils.errorHandler(var29)
            null
        }
    }

    override fun getData(
    keystore: KeyStore,
    samlToken: SAMLToken,
    passPhrase: String,
    credential: KeyStoreCredential,
    param: GetPrescriptionStatusParam
                        ): GetPrescriptionStatusResult? {
        RidValidator.validateRid(param.rid)
        return try {
            val getPrescriptionStatusRequest = getGetPrescriptionStatus(credential, param) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
            try {
                val response: GetPrescriptionStatusResponse? = recipePrescriberServiceV4.getPrescriptionStatus(samlToken, credential, getPrescriptionStatusRequest)
                response?.let {
                    unsealGetPrescriptionStatusResponse(it).also {
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

    override fun getData(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ListRidsHistoryParam): ListRidsHistoryResult? {
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
            } catch (var8: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8)
            }
        } catch (var9: Throwable) {
            Exceptionutils.errorHandler(var9)
            null
        }
    }

    override fun getData(
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

    override fun getData(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ValidationPropertiesParam): ValidationPropertiesResult? {
        return try {
            val validationProperties: GetValidationPropertiesRequest = getValidationProperties(credential, param) ?: throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON, "Unknown error in recipe")
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

    override fun putData(
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
            val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = false
            val builder: DocumentBuilder = factory.newDocumentBuilder()
            val kmehrDocument: Document = builder.parse(ByteArrayInputStream(xmlDocument))
            val propertyHandler =
                PropertyHandler.getInstance()
            val xpath: XPath = XPathFactory.newInstance().newXPath()
            val xpathStr1 = propertyHandler.getProperty("referenceSourceVersion.xpath1")
            val referenceSourceVersionNodeList1: NodeList =
                xpath.evaluate(xpathStr1, kmehrDocument, XPathConstants.NODESET) as NodeList
            var referenceSourceVersionPart1 = ""
            if (referenceSourceVersionNodeList1.item(0) != null) {
                referenceSourceVersionPart1 = referenceSourceVersionNodeList1.item(0).textContent
            }
            val xpathStr2 = propertyHandler.getProperty("referenceSourceVersion.xpath2")
            val referenceSourceVersionNodeList2: NodeList =
                xpath.evaluate(xpathStr2, kmehrDocument, XPathConstants.NODESET) as NodeList
            var referenceSourceVersionPart2 = ""
            if (referenceSourceVersionNodeList2.item(0) != null) {
                referenceSourceVersionPart2 =
                    referenceSourceVersionPart2 + referenceSourceVersionNodeList2.item(0).textContent
            }
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

    @Throws(IntegrationModuleException::class)
    protected fun checkStatus(response: StatusResponseType) {
        if (EHEALTH_SUCCESS_CODE_100 != response.status.statusCode.value && EHEALTH_SUCCESS_CODE_200 != response.status.statusCode.value && EHEALTH_SUCCESS_URN != response.status.statusCode.value) {
            log.error("Error Status received : " + response.status.statusCode.value)
            throw IntegrationModuleException(getLocalisedMsg(response.status))
        }
    }

    private fun getLocalisedMsg(status: Status): String {
        return if (status.statusMessage.isNotBlank()) status.statusMessage else status.statusCode.value
    }

}
