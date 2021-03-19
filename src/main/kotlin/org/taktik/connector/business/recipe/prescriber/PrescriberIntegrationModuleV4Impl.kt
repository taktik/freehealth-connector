package org.taktik.connector.business.recipe.prescriber


import be.fgov.ehealth.commons.core.v2.Status
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.recipe.core.v4.CreatePrescriptionAdministrativeInformationType
import be.fgov.ehealth.recipe.core.v4.SecuredContentType
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksRequest
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationRequest
import be.recipe.services.prescriber.CreatePrescriptionParam
import be.recipe.services.prescriber.CreatePrescriptionResult
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult
import be.recipe.services.prescriber.GetPrescriptionStatusParam
import be.recipe.services.prescriber.GetPrescriptionStatusResult
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
import be.recipe.services.prescriber.UpdateFeedbackFlagParam
import be.recipe.services.prescriber.UpdateFeedbackFlagResult
import be.recipe.services.prescriber.ValidationPropertiesParam
import be.recipe.services.prescriber.ValidationPropertiesResult
import com.sun.xml.internal.ws.client.ClientTransportException
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.taktik.connector.business.recipe.common.AbstractIntegrationModule
import org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceV4Impl
import org.taktik.connector.business.recipe.utils.Exceptionutils
import org.taktik.connector.business.recipe.utils.RidValidator
import org.taktik.connector.business.recipe.utils.ValidationUtils
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
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


