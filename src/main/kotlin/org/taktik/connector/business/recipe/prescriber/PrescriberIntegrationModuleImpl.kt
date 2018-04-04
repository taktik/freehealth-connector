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
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken
import be.fgov.ehealth.recipe.core.v1.CreatePrescriptionAdministrativeInformationType
import be.fgov.ehealth.recipe.core.v1.PrescriberServiceAdministrativeInformationType
import be.fgov.ehealth.recipe.core.v1.SecuredContentType
import be.fgov.ehealth.recipe.core.v1.SendNotificationAdministrativeInformationType
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckRequest
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberRequest
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberResponse
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksRequest
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksResponse
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsRequest
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsResponse
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationRequest
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagRequest
import be.recipe.services.prescriber.CreatePrescriptionParam
import be.recipe.services.prescriber.CreatePrescriptionResult
import be.recipe.services.prescriber.GetListOpenPrescriptionParam
import be.recipe.services.prescriber.GetListOpenPrescriptionResult
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult
import be.recipe.services.prescriber.ListFeedbackItem
import be.recipe.services.prescriber.ListFeedbacksParam
import be.recipe.services.prescriber.ListFeedbacksResult
import be.recipe.services.prescriber.RevokePrescriptionParam
import be.recipe.services.prescriber.SendNotificationParam
import be.recipe.services.prescriber.UpdateFeedbackFlagParam
import com.sun.xml.internal.ws.client.ClientTransportException
import org.apache.commons.lang3.StringUtils
import org.apache.log4j.Logger
import org.bouncycastle.util.encoders.Base64
import org.taktik.connector.business.recipe.common.AbstractIntegrationModule
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceImpl
import org.taktik.connector.business.recipe.utils.KmehrHelper
import org.taktik.connector.business.recipeprojects.common.utils.ValidationUtils
import org.taktik.connector.business.recipeprojects.core.domain.IdentifierTypes
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils
import org.taktik.connector.business.recipeprojects.core.utils.MarshallerHelper
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.kgss.impl.KgssServiceImpl
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.SAMLToken

import java.io.ByteArrayInputStream
import java.util.ArrayList
import java.util.HashMap

