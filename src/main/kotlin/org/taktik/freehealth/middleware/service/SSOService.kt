package org.taktik.freehealth.middleware.service

import com.nimbusds.oauth2.sdk.TokenResponse
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import java.util.UUID

interface SSOService {
    fun getBearerToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, profile: String? = null): BearerToken?
    fun getOauth2Token(
        tokenId: UUID,
        keystoreId: UUID,
        passPhrase: String,
        cbe: String,
        kid: String,
        isAcceptance: Boolean
    ): TokenResponse?
}
