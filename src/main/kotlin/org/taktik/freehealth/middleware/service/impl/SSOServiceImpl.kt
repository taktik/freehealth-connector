package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import org.springframework.stereotype.Service
import org.taktik.connector.technical.enumeration.SsoProfile
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sso.SingleSignOnService
import org.taktik.connector.technical.service.sso.impl.SingleSignOnServiceImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import org.taktik.freehealth.middleware.service.SSOService
import org.taktik.freehealth.middleware.service.STSService
import java.util.UUID

@Service
class SSOServiceImpl(private val stsService: STSService, private val keyDepotService: KeyDepotService) : SSOService {
    val ssosi: SingleSignOnService = SingleSignOnServiceImpl()

    override fun getBearerToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, profile: String?): BearerToken? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())

        return ssosi.signin(profile?.let { SsoProfile.valueOf(it) } ?: SsoProfile.SAML2_POST, samlToken)?.let { BearerToken(it) }
    }
}
