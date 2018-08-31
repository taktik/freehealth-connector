package org.taktik.connector.business.chapterIV.utils

import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.ConfigValidator
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory
import org.taktik.connector.technical.utils.IdentifierType

object KeyDepotHelper {
    private var configValidator = ConfigFactory.getConfigValidator(listOf(
        "chapterIV.keydepot.application",
        "chapterIV.keydepot.identifiertype",
        "chapterIV.keydepot.identifiersubtype",
        "chapterIV.keydepot.identifiervalue"))

    val chapterIVEncryptionToken: EncryptionToken
        @Throws(TechnicalConnectorException::class)
        get() {
            val identifierTypeString = configValidator!!.getProperty("chapterIV.keydepot.identifiertype")
            val identifierSubTypeString = configValidator!!.getProperty("chapterIV.keydepot.identifiersubtype")
            val identifierSource = 48
            val identifier = IdentifierType.lookup(identifierTypeString, null as String?, identifierSource)
            return if (identifier == null) {
                throw IllegalStateException("invalid configuration : identifier with type ]$identifierTypeString[ and subtype ]$identifierSubTypeString[ for source ETKDEPOT not found")
            } else {
                KeyDepotManagerFactory.getKeyDepotManager()
                    .getEtk(identifier, configValidator!!.getLongProperty("chapterIV.keydepot.identifiervalue", 0L), configValidator!!.getProperty("chapterIV.keydepot.application"))
            }
        }
}
