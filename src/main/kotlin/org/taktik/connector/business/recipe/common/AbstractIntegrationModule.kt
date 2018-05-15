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
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken
import be.fgov.ehealth.etee.crypto.status.CryptoResultException
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent
import net.sf.ehcache.Cache
import net.sf.ehcache.CacheManager
import org.apache.log4j.Logger
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Base64
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.utils.ETKHelper
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils
import org.taktik.connector.business.recipeprojects.core.utils.LoggingUtil
import org.taktik.connector.business.recipeprojects.core.utils.MessageDumper
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.kgss.impl.KgssServiceImpl

import javax.crypto.spec.SecretKeySpec
import java.io.File
import java.security.Key
import java.security.KeyStore
import java.security.Security
import java.util.Arrays
import java.util.regex.Pattern

abstract class AbstractIntegrationModule @Throws(IntegrationModuleException::class) constructor() {
    private val ridPattern = Pattern.compile(RID_PATTERN)

    protected var dataUnsealer: DataUnsealer? = null
    var oldDataSealer: DataSealer? = null
    var oldDataUnsealer: DataUnsealer? = null

    protected var etkHelper: ETKHelper? = null
        private set
    protected var symmKey: Key? = null
        private set

    private var cacheManager: CacheManager? = null
    private var kgssCache: Cache? = null
    private var etkCache: Cache? = null

    private val kgssService = KgssServiceImpl()

    protected val encryptionUtils: EncryptionUtils
        get() = EncryptionUtils.getInstance()

    val propertyHandler: PropertyHandler
        get() = PropertyHandler.getInstance()

    private val jaxContextCentralizer: JaxContextCentralizer
        get() = JaxContextCentralizer.getInstance()

    init {
        init()
    }

    @Throws(IntegrationModuleException::class)
    protected fun init() {
        try {
            LOG.info("Init abstractIntegrationModule!")
            LoggingUtil.initLog4J(propertyHandler)

            jaxContextCentralizer.addContext(GetKeyRequestContent::class.java)
            jaxContextCentralizer.addContext(GetKeyResponseContent::class.java)

            Security.addProvider(BouncyCastleProvider())

            MessageDumper.getInstance().init(propertyHandler)

            // When running in DOTNET, the current context class loader must be overriden to avoid class not found exceptions!!!
            Thread.currentThread().contextClassLoader = javaClass.classLoader
            System.setProperty(
                "javax.xml.soap.SOAPFactory",
                "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl"
            )

            // Extra debug information
            if (LOG.isDebugEnabled) {
                LOG.debug("Curdir : " + File(".").canonicalPath)
                LOG.debug("Support P12 keystores : " + KeyStore.getInstance("PKCS12"))
            }

            //initCaching();
            //initEncryption();
            LOG.info("End Init abstractIntegrationModule!")
        } catch (t: Throwable) {
            LOG.error("Exception in init abstractIntegrationModule: ", t)
            Exceptionutils.errorHandler(t)
        }
    }

    private fun initCaching() {
        LOG.info("INIT CACHE MANAGER")
        val url = javaClass.getResource("/cache/config/ehcache.xml")
        cacheManager = CacheManager.newInstance(url)

        LOG.info("DOES KGSS CACHE EXIST?")
        kgssCache = cacheManager!!.getCache("KGSS")
        if (kgssCache == null) {
            LOG.info("NEW KGSS CACHE")
            kgssCache = Cache("KGSS", 0, false, false, 0, 0)
            cacheManager!!.addCache(kgssCache)
        }

        LOG.info("DOES ETK CACHE EXIST?")
        etkCache = cacheManager!!.getCache("ETK")
        if (etkCache == null) {
            LOG.info("NEW ETK CACHE")
            etkCache = Cache("ETK", 0, false, false, 0, 0)
            cacheManager!!.addCache(etkCache)
        }
    }

    @Throws(IntegrationModuleException::class)
    private fun initEncryption() {
        try {

            LOG.info("Init the encryption - create symmKey")
            symmKey = encryptionUtils.generateSecretKey()

            if (encryptionUtils.oldKeyStore != null) {
                oldDataSealer = encryptionUtils.initOldSealing()
                oldDataUnsealer = encryptionUtils.initOldUnSealing()
            }
            LOG.info("Init the encryption - init etkHelper")
            etkHelper = ETKHelper(propertyHandler, encryptionUtils)

            // if (hasPersonalEtk()) { //only for care providers
            // LOG.info("Init the encryption - care provider has a personal ETK");
            // encrUtils.verifyDecryption(etkHelper.getSystemETK().get(0));
            // }
        } catch (t: Throwable) {
            LOG.error("Exception occured when initializing the encryption util: ", t)
            Exceptionutils.errorHandler(t, "error.initialization")
        }
    }

