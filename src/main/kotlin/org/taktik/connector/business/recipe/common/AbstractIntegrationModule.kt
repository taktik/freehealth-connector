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

/*
 *
 */
package org.taktik.connector.business.recipe.common

import be.apb.gfddpp.common.utils.JaxContextCentralizer
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer
import be.fgov.ehealth.etee.crypto.status.CryptoResultException
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.slf4j.LoggerFactory
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.utils.ETKHelper
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils
import org.taktik.connector.business.recipeprojects.core.utils.MessageDumper
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.kgss.impl.KgssServiceImpl
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import java.security.Key
import java.security.Security
import java.util.Arrays
import java.util.regex.Pattern

abstract class AbstractIntegrationModule(val keyDepotService: KeyDepotService) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val ridPattern = Pattern.compile(RID_PATTERN)

    var oldDataSealer: DataSealer? = null
    var oldDataUnsealer: DataUnsealer? = null

    protected lateinit var etkHelper: ETKHelper
        private set
    protected lateinit var symmKey: Key
        private set

    private val kgssService = KgssServiceImpl()

    protected val propertyHandler = PropertyHandler.getInstance()
    protected val encryptionUtils = EncryptionUtils.getInstance(propertyHandler)
    private val jaxContextCentralizer = JaxContextCentralizer.getInstance()

    init {
        init()
    }

    @Throws(IntegrationModuleException::class)
    protected fun init() {
        try {
            jaxContextCentralizer.addContext(GetKeyRequestContent::class.java)
            jaxContextCentralizer.addContext(GetKeyResponseContent::class.java)

            Security.addProvider(BouncyCastleProvider())

            MessageDumper.getInstance().init(propertyHandler)
            System.setProperty("javax.xml.soap.SOAPFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl")

            try {
                log.info("Init recipe encryption - create symmKey")
                symmKey = encryptionUtils.generateSecretKey()
                log.info("Init recipe encryption - init etkHelper")
                etkHelper = ETKHelper(keyDepotService)
            } catch (t: Throwable) {
                log.error("Exception occured when initializing the encryption util: ", t)
                Exceptionutils.errorHandler(t, "error.initialization")
            }
        } catch (t: Throwable) {
            log.error("Exception in init abstractIntegrationModule: ", t)
            Exceptionutils.errorHandler(t)
        }
    }

    private fun initCaching() {

    }

    @Synchronized
    @Throws(IntegrationModuleException::class)
    protected fun sealRequest(crypto: Crypto, paramEncryptionToken: EncryptionToken, paramArrayOfByte: ByteArray): ByteArray {
        return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, paramEncryptionToken, paramArrayOfByte)
    }

    /**
     * Unseal request.
     *
     * @param message the message
     * @return the byte[]
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    protected fun unsealRequest(crypto: Crypto, message: ByteArray): ByteArray {
        return crypto.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, message).contentAsByte
    }

    /**
     * Unseal.
     *
     * @param message the message
     * @return the byte[]
     * @throws IntegrationModuleException the integration module exception
     */
    @Throws(IntegrationModuleException::class)
    protected fun unsealNotif(crypto: Crypto, message: ByteArray): ByteArray {
        return crypto.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, message).contentAsByte
    }

    private fun unsealNotifOld(message: ByteArray): ByteArray? {
        val result = oldDataUnsealer!!.unseal(message)
        // decryption operation succeeded and there are no errors or failures
        if (result != null && result.hasData()) {

            if (result.hasErrors()) { // 3.A.A. There are no errors or failures
                for (error in result.errors) {
                    log.error(error.name)
                }
                for (warning in result.warnings) {
                    log.error(warning.name)
                }
                if (result.fatal != null) {
                    log.error(result.fatal.errorMessage)
                }
            }

            // Get the unsealed data
            val unsealedDataStream = result.data.content

            return IOUtils.getBytes(unsealedDataStream)
        }
        return null
    }

    @Throws(IntegrationModuleException::class)
    protected fun unsealNotiffeed(crypto: Crypto, message: ByteArray): ByteArray? {
        var unsealedNotification: ByteArray? = null
        var calledUnsealNotifOld = false
        try {
            log.debug("Start unseal notification: " + org.apache.commons.io.IOUtils.toString(message, "UTF-8"))
            unsealedNotification = crypto.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, message).contentAsByte
            if (unsealedNotification != null) {
                return unsealedNotification
            }
            if (oldDataUnsealer != null) {
                log.debug(
                    "Unseal notification was null. Start unseal notification with old keystore: " + Arrays.toString(
                        message
                    )
                )
                calledUnsealNotifOld = true
                unsealedNotification = unsealNotifOld(message)
                if (unsealedNotification != null) {
                    return unsealNotifOld(message)
                }
            } else {
                log.debug("OldDataUnsealer is null.")
            }
        } catch (t: Throwable) {
            log.error("Exception occured with unsealing notification: ", t)
            if (calledUnsealNotifOld) {
                if (t is CryptoResultException && t.message?.contains("There is no data available") == true) {
                    return null
                }
                Exceptionutils.errorHandler(t, "error.data.unseal")
            } else {
                try {
                    log.debug(
                        "Exception occured with unsealing notification. Trying to unseal notification with old keystore: " + Arrays.toString(
                            message
                        )
                    )
                    unsealedNotification = unsealNotifOld(message)
                } catch (te: Throwable) {
                    if (t is CryptoResultException && t.message?.contains("There is no data available") == true) {
                        return null
                    }
                    Exceptionutils.errorHandler(te, "error.data.unseal")
                }

            }
        }

        if (unsealedNotification == null) {
            throw IntegrationModuleException(I18nHelper.getLabel("error.data.unseal"))
        }
        return unsealedNotification
    }


    @Throws(IntegrationModuleException::class)
    protected fun unsealPrescriptionForUnknown(crypto: Crypto, key: KeyResult, protectedMessage: ByteArray): ByteArray {
        return unsealForUnknown(crypto, key, protectedMessage)
    }

    private fun unsealForUnknown(crypto: Crypto, key: KeyResult, protectedMessage: ByteArray): ByteArray {
        return crypto.unsealForUnknown(key.secretKey, protectedMessage);
    }

    @Throws(IntegrationModuleException::class)
    protected fun getKeyFromKgss(credential: KeyStoreCredential, samlToken: SAMLToken, keyId: String, myEtk: ByteArray): KeyResult? {
        return try {
            kgssService.retrieveKeyFromKgss(credential, samlToken, keyId.toByteArray(Charsets.UTF_8), myEtk, etkHelper!!.kgsS_ETK[0].encoded)
        } catch (t: Throwable) {
            log.error("Exception in getKeyFromKgss abstractIntegrationModule: ", t)
            null
        }
    }

    @Throws(IntegrationModuleException::class)
    protected fun validateRid(rid: String) {
        val matcher = ridPattern.matcher(rid)
        if (!matcher.find()) {
            log.error("Invalid RID was provided.")
            throw IntegrationModuleException(I18nHelper.getLabel("error.rid.validation", arrayOf<Any>(rid)))
        }
    }

    companion object {
        val EHEALTH_SUCCESS_CODE_100 = "100"
        val EHEALTH_SUCCESS_CODE_200 = "200"
        val RID_PATTERN = "BE([PKN])([P0-9])([0-9A-Z]){8}"
    }
}
