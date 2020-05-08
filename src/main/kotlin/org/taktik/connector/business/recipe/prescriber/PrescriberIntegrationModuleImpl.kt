/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.connector.business.recipe.prescriber

import be.fgov.ehealth.commons.core.v1.IdentifierType
import be.fgov.ehealth.commons.core.v1.StatusType
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.recipe.core.v1.CreatePrescriptionAdministrativeInformationType
import be.fgov.ehealth.recipe.core.v1.PrescriberServiceAdministrativeInformationType
import be.fgov.ehealth.recipe.core.v1.SecuredContentType
import be.fgov.ehealth.recipe.core.v1.SendNotificationAdministrativeInformationType
import be.fgov.ehealth.recipe.protocol.v1.*
import be.recipe.services.prescriber.*
import com.sun.xml.internal.ws.client.ClientTransportException
import org.apache.commons.lang3.StringUtils
import org.bouncycastle.util.encoders.Base64
import org.slf4j.LoggerFactory
import org.taktik.connector.business.recipe.common.AbstractIntegrationModule
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceImpl
import org.taktik.connector.business.recipe.utils.KmehrHelper
import org.taktik.connector.business.recipeprojects.core.domain.IdentifierTypes
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.utils.*
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.kgss.impl.KgssServiceImpl
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.impl.RecipeServiceImpl
import java.io.ByteArrayInputStream
import java.security.KeyStore
import java.util.*

open class PrescriberIntegrationModuleImpl(val stsService: STSService, keyDepotService: KeyDepotService) : AbstractIntegrationModule(keyDepotService), PrescriberIntegrationModule {
    private val log = LoggerFactory.getLogger(PrescriberIntegrationModuleImpl::class.java)
    private val keyCache = HashMap<String, KeyResult>()
    /**
     * The prescription cache. Key is the RID, Value is the Patient ID.
     */
    private val prescriptionCache = HashMap<String, String>()
    private val kgssService = KgssServiceImpl()
    private val recipePrescriberService = RecipePrescriberServiceImpl()
    private val kmehrHelper = KmehrHelper(Properties().apply { load(RecipeServiceImpl::class.java.getResourceAsStream("/org/taktik/connector/business/recipe/validation.properties")) })

    /**
     * Gets the new key.
     *
     *
     * @param nihii
     * @param patientId        the patient id
     * @param prescriptionType the prescription type
     * @return the new key
     * @throws IntegrationModuleException the integration module exception
     */
    @Throws(IntegrationModuleException::class)
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
    override fun ping(samlToken: SAMLToken, credential: KeyStoreCredential) {
        var response: AliveCheckResponse? = null
        try {
            response = recipePrescriberService.aliveCheck(samlToken, credential, AliveCheckRequest())
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
        }

        log.info("Ping response : " + response.aliveCheckResult)
        checkStatus(response)
    }