    @Synchronized
    @Throws(IntegrationModuleException::class)
    protected fun sealRequest(paramEncryptionToken: EncryptionToken, paramArrayOfByte: ByteArray): ByteArray {
        return seal(paramEncryptionToken, paramArrayOfByte)
    }

    protected fun seal(paramEncryptionToken: EncryptionToken, paramArrayOfByte: ByteArray): ByteArray {
        return ByteArray(0)
    }

    /**
     * Unseal request.
     *
     * @param message the message
     * @return the byte[]
     * @throws IntegrationModuleException the integration module exception
     */

    @Throws(IntegrationModuleException::class)
    protected fun unsealRequest(message: ByteArray): ByteArray {
        return unseal(message)
    }

    protected fun unseal(message: ByteArray): ByteArray {
        return ByteArray(0)
    }

    /**
     * Unseal.
     *
     * @param message the message
     * @return the byte[]
     * @throws IntegrationModuleException the integration module exception
     */
    @Throws(IntegrationModuleException::class)
    protected fun unsealNotif(message: ByteArray): ByteArray {
        return unseal(message)
    }

    private fun unsealNotifOld(message: ByteArray): ByteArray? {
        val result = oldDataUnsealer!!.unseal(message)
        // decryption operation succeeded and there are no errors or failures
        if (result != null && result.hasData()) {

            if (result.hasErrors()) { // 3.A.A. There are no errors or failures
                for (error in result.errors) {
                    LOG.error(error.name)
                }
                for (warning in result.warnings) {
                    LOG.error(warning.name)
                }
                if (result.fatal != null) {
                    LOG.error(result.fatal.errorMessage)
                }
            }

            // Get the unsealed data
            val unsealedDataStream = result.data.content

            return IOUtils.getBytes(unsealedDataStream)
        }
        return null
    }

    @Throws(IntegrationModuleException::class)
    protected fun unsealNotiffeed(message: ByteArray): ByteArray? {
        var unsealedNotification: ByteArray? = null
        var calledUnsealNotifOld = false
        try {
            LOG.debug("Start unseal notification: " + org.apache.commons.io.IOUtils.toString(message, "UTF-8"))
            unsealedNotification = unseal(message)
            if (unsealedNotification != null) {
                return unsealedNotification
            }
            if (oldDataUnsealer != null) {
                LOG.debug(
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
                LOG.debug("OldDataUnsealer is null.")
            }
        } catch (t: Throwable) {
            LOG.error("Exception occured with unsealing notification: ", t)
            if (calledUnsealNotifOld) {
                if (t is CryptoResultException && t.message?.contains("There is no data available") == true) {
                    return null
                }
                Exceptionutils.errorHandler(t, "error.data.unseal")
            } else {
                try {
                    LOG.debug(
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
    protected fun unsealPrescriptionForUnknown(key: KeyResult, protectedMessage: ByteArray): ByteArray {
        return unsealForUnknown(key, protectedMessage)
    }

    private fun unsealForUnknown(key: KeyResult, protectedMessage: ByteArray): ByteArray {
        return ByteArray(0)
    }

    @Throws(IntegrationModuleException::class)
    protected fun getKeyFromKgss(keyId: String, myEtk: ByteArray): KeyResult? {
        var keyResult: KeyResult? = null
        try {
            // For test, when a sim key is specified in the config
            if (propertyHandler.hasProperty("test_kgss_key")) {
                val part1 =
                    propertyHandler.getProperty("test_kgss_key").split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                val part2 =
                    propertyHandler.getProperty("test_kgss_key").split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                // LOG.info("KGSS key retrieved from configuration. Key Id = part1);
                val keyResponse = Base64.decode(part2)
                return KeyResult(SecretKeySpec(keyResponse, "AES"), part1)
            }

            keyResult =
                null //TODO kgssService.retrieveKeyFromKgss(keyId.getBytes(), myEtk, etkHelper.getKGSS_ETK().get(0).getEncoded());
        } catch (t: Throwable) {
            LOG.error("Exception in getKeyFromKgss abstractIntegrationModule: ", t)
            Exceptionutils.errorHandler(t)
        }

        return keyResult
    }

    @Throws(IntegrationModuleException::class)
    protected fun validateRid(rid: String) {
        val matcher = ridPattern.matcher(rid)
        if (!matcher.find()) {
            LOG.error("Invalid RID was provided.")
            throw IntegrationModuleException(I18nHelper.getLabel("error.rid.validation", arrayOf<Any>(rid)))
        }
    }

    companion object {

        private val LOG = Logger.getLogger(AbstractIntegrationModule::class.java)

        val EHEALTH_SUCCESS_CODE_100 = "100"
        val EHEALTH_SUCCESS_CODE_200 = "200"
        val RID_PATTERN = "BE([PKN])([P0-9])([0-9A-Z]){8}"
    }
}
