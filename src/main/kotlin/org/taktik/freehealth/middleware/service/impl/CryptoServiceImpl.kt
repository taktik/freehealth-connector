package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import org.springframework.stereotype.Service
import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotManager
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.service.CryptoService
import org.taktik.freehealth.middleware.service.STSService
import java.util.UUID

@Service
class CryptoServiceImpl(val stsService: STSService, keyDepotService: KeyDepotService) : CryptoService {
    val keyDepotManager: KeyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService)

    override fun encrypt(keystoreId: UUID,
        passPhrase: String,
        addressee: Addressee
        ,
        plainData: ByteArray): ByteArray? {
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase)

        val etkTokens = this.keyDepotManager.getEtkSet(
            addressee.identifierTypeHelper,
            addressee.idAsLong,
            addressee.applicationId,
            keystoreId,
            false
                                                      ) + this.keyDepotManager.getETK(credential, keystoreId)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, etkTokens, plainData)
    }

    override fun decrypt(keystoreId: UUID,
        passPhrase: String,
        encryptedData: ByteArray): ByteArray? {
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase)

        val hokPrivateKeys = KeyManager.getDecryptionKeys(credential.keyStore, credential.password)
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        return crypto.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, encryptedData).contentAsByte
    }
}