    /**
     * Creates the prescription.
     *
     *
     *
     *
     * @param samlToken
     * @param credential
     * @param nihii
     * @param feedbackRequested the feedback requested
     * @param patientId         the patient id
     * @param prescription      the prescription
     * @param prescriptionType  the prescription type
     * @return the string
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    override fun createPrescription(keystore: KeyStore, samlToken: SAMLToken, passPhrase: String, credential: KeyStoreCredential, nihii: String, feedbackRequested: Boolean, patientId: String, prescription: ByteArray, prescriptionType: String): String? {
        if (StringUtils.isBlank(patientId)) {
            throw IntegrationModuleException("Patient ID is 0.")
        }

        try {
            // init helper
            val helper = MarshallerHelper(CreatePrescriptionResult::class.java, CreatePrescriptionParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // create sealed prescription
            val key = getNewKey(credential, nihii, patientId, prescriptionType)
            val message = getCrypto(credential).seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, null, KeyResult(key?.secretKey, key?.keyId), IOUtils.compress(prescription))

            // create sealed content
            val params = CreatePrescriptionParam()
            params.patientId = patientId
            params.setFeedbackRequested(feedbackRequested)
            params.prescription = message
            params.prescriptionType = prescriptionType
            params.symmKey = symmKey!!.encoded
            params.keyId = key!!.keyId
            params.prescriberId = nihii

            // create request
            val request = CreatePrescriptionRequest()
            request.securedCreatePrescriptionRequest =
                createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(params)))

            // create administrative info
            val info = CreatePrescriptionAdministrativeInformationType()
            request.administrativeInformation = info
            info.keyIdentifier = Base64.decode(key.keyId)
            // info.setKeyIdentifier(key.getKeyId().getBytes());
            info.prescriptionType = prescriptionType
            info.patientIdentifier = createIdentifierType(patientId, IdentifierTypes.SSIN.name)
            info.prescriberIdentifier = createIdentifierType(nihii, IdentifierTypes.NIHII11.name)

            // WS call
            val response = recipePrescriberService.createPrescription(samlToken, credential, request)
            checkStatus(response)

            // unseal response
            val result = helper.unsealWithSymmKey(response.securedCreatePrescriptionResponse.securedContent, symmKey)

            // update the local cache
            setPatientId(result.rid, patientId)

            return result.rid
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

        return null
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
            val etkRecipes = etkHelper!!.recipe_ETK

            // create params
            val params = RevokePrescriptionParam()
            params.rid = rid
            params.reason = reason
            params.prescriberId = nihii

            // create request
            val request = RevokePrescriptionRequest()
            request.securedRevokePrescriptionRequest = createSecuredContentType(sealRequest(getCrypto(credential),etkRecipes[0], helper.toXMLByteArray(params)))

            // Admin Info for eHealth
            val info = PrescriberServiceAdministrativeInformationType()
            info.patientIdentifier = createIdentifierType(getPatientId(rid), IdentifierTypes.SSIN.name)

            request.administrativeInformation = info

            // call WS
            try {
                checkStatus(recipePrescriberService.revokePrescription(samlToken, credential, request))
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
            }

        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

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
        keystore: KeyStore,
        passPhrase: String,
        nihii: String,
        rid: String
    ): GetPrescriptionForPrescriberResult? {
        validateRid(rid)
        try {

            // init helper
            val helper = MarshallerHelper(GetPrescriptionForPrescriberResult::class.java, GetPrescriptionForPrescriberParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // create sealed request
            val param = GetPrescriptionForPrescriberParam()
            param.rid = rid
            param.symmKey = symmKey!!.encoded
            param.prescriberId = nihii

            // build request
            val request = GetPrescriptionForPrescriberRequest()
            request.securedGetPrescriptionForPrescriberRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param)))

            val info = PrescriberServiceAdministrativeInformationType()

            // id added
            info.patientIdentifier = createIdentifierType(getPatientId(rid), IdentifierTypes.SSIN.name)
            request.administrativeInformation = info

            // call sealed WS
            var response: GetPrescriptionForPrescriberResponse? = null

            try {
                response = recipePrescriberService.getPrescriptionForPrescriber(samlToken, credential, request)
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
            }

            checkStatus(response)

            // unseal WS response
            val result = helper.unsealWithSymmKey(response.securedGetPrescriptionForPrescriberResponse.securedContent, symmKey)
            getKeyFromKgss(credential, samlToken, result.encryptionKeyId, stsService.getHolderOfKeysEtk(credential, nihii)!!.encoded)?.let { key ->
                result.prescription = IOUtils.decompress(getCrypto(credential).unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, key, result.prescription).contentAsByte)
            }

            // update the local cache
            setPatientId(result.rid, result.patientId)

            return result
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

        return null
    }

    /**
     * List open prescription.
     *
     *
     *
     *
     * @param samlToken
     * @param credential
     * @param nihii
     * @param patientId
     * @return the list for a geiven patient.
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    override fun listOpenPrescription(samlToken: SAMLToken, credential: KeyStoreCredential, nihii: String, patientId: String?): List<String> {
        try {
            // init helper
            val helper = MarshallerHelper(GetListOpenPrescriptionResult::class.java, GetListOpenPrescriptionParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // create param
            val param = GetListOpenPrescriptionParam()
            param.symmKey = symmKey!!.encoded
            param.prescriberId = nihii
            param.patientId = patientId

            // create request
            val request = ListOpenPrescriptionsRequest()
            request.securedListOpenPrescriptionsRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param)))

            // call sealed WS
            var response: ListOpenPrescriptionsResponse? = null
            try {
                response = recipePrescriberService.listOpenPrescriptions(samlToken, credential, request)
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
            }

            checkStatus(response)

            // unseal WS response
            val result = helper.unsealWithSymmKey(response.securedListOpenPrescriptionsResponse.securedContent, symmKey)

            return if (result.prescriptions == null) ArrayList() else result.prescriptions

        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

        return listOf()
    }

    /**
     * List open prescription.
     *
     * @return the list
     * @throws IntegrationModuleException the integration module exception
     * @param samlToken
     * @param credential
     * @param nihii
     */

    @Throws(IntegrationModuleException::class)
    override fun listOpenPrescription(samlToken: SAMLToken, credential: KeyStoreCredential, nihii: String): List<String> {
        return listOpenPrescription(samlToken, credential, nihii, null)
    }

