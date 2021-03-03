package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jwt.SignedJWT
import com.nimbusds.oauth2.sdk.AuthorizationGrant
import com.nimbusds.oauth2.sdk.ClientCredentialsGrant
import com.nimbusds.oauth2.sdk.GrantType
import com.nimbusds.oauth2.sdk.TokenRequest
import com.nimbusds.oauth2.sdk.TokenResponse
import com.nimbusds.oauth2.sdk.auth.ClientAuthenticationMethod
import com.nimbusds.oauth2.sdk.auth.JWTAuthentication
import com.nimbusds.oauth2.sdk.auth.JWTAuthenticationClaimsSet
import com.nimbusds.oauth2.sdk.auth.PrivateKeyJWT
import com.nimbusds.oauth2.sdk.http.HTTPRequest
import com.nimbusds.oauth2.sdk.id.Audience
import com.nimbusds.oauth2.sdk.id.ClientID
import com.nimbusds.oauth2.sdk.id.JWTID
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.technical.enumeration.SsoProfile
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sso.SingleSignOnService
import org.taktik.connector.technical.service.sso.impl.SingleSignOnServiceImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.domain.rsw.Jwt
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import org.taktik.freehealth.middleware.service.SSOService
import org.taktik.freehealth.middleware.service.STSService
import java.net.URI
import java.security.interfaces.RSAPrivateKey
import java.util.*

@Service
class SSOServiceImpl(private val stsService: STSService, private val keyDepotService: KeyDepotService) : SSOService {
    val ssosi: SingleSignOnService = SingleSignOnServiceImpl()

    override fun getBearerToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, profile: String?): BearerToken? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())

        return ssosi.signin(profile?.let { SsoProfile.valueOf(it) } ?: SsoProfile.SAML2_POST, samlToken)?.let { BearerToken(it) }
    }

    override fun getOauth2Token(tokenId: UUID, keystoreId: UUID, passPhrase: String, cbe: String, kid: String, isAcceptance: Boolean): TokenResponse? {
        val principal = SecurityContextHolder.getContext().authentication?.principal as? User ?: throw AuthenticationCredentialsNotFoundException("JWT tokens are only available through authenticated accesses")
        val orgKeystoreUuid = (if (isAcceptance) principal.orgKeystoreAccUuid else principal.orgKeystoreProdUuid)?.let { UUID.fromString(it) } ?: throw AuthenticationCredentialsNotFoundException("Organisation keystore was not set")
        val orgKeystorePassword = (if (isAcceptance) principal.orgKeystoreAccPassword else principal.orgKeystoreProdPassword) ?: throw AuthenticationCredentialsNotFoundException("Organisation keystore password was not set")

        val orgKeystore = stsService.getKeyStore(orgKeystoreUuid, orgKeystorePassword)

        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")

        val hokPrivateKeys = KeyManager.getDecryptionKeys(orgKeystore, passPhrase.toCharArray())

        val tokenRequest = TokenRequest(
            URI("https://api-acpt.ehealth.fgov.be/auth"),
            PrivateKeyJWT(
                JWTAuthenticationClaimsSet(
                    ClientID("cbe-" + cbe),
                    Audience(if (isAcceptance) "https://api-acpt.ehealth.fgov.be/auth/realms/M2M" else "https://api.ehealth.fgov.be/auth/realms/M2M")
                ),
                JWSAlgorithm.RS256,
                hokPrivateKeys.entries.find { it.key != "authentication" }?.value as? RSAPrivateKey?
                    ?: throw AuthenticationCredentialsNotFoundException("Cannot find private key for signature"),
                kid,
                null
            ),
            ClientCredentialsGrant()
        )

        val httpRequest: HTTPRequest = tokenRequest.toHTTPRequest()
        httpRequest.accept = MediaType.APPLICATION_JSON_VALUE
        httpRequest.connectTimeout = 30000
        httpRequest.readTimeout = 30000
        val tokenResponse = TokenResponse.parse(httpRequest.send())

        return tokenResponse
    }

}