class PrescriberIntegrationModuleV4Impl(val stsService: STSService, keyDepotService: KeyDepotService) :
    AbstractIntegrationModule(keyDepotService), PrescriberIntegrationModuleV4 {
    private val log = LoggerFactory.getLogger(PrescriberIntegrationModuleV4Impl::class.java)
    private val recipePrescriberServiceV4 = RecipePrescriberServiceV4Impl()
    private val keyCache = HashMap<String, KeyResult>()

    private fun getNewKey(
        credential: KeyStoreCredential,
        nihii: String,
        patientId: String,
        prescriptionType: String
    ): KeyResult? {
        val key: KeyResult?

        val cacheId = "($patientId#$prescriptionType)"
        if (keyCache.containsKey(cacheId)) {
            key = keyCache[cacheId]
            keyCache.remove(cacheId)
        } else {
            key = getNewKeyFromKgss(
                credential,
                prescriptionType,
                nihii,
                patientId,
                stsService.getHolderOfKeysEtk(credential, nihii)!!.encoded
            )
        }
        return key
    }

    /**
     * Gets the prescription.
     *
     *
     *
     *
     * @param samlToken
     * @param credential
     * @param nihii
     * @param rid the rid
     * @return the prescription
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    override fun getPrescription(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        vendorName: String?,
        packageVersion: String?
    ): GetPrescriptionForPrescriberResult = try {
        validateRid(rid)
        val helper = MarshallerHelper(
            GetPrescriptionForPrescriberResult::class.java,
            GetPrescriptionForPrescriberParam::class.java
        )
        val param = GetPrescriptionForPrescriberParam().apply {
            this.rid = rid
            this.symmKey = recipeSymmKey.encoded
            this.prescriberId = nihii
        }
        val request = GetPrescriptionRequest().apply {
            this.securedGetPrescriptionRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0],
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }

        // call sealed WS
        try {
            recipePrescriberServiceV4.getPrescriptionForPrescriber(samlToken, credential, request).let { response ->
                helper.unsealWithSymmKey(response.securedGetPrescriptionResponse.securedContent, recipeSymmKey)?.also {
                    checkStatus(response)
                }
            }
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
        }?.also {
            getKeyFromKgss(
                credential,
                samlToken,
                it.encryptionKeyId,
                stsService.getHolderOfKeysEtk(credential, nihii)!!.encoded
            )?.let { key ->
                // unseal WS response
                it.prescription = IOUtils.decompress(
                    getCrypto(credential).unseal(
                        Crypto.SigningPolicySelector.WITH_NON_REPUDIATION,
                        key,
                        it.prescription
                    ).contentAsByte
                )
            }
        } ?: throw IntegrationModuleException(I18nHelper.getLabel("error.data.unseal"))
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }

    override fun createPrescription(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        patientSsin: String,
        nihii: String,
        feedbackRequested: Boolean,
        prescriptionType: String,
        expirationDate: LocalDateTime,
        prescription: ByteArray,
        visibility: String?,
        vendorName: String?,
        packageVersion: String?
    ): String = try {
        ValidationUtils.validatePatientIdNotBlank(patientSsin)
        ValidationUtils.validatePatientId(patientSsin)
        ValidationUtils.validateVisi(visibility, false)

        val expDateAsString = expirationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        ValidationUtils.validateExpirationDate(expDateAsString)
        //performValidation(prescription, prescriptionType, expDateAsString)
        val helper = MarshallerHelper(CreatePrescriptionResult::class.java, CreatePrescriptionParam::class.java)
        val key: KeyResult =
            getNewKey(credential, nihii, patientSsin, prescriptionType) ?: throw TechnicalConnectorException(
                TechnicalConnectorExceptionValues.ERROR_KGSS,
                "Cannot obtain key from KGSS for patient $patientSsin"
            )
        val message = getCrypto(credential).seal(
            Crypto.SigningPolicySelector.WITH_NON_REPUDIATION,
            null,
            KeyResult(key.secretKey, key.keyId),
            IOUtils.compress(prescription)
        )

        val param = CreatePrescriptionParam().apply {
            this.prescription = message
            this.prescriptionType = prescriptionType
            this.isFeedbackRequested = feedbackRequested
            this.symmKey = recipeSymmKey.encoded
            this.keyId = key.keyId
            this.patientId = patientSsin
            this.expirationDate = expDateAsString
            this.vision = visibility
            this.prescriberId = nihii
        }
        val request = CreatePrescriptionRequest().apply {
            this.securedCreatePrescriptionRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0] as EncryptionToken,
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.id = "id" + UUID.randomUUID().toString()
            this.issueInstant = DateTime.now()
            this.administrativeInformation = CreatePrescriptionAdministrativeInformationType().apply {
                this.keyIdentifier = key.keyId.toByteArray()
                this.prescriptionVersion = extractPrescriptionVersionFromKmehr(prescription)
                this.referenceSourceVersion = extractReferenceSourceVersionFromKmehr(prescription)
                this.prescriptionType = prescriptionType
            }
        }
        log.debug("Recip-e v4 prescription is {}", prescription.toString(Charsets.UTF_8))
        log.debug("Recip-e v4 message is {}", ConnectorXmlUtils.toString(param))
        log.debug("Recip-e v4 request is {}", ConnectorXmlUtils.toString(request))

        recipePrescriberServiceV4.createPrescription(samlToken, credential, request).let { response ->
            helper.unsealWithSymmKey(response.securedCreatePrescriptionResponse.securedContent, recipeSymmKey).also {
                checkStatus(response)
            }
        }.rid
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }

    override fun getPrescriptionStatus(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        vendorName: String?,
        packageVersion: String?
    ): GetPrescriptionStatusResult = try {
        RidValidator.validateRid(rid)
        val helper =
            MarshallerHelper(GetPrescriptionStatusParam::class.java, GetPrescriptionStatusParam::class.java)
        val etkRecipes: List<*> = etkHelper.recipe_ETK

        val param = GetPrescriptionStatusParam().apply {
            this.rid = rid
            this.prescriberId = nihii
            this.symmKey = recipeSymmKey.encoded
        }
        val request = GetPrescriptionStatusRequest().apply {
            this.securedGetPrescriptionStatusRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkRecipes[0] as EncryptionToken,
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }
        try {
            recipePrescriberServiceV4.getPrescriptionStatus(samlToken, credential, request).let { response ->
                MarshallerHelper(
                    GetPrescriptionStatusResult::class.java,
                    Any::class.java
                ).unsealWithSymmKey(response.securedGetPrescriptionStatusResponse.securedContent, recipeSymmKey)
                    .also { checkStatus(it) }
            }
        } catch (ex: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), ex)
        }
    } catch (err: Throwable) {
        Exceptionutils.errorHandler(err)
    }

    override fun listFeedback(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        readFlag: Boolean,
        vendorName: String?,
        packageVersion: String?
    ): List<ListFeedbackItem> = try {
        val helper = MarshallerHelper(ListFeedbacksResult::class.java, ListFeedbacksParam::class.java)
        val etkRecipes = etkHelper.recipe_ETK
        val param = ListFeedbacksParam().apply {
            this.readFlag = readFlag
            this.symmKey = recipeSymmKey.encoded
        }
        val request = ListFeedbacksRequest().apply {
            this.securedListFeedbacksRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkRecipes[0],
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }
        try {
            recipePrescriberServiceV4.listFeedbacks(samlToken, credential, request).let { response ->
                helper.unsealWithSymmKey(response.securedListFeedbacksResponse.securedContent, recipeSymmKey)
                    .also { checkStatus(it) }
            }
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
        }.feedbacks.map {
            ListFeedbackItem(it).apply {
                setContent(try {
                    unsealFeedback(getCrypto(credential), it.content)?.let { it -> IOUtils.decompress(it) } ?: it.content
                } catch (t: Throwable) {
                    this.linkedException = t; it.content
                })
            }
        }
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }

    override fun listRidsHistory(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        patientSsin: String,
        vendorName: String?,
        packageVersion: String?
    ): ListRidsHistoryResult = try {
        ValidationUtils.validatePatientId(patientSsin)
        val helper = MarshallerHelper(ListRidsHistoryParam::class.java, ListRidsHistoryParam::class.java)
        val param = ListRidsHistoryParam().apply {
            this.patientId = patientSsin
            this.symmKey = recipeSymmKey.encoded
        }
        param.symmKey = recipeSymmKey.encoded
        val listRidsHistory = ListRidsHistoryRequest().apply {
            this.securedListRidsHistoryRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0],
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.id = "id" + UUID.randomUUID().toString()
            this.issueInstant = DateTime()
        }

        try {
            recipePrescriberServiceV4.listRidsHistory(samlToken, credential, listRidsHistory).let { response ->
                MarshallerHelper(
                    ListRidsHistoryResult::class.java,
                    Any::class.java
                ).unsealWithSymmKey(response.securedListRidsHistoryResponse.securedContent, recipeSymmKey)
                    .also { checkStatus(it) }
            }
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte)
        }
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }

    override fun listOpenRids(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        patientSsin: String,
        vendorName: String?,
        packageVersion: String?
    ): ListOpenRidsResult = try {
        val helper = MarshallerHelper(ListOpenRidsParam::class.java, ListOpenRidsParam::class.java)

        val param = ListOpenRidsParam().apply {
            this.patientId = patientSsin
            this.symmKey = recipeSymmKey.encoded
        }
        val request = ListOpenRidsRequest().apply {
            this.securedListOpenRidsRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0] as EncryptionToken,
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }
        try {
            recipePrescriberServiceV4.listOpenRids(samlToken, credential, request).let { response ->
                MarshallerHelper(
                    ListOpenRidsResult::class.java,
                    Any::class.java
                ).unsealWithSymmKey(response.securedListOpenRidsResponse.securedContent, recipeSymmKey)
                    .also { checkStatus(it) }
            }
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte)
        }
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }

    override fun setVision(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        rid: String,
        vision: String,
        vendorName: String?,
        packageVersion: String?
    ): PutVisionResult = try {
        RidValidator.validateRid(rid)
        ValidationUtils.validateVisi(vision, false)

        val helper = MarshallerHelper(PutVisionParam::class.java, PutVisionParam::class.java)
        val param = PutVisionParam().apply {
            this.rid = rid
            this.vision = vision
            this.symmKey = recipeSymmKey.encoded
        }

        val request = PutVisionForPrescriberRequest().apply {
            this.securedPutVisionForPrescriberRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0] as EncryptionToken,
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }

        recipePrescriberServiceV4.putVisionForPrescriber(samlToken, credential, request).let { response ->
            MarshallerHelper(PutVisionResult::class.java, Any::class.java).unsealWithSymmKey(
                response.securedPutVisionForPrescriberResponse.securedContent,
                recipeSymmKey
            ).also { checkStatus(it) }
        }
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }

    fun updateFeedbackFlag(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        feedbackAllowed: Boolean,
        vendorName: String? = null,
        packageVersion: String? = null
    ): UpdateFeedbackFlagResult = try {
        RidValidator.validateRid(rid)
        val helper = MarshallerHelper(Any::class.java, UpdateFeedbackFlagParam::class.java)

        val param = UpdateFeedbackFlagParam().apply {
            this.isAllowFeedback = feedbackAllowed
            this.rid = rid
            this.prescriberId = nihii
            this.symmKey = recipeSymmKey.encoded
        }
        val request = PutFeedbackFlagRequest().apply {
            this.securedPutFeedbackFlagRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0] as EncryptionToken,
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }
        recipePrescriberServiceV4.putFeedbackFlag(samlToken, credential, request).let { response ->
            MarshallerHelper(
                UpdateFeedbackFlagResult::class.java,
                UpdateFeedbackFlagResult::class.java
            ).unsealWithSymmKey(response.securedPutFeedbackFlagResponse.securedContent, recipeSymmKey).also { checkStatus(it) }
        }
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }


    @Throws(IntegrationModuleException::class)
    override fun revokePrescription(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        reason: String,
        vendorName: String?,
        packageVersion: String?
    ): RevokePrescriptionResult = try {
        validateRid(rid)
        val helper = MarshallerHelper(Any::class.java, RevokePrescriptionParam::class.java)

        val params = RevokePrescriptionParam().apply {
            this.rid = rid
            this.reason = reason
            this.prescriberId = nihii
            this.symmKey = recipeSymmKey.encoded
        }
        val request = RevokePrescriptionRequest().apply {
            this.securedRevokePrescriptionRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0],
                    helper.toXMLByteArray(params)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }
        try {
            recipePrescriberServiceV4.revokePrescription(samlToken, credential, request).let { response ->
                MarshallerHelper(RevokePrescriptionResult::class.java, Any::class.java).unsealWithSymmKey(
                    response.securedRevokePrescriptionResponse.securedContent,
                    recipeSymmKey
                ).also { checkStatus(it) }
            }
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
        }
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }

    override fun getValidationProperties(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        param: ValidationPropertiesParam,
        vendorName: String?,
        packageVersion: String?
    ): ValidationPropertiesResult = try {
        val helper = MarshallerHelper(ValidationPropertiesParam::class.java, ValidationPropertiesParam::class.java)
        param.symmKey = recipeSymmKey.encoded

        val request = GetValidationPropertiesRequest().apply {
            this.securedGetValidationPropertiesRequest = createSecuredContentType(
                sealRequest(
                    getCrypto(credential),
                    etkHelper.recipe_ETK[0] as EncryptionToken,
                    helper.toXMLByteArray(param)
                )
            )
            this.programId = "${vendorName ?: "freehealth-connector"}/${packageVersion ?: ""}"
            this.issueInstant = DateTime()
            this.id = "id" + UUID.randomUUID().toString()
        }
        recipePrescriberServiceV4.getValidationProperties(samlToken, credential, request).let { response ->
            MarshallerHelper(ValidationPropertiesResult::class.java, Any::class.java).unsealWithSymmKey(
                response.securedGetValidationPropertiesResponse.securedContent,
                recipeSymmKey
            ).also { checkStatus(it) }
        }
    } catch (t: Throwable) {
        Exceptionutils.errorHandler(t)
    }


    private fun createSecuredContentType(content: ByteArray): SecuredContentType {
        val secured = SecuredContentType()
        secured.securedContent = content
        return secured
    }


    @Throws(IntegrationModuleException::class)
    private fun extractPrescriptionVersionFromKmehr(xmlDocument: ByteArray) = try {
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
    } catch (var10: SAXException) {
        Exceptionutils.errorHandler(var10)
    } catch (var10: IOException) {
        Exceptionutils.errorHandler(var10)
    } catch (var10: XPathExpressionException) {
        Exceptionutils.errorHandler(var10)
    }

    @Throws(IntegrationModuleException::class)
    private fun extractReferenceSourceVersionFromKmehr(xmlDocument: ByteArray) = try {
        val factory: DocumentBuilderFactory =
            DocumentBuilderFactory.newInstance().apply { isNamespaceAware = false }
        val builder: DocumentBuilder = factory.newDocumentBuilder()
        val kmehrDocument: Document = builder.parse(ByteArrayInputStream(xmlDocument))
        val propertyHandler = PropertyHandler.getInstance()
        val xpath: XPath = XPathFactory.newInstance().newXPath()
        val xpathStr1 = propertyHandler.getProperty("referenceSourceVersion.xpath1")
        val referenceSourceVersionNodeList1: NodeList =
            xpath.evaluate(xpathStr1, kmehrDocument, XPathConstants.NODESET) as NodeList
        val referenceSourceVersionPart1 = referenceSourceVersionNodeList1.item(0)?.textContent ?: ""
        val xpathStr2 = propertyHandler.getProperty("referenceSourceVersion.xpath2")
        val referenceSourceVersionNodeList2: NodeList =
            xpath.evaluate(xpathStr2, kmehrDocument, XPathConstants.NODESET) as NodeList
        val referenceSourceVersionPart2 = referenceSourceVersionNodeList2.item(0)?.textContent ?: ""
        val referenceSourceVersion = "$referenceSourceVersionPart1:$referenceSourceVersionPart2"
        if (StringUtils.isNotBlank(referenceSourceVersion)) referenceSourceVersion else "Unknown"
    } catch (var14: ParserConfigurationException) {
        Exceptionutils.errorHandler(var14)
    } catch (var14: SAXException) {
        Exceptionutils.errorHandler(var14)
    } catch (var14: IOException) {
        Exceptionutils.errorHandler(var14)
    } catch (var14: XPathExpressionException) {
        Exceptionutils.errorHandler(var14)
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
                param.symmKey = this.recipeSymmKey.encoded
                val request = SendNotificationRequest()
                request.securedSendNotificationRequest = this.createSecuredContentType(
                    this.sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param))
                )
                request.programId = PropertyHandler.getInstance().getProperty("programIdentification")
                request.issueInstant = DateTime()
                request.id = "id" + UUID.randomUUID().toString()
                try {
                    val response = recipePrescriberServiceV4.sendNotification(samlToken, credential, request)
                    val marshaller =
                        MarshallerHelper(SendNotificationResult::class.java, SendNotificationResult::class.java)
                    this.checkStatus(
                        marshaller.unsealWithSymmKey(
                            response.securedSendNotificationResponse.securedContent,
                            this.recipeSymmKey
                        )
                    )
                } catch (cte: ClientTransportException) {
                    throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
                }
            }
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }
    }

    private fun getLocalisedMsg(status: Status): String {
        return if (status.statusMessage.isNotBlank()) status.statusMessage else status.statusCode.value
    }

    private fun checkStatus(response: StatusResponseType) {
        if (EHEALTH_SUCCESS_CODE_100 != response.status.statusCode.value && EHEALTH_SUCCESS_CODE_200 != response.status.statusCode.value && EHEALTH_SUCCESS_URN != response.status.statusCode.value) {
            log.error("Error Status received : " + response.status.statusCode.value)
            throw IntegrationModuleException(getLocalisedMsg(response.status))
        }
    }

    private fun checkStatus(response: be.recipe.services.core.ResponseType) {
        if (EHEALTH_SUCCESS_CODE_100 != response.status.code && EHEALTH_SUCCESS_CODE_200 != response.status.code) {
            log.error("Error Status received : " + response.status.code)
            throw IntegrationModuleException(getLocalisedMsg(response.status))
        }
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

    @Throws(IntegrationModuleException::class)
    private fun unsealFeedback(crypto: Crypto, message: ByteArray?): ByteArray? {
        return message?.let { unsealNotiffeed(crypto, it) }
    }

    @Throws(IntegrationModuleException::class)
    private fun getNewKeyFromKgss(
        credential: KeyStoreCredential,
        prescriptionType: String,
        prescriberId: String,
        patientId: String,
        myEtk: ByteArray
    ): KeyResult? {
        val etkKgss = etkHelper.kgsS_ETK[0]
        val credentialTypes = propertyHandler.getMatchingProperties("kgss.createPrescription.ACL.$prescriptionType")
            ?.map { it.replace(Regex("\\{saml.quality}"), credential.quality) }

        return try {
            kgssService.retrieveNewKey(
                credential,
                etkKgss.encoded,
                credentialTypes,
                prescriberId,
                null,
                patientId,
                myEtk
            )
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }
    }


    @Throws(IntegrationModuleException::class)
    private fun sealNotification(
        crypto: Crypto,
        paramEncryptionToken: EncryptionToken,
        paramArrayOfByte: ByteArray
    ): ByteArray {
        return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, paramEncryptionToken, paramArrayOfByte)
    }

    private fun getCrypto(credential: KeyStoreCredential): Crypto {
        val hokPrivateKeys = KeyManager.getDecryptionKeys(credential.keyStore, credential.password)
        return CryptoFactory.getCrypto(credential, hokPrivateKeys)
    }

}