    /**
     * Address prescription.
     *
     *
     *
     *
     * @param samlToken
     * @param credential
     * @param nihii
     * @param notificationText the notification text
     * @param patientId        the patient id
     * @param executorId       the executor id
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    override fun sendNotification(samlToken: SAMLToken, credential: KeyStoreCredential, nihii: String, notificationText: ByteArray, patientId: String, executorId: String) {
        try {
            kmehrHelper.assertValidNotification(ByteArrayInputStream(notificationText))

            // init helper
            val helper = MarshallerHelper(Any::class.java, SendNotificationParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // get recipient etk
            val etkRecipients = etkHelper!!.getEtks(KgssIdentifierType.NIHII_PHARMACY, executorId)

            val notificationZip = IOUtils.compress(notificationText)

            for (i in etkRecipients.indices) {
                val etkRecipient = etkRecipients[0]

                val notificationSealed = sealNotification(getCrypto(credential),etkRecipient, notificationZip)

                // create param
                val param = SendNotificationParam()
                param.content = notificationSealed
                param.executorId = executorId
                param.prescriberId = nihii
                param.patientId = patientId

                // create request
                val request = SendNotificationRequest()
                request.securedSendNotificationRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param)))
                val info = SendNotificationAdministrativeInformationType()
                info.executorIdentifier = createIdentifierType(executorId, IdentifierTypes.SSIN.name)
                info.patientIdentifier = createIdentifierType(patientId, IdentifierTypes.SSIN.name)
                request.administrativeInformation = info

                // call sealed WS
                try {
                    checkStatus(recipePrescriberService.sendNotification(samlToken, credential, request))
                } catch (cte: ClientTransportException) {
                    throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
                }

            }
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

    }

    /**
     * Update feedback flag.
     *
     *
     *
     *
     * @param samlToken
     * @param credential
     * @param nihii
     * @param rid             the rid
     * @param feedbackAllowed the feedback allowed
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    override fun updateFeedbackFlag(samlToken: SAMLToken, credential: KeyStoreCredential, nihii: String, rid: String, feedbackAllowed: Boolean) {
        validateRid(rid)
        try {

            // init helper
            val helper = MarshallerHelper(Any::class.java, UpdateFeedbackFlagParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // create param
            val param = UpdateFeedbackFlagParam()
            param.isAllowFeedback = feedbackAllowed
            param.rid = rid
            param.prescriberId = nihii

            val request = UpdateFeedbackFlagRequest()
            request.securedUpdateFeedbackFlagRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param)))
            val info = PrescriberServiceAdministrativeInformationType()

            // Admin Info for eHealth
            info.patientIdentifier = createIdentifierType(getPatientId(rid), IdentifierTypes.SSIN.name)
            request.administrativeInformation = info

            // call sealed WS
            try {
                checkStatus(recipePrescriberService.updateFeedbackFlag(samlToken, credential, request))
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
            }

        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

    }


    @Throws(IntegrationModuleException::class)
    override fun listFeedback(samlToken: SAMLToken, credential: KeyStoreCredential, nihii: String, readFlag: Boolean): List<ListFeedbackItem> {

        try {
            // init helper
            val helper = MarshallerHelper(ListFeedbacksResult::class.java, ListFeedbacksParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // create param
            val param = ListFeedbacksParam()
            param.readFlag = readFlag
            param.symmKey = symmKey!!.encoded
            param.prescriberId = nihii

            // create request
            val request = ListFeedbacksRequest()
            request.securedListFeedbacksRequest = createSecuredContentType(sealRequest(getCrypto(credential), etkRecipes[0], helper.toXMLByteArray(param)))

            // call sealed WS
            var response: ListFeedbacksResponse? = null

            try {
                response = recipePrescriberService.listFeedbacks(samlToken, credential, request)
            } catch (cte: ClientTransportException) {
                throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
            }

            checkStatus(response)

            // unseal WS response
            val feedbacks = helper.unsealWithSymmKey(response.securedListFeedbacksResponse.securedContent, symmKey).feedbacks

            for (i in feedbacks.indices) {
                val item = org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem(feedbacks[i])
                item.content = try { unsealFeedback(getCrypto(credential), item.content)?.let {IOUtils.decompress(it)} ?: item.content } catch (t: Throwable) {item.linkedException = t; item.content}

                feedbacks[i] = item
            }
            return feedbacks

        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

        return listOf()
    }

    private fun createSecuredContentType(content: ByteArray): SecuredContentType {
        val secured = SecuredContentType()
        secured.securedContent = content
        return secured
    }

    @Throws(IntegrationModuleException::class)
    private fun checkStatus(response: ResponseType) {
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

    private fun createIdentifierType(id: String, type: String): IdentifierType {
        val ident = IdentifierType()
        ident.id = id + ""
        ident.type = type
        return ident
    }

    private fun getPatientId(rid: String): String {
        return prescriptionCache[rid] ?: "72081061175"
    }

    private fun setPatientId(rid: String, patientId: String) {
        prescriptionCache.put(rid, patientId)
    }


    @Throws(IntegrationModuleException::class)
    protected fun unsealFeedback(crypto: Crypto, message: ByteArray?): ByteArray? {
        return message?.let { unsealNotiffeed(crypto, it) }
    }


    @Throws(IntegrationModuleException::class)
    protected fun getNewKeyFromKgss(credential: KeyStoreCredential, prescriptionType: String, prescriberId: String, executorId: String?, patientId: String, myEtk: ByteArray): KeyResult? {
        val etkKgss = etkHelper!!.kgsS_ETK[0]
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

    private fun getCrypto(credential: KeyStoreCredential) : Crypto {
        val hokPrivateKeys = KeyManager.getDecryptionKeys(credential.keyStore, credential.password)
        return CryptoFactory.getCrypto(credential, hokPrivateKeys)
    }

}
