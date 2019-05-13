package org.taktik.connector.technical.validator.impl

import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper
import org.taktik.connector.technical.utils.KeyStoreManager
import org.taktik.connector.technical.validator.TimeStampValidator
import java.security.KeyStore
import java.util.HashMap

class TimeStampValidatorFactory private constructor() {

    init {
        throw UnsupportedOperationException()
    }

    companion object {
        private val TIMESTAMP_SIGNATURE_KEYSTORE_PWD = "timestamp.signature.keystore.pwd"
        private val TIMESTAMP_SIGNATURE_KEYSTORE_PATH = "timestamp.signature.keystore.path"
        private val PROP_KEYSTORE_DIR = "KEYSTORE_DIR"
        private val PROP_TIMESTAMPVALIDATOR_FACTORY = "timestamp.validator.factory"
        private val PROP_DEFAULT_TIMESTAMPVALIDATOR_FACTORY = "org.taktik.connector.technical.validator.impl.TimeStampValidatorImpl"
        private var validatorInstance: TimeStampValidator? = null
        private val helperFactory = ConfigurableFactoryHelper<TimeStampValidator>("timestamp.validator.factory", "org.taktik.connector.technical.validator.impl.TimeStampValidatorImpl")

        val instance: TimeStampValidator
            @Throws(TechnicalConnectorException::class)
            get() {
                if (validatorInstance == null) {
                    validatorInstance = helperFactory.getImplementation(init(), false)
                }

                return validatorInstance!!
            }

        @Throws(TechnicalConnectorException::class)
        private fun init(): Map<String, Any> {
            val config = ConfigFactory.getConfigValidatorFor("timestamp.signature.keystore.path", "timestamp.signature.keystore.pwd", "KEYSTORE_DIR")
            val parameterMap = HashMap<String,Any>()
            val keystorePath = config.getProperty("KEYSTORE_DIR") + config.getProperty("timestamp.signature.keystore.path")
            val keyStoreManager = KeyStoreManager(keystorePath, config.getProperty("timestamp.signature.keystore.pwd").toCharArray())
            val keyStore = keyStoreManager.keyStore
            parameterMap.put("timestampvalidator.keystore", keyStore)
            return parameterMap
        }


        val timeStampValidator: TimeStampValidator
            @Deprecated("")
            @Throws(TechnicalConnectorException::class)
            get() = instance

        fun reset() {
            validatorInstance = null
        }
    }
}