class PrescriberIntegrationModuleImpl @Throws(IntegrationModuleException::class)
constructor() : AbstractIntegrationModule(), PrescriberIntegrationModule {

    private val keyCache = HashMap<String, KeyResult>()
    /**
     * The prescription cache. Key is the RID, Value is the Patient ID.
     */
    private val prescriptionCache = HashMap<String, String>()
    private val kgssService = KgssServiceImpl()
    private val recipePrescriberService = RecipePrescriberServiceImpl()
    private val kmehrHelper = KmehrHelper(PropertyHandler.getInstance().properties)


    init {
        LOG.info("*************** Prescriber System module init correctly *******************")
    }

    /**
     * Prepare create prescription.
     *
     *
     * @param nihii
     * @param patientId the patient id
     * @throws IntegrationModuleException
     */
    @Throws(IntegrationModuleException::class)
    override fun prepareCreatePrescription(nihii: String, patientId: String, prescriptionType: String) {
        val cacheId = "($patientId#$prescriptionType)"
        getNewKeyFromKgss(prescriptionType, nihii, null, patientId, etkHelper!!.systemETK[0].encoded)?.let { keyCache.put(cacheId, it) }
    }

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
    private fun getNewKey(nihii: String, patientId: String, prescriptionType: String): KeyResult? {
        var key: KeyResult? = null

        val cacheId = "($patientId#$prescriptionType)"
        if (keyCache.containsKey(cacheId)) {
            key = keyCache[cacheId]
            keyCache.remove(cacheId)
        } else {
            key = getNewKeyFromKgss(prescriptionType, nihii, null, patientId, etkHelper!!.systemETK[0].encoded)
        }
        return key
    }


    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun ping(samlToken: SAMLToken, credential: Credential) {
        var response: AliveCheckResponse? = null
        try {
            response = recipePrescriberService.aliveCheck(samlToken, credential, AliveCheckRequest())
        } catch (cte: ClientTransportException) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte)
        }

        LOG.info("Ping response : " + response.aliveCheckResult)
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
    override fun createPrescription(samlToken: SAMLToken, credential: Credential, nihii: String, feedbackRequested: Boolean, patientId: String, prescription: ByteArray, prescriptionType: String): String? {
        if (StringUtils.isBlank(patientId)) {
            throw IntegrationModuleException("Patient ID is 0.")
        }

        ValidationUtils.validatePatientId(patientId)
        try {

            kmehrHelper.assertValidKmehrPrescription(ByteArrayInputStream(prescription), prescriptionType)

            // init helper
            val helper = MarshallerHelper(CreatePrescriptionResult::class.java, CreatePrescriptionParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // create sealed prescription
            var message: ByteArray? = IOUtils.compress(prescription)

            val key = getNewKey(nihii, patientId, prescriptionType)
            message = sealPrescriptionForUnknown(key, message)

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
            request.securedCreatePrescriptionRequest = createSecuredContentType(sealRequest(etkRecipes[0], helper.toXMLByteArray(params)))

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
    override fun revokePrescription(samlToken: SAMLToken, credential: Credential, nihii: String, rid: String, reason: String) {
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
            request.securedRevokePrescriptionRequest = createSecuredContentType(sealRequest(etkRecipes[0], helper.toXMLByteArray(params)))

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
    override fun getPrescription(samlToken: SAMLToken, credential: Credential, nihii: String, rid: String): GetPrescriptionForPrescriberResult? {
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
            request.securedGetPrescriptionForPrescriberRequest = createSecuredContentType(sealRequest(etkRecipes[0], helper.toXMLByteArray(param)))

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

            getKeyFromKgss(result.encryptionKeyId, etkHelper!!.systemETK[0].encoded)?.let {key ->
                result.prescription = IOUtils.decompress(unsealPrescriptionForUnknown(key, result.prescription))
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
    override fun listOpenPrescription(samlToken: SAMLToken, credential: Credential, nihii: String, patientId: String): List<String> {
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
            request.securedListOpenPrescriptionsRequest = createSecuredContentType(sealRequest(etkRecipes[0], helper.toXMLByteArray(param)))

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
    override fun listOpenPrescription(samlToken: SAMLToken, credential: Credential, nihii: String): List<String> {
        return listOpenPrescription(samlToken, credential, nihii, null!!)
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
    override fun sendNotification(samlToken: SAMLToken, credential: Credential, nihii: String, notificationText: ByteArray, patientId: String, executorId: String) {
        try {
            kmehrHelper.assertValidNotification(ByteArrayInputStream(notificationText))
            ValidationUtils.validatePatientId(patientId)

            // init helper
            val helper = MarshallerHelper(Any::class.java, SendNotificationParam::class.java)

            // get recipe etk
            val etkRecipes = etkHelper!!.recipe_ETK

            // get recipient etk
            val etkRecipients = etkHelper!!.getEtks(KgssIdentifierType.NIHII_PHARMACY, executorId)

            val notificationZip = IOUtils.compress(notificationText)

            for (i in etkRecipients.indices) {
                val etkRecipient = etkRecipients[0]

                val notificationSealed = sealNotification(etkRecipient, notificationZip)

                // create param
                val param = SendNotificationParam()
                param.content = notificationSealed
                param.executorId = executorId
                param.prescriberId = nihii
                param.patientId = patientId

                // create request
                val request = SendNotificationRequest()
                request.securedSendNotificationRequest = createSecuredContentType(sealRequest(etkRecipes[0], helper.toXMLByteArray(param)))
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
    override fun updateFeedbackFlag(samlToken: SAMLToken, credential: Credential, nihii: String, rid: String, feedbackAllowed: Boolean) {
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
            request.securedUpdateFeedbackFlagRequest = createSecuredContentType(sealRequest(etkRecipes[0], helper.toXMLByteArray(param)))
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
    override fun listFeedback(samlToken: SAMLToken, credential: Credential, nihii: String, readFlag: Boolean): List<ListFeedbackItem> {

        try {
            // check if personal password has been set
            val personalETKs = etkHelper!!.getEtks(KgssIdentifierType.NIHII, nihii)

            encryptionUtils.verifyDecryption(personalETKs[0])

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
            request.securedListFeedbacksRequest = createSecuredContentType(sealRequest(etkRecipes[0], helper.toXMLByteArray(param)))

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
                item.content = try { unsealFeedback(item.content)?.let {IOUtils.decompress(it)} ?: item.content } catch (t: Throwable) {item.linkedException = t; item.content}

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
            LOG.error("Error Status received : " + response.status.code)
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
    override fun setPersonalPassword(nihii: String, niss: String, personalPassword: String) {
        try {
            val encryptionUtils = EncryptionUtils.getInstance()
            encryptionUtils.unlockPersonalKey(niss, personalPassword)
            dataUnsealer = encryptionUtils.initUnsealing()
            val tokens = etkHelper!!.getEtks(KgssIdentifierType.NIHII, nihii)
            encryptionUtils.verifyDecryption(tokens[0])
        } catch (e: Exception) {
            throw IntegrationModuleException(e)
        }

    }


    @Throws(IntegrationModuleException::class)
    protected fun unsealFeedback(message: ByteArray?): ByteArray? {
        return message?.let { unsealNotiffeed(it) }
    }


    @Throws(IntegrationModuleException::class)
    protected fun getNewKeyFromKgss(prescriptionType: String, prescriberId: String, executorId: String?, patientId: String, myEtk: ByteArray): KeyResult? {

        val etkKgss = etkHelper!!.kgsS_ETK[0]
        val credentialTypes = propertyHandler.getMatchingProperties("kgss.createPrescription.ACL." + prescriptionType)

        var keyResult: KeyResult? = null
        try {
            keyResult = null //TODO kgssService.retrieveNewKey(etkKgss.getEncoded(), credentialTypes, prescriberId, executorId, patientId, myEtk);
        } catch (t: Throwable) {
            Exceptionutils.errorHandler(t)
        }

        return keyResult
    }


    @Throws(IntegrationModuleException::class)
    protected fun sealNotification(paramEncryptionToken: EncryptionToken, paramArrayOfByte: ByteArray): ByteArray {
        return seal(paramEncryptionToken, paramArrayOfByte)
    }

    @Throws(IntegrationModuleException::class)
    protected fun sealPrescriptionForUnknown(key: KeyResult?, messageToProtect: ByteArray?): ByteArray? {
        return null //TODO seal(messageToProtect, key.getSecretKey(), key.getKeyId());
    }

    companion object {

        private val LOG = Logger.getLogger(PrescriberIntegrationModuleImpl::class.java)
    }
}
