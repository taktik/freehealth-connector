package org.taktik.freehealth.middleware.dispatcher

import org.springframework.web.multipart.MultipartFile
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.dto.CertificateInfo
import java.security.KeyStore
import java.util.*

interface STSDispatcher {
    fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String, //nihii for medical house and niss for doctor
        passPhrase: String,
        quality: String,
        tokenId: UUID?,
        extraDesignators: List<Pair<String, String>>
    ): SamlTokenResult?

    fun registerToken(tokenId: UUID, token: String, quality: String)
    fun checkTokenValid(tokenId: UUID): Boolean
    fun uploadKeystore(file: MultipartFile): UUID
    fun uploadKeystore(data: ByteArray): UUID
    fun getKeyStore(keystoreId: UUID, passPhrase: String): KeyStore?
    fun checkIfKeystoreExist(keystoreId: UUID): Boolean
    fun getSAMLToken(tokenId: UUID, keystoreId: UUID, passPhrase: String): SAMLToken?
    fun getKeystoreInfo(keystoreId: UUID, passPhrase: String): CertificateInfo
}
