package org.taktik.connector.business.chapterIV.utils

import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType
import org.apache.commons.logging.LogFactory
import java.io.IOException
import java.util.ArrayList
import java.util.Properties
import org.slf4j.LoggerFactory

object ACLUtils {
    private val props = Properties()
    private val LOG = LogFactory.getLog(ACLUtils::class.java)
    private val config = ConfigFactory.getConfigValidator()

    @Throws(TechnicalConnectorException::class)
    fun createAclChapterIV(subTypeName: String): List<CredentialType> {
        val allowedReaders = ArrayList<CredentialType>()
        val rootKey = "chapterIV.$subTypeName.ACL"
        val defaultCredentialTypes = getMatchingProperties(rootKey)
        var credentialTypes = config.getMatchingProperties(rootKey)
        if (credentialTypes.size == 0) {
            LOG.debug("Using default properties")
            credentialTypes = defaultCredentialTypes
        }

        LOG.debug("#of ACL's found in config: " + credentialTypes.size)

        credentialTypes.forEach { credentialTypeStr ->
            val atrs = credentialTypeStr.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (atrs.size != 3 && atrs.size != 2) {
                LOG.error("Incorrect attributes array length : throwing technical connector exception")
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY, *arrayOfNulls(0))
            }

            LOG.debug(" .ACL: " + atrs[0] + " # " + atrs[1])
            val ct = CredentialType()
            ct.namespace = atrs[0]
            ct.name = atrs[1]
            if (atrs.size == 3) {
                ct.values.add(atrs[2])
            }
            allowedReaders.add(ct)
        }

        return allowedReaders
    }

    private fun getMatchingProperties(rootKey: String): List<String> {
        var i = 1
        val ret = ArrayList<String>()

        while (true) {
            val key = "$rootKey.$i"
            if (props.getProperty(key) == null) {
                return ret
            }

            ret.add(props.getProperty(key))
            ++i
        }
    }

    init {
        try {
            props.load(ACLUtils::class.java.getResourceAsStream("/be.fgov.ehealth.business.chapter4.properties"))
        } catch (var1: IOException) {
            LOG.warn("Couldn't load chapterIV properties")
        }
    }
}
