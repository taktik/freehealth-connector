package org.taktik.freehealth.middleware.service.impl

import com.nimbusds.oauth2.sdk.ClientCredentialsGrant
import com.nimbusds.oauth2.sdk.TokenRequest
import com.nimbusds.oauth2.sdk.TokenResponse
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic
import com.nimbusds.oauth2.sdk.auth.Secret
import com.nimbusds.oauth2.sdk.http.HTTPRequest
import com.nimbusds.oauth2.sdk.id.ClientID
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import org.taktik.freehealth.middleware.service.APBService
import java.net.URI

@Service
class APBServiceImpl() : APBService {
    private val config = ConfigFactory.getConfigValidator()


    override fun getAPBBearerToken(): TokenResponse {
        val customParams = HashMap<String, List<String>>()
        customParams["scope"] = listOf("production")
        var user = SecurityContextHolder.getContext().authentication?.principal as? User
        if (user?.apbCustomerId == null && user?.apbPassword === null)
            return getBearerToken(config.getProperty("apb.username"), config.getProperty("apb.password"), customParams)
        return getBearerToken(user.apbCustomerId, user.apbPassword, customParams)
    }

    override fun getFTMBearerToken(): TokenResponse {
        val customParams = HashMap<String, List<String>>()
        customParams["scope"] = listOf("tools")
        var user = SecurityContextHolder.getContext().authentication?.principal as? User
        if (user?.ftmCustomerId == null && user?.ftmPassword === null)
            return getBearerToken(config.getProperty("ftm.username"), config.getProperty("ftm.password"), customParams)
        return getBearerToken(user.ftmCustomerId, user.ftmPassword, customParams)
    }

    fun getBearerToken(customerId: String?, password: String?, customParams: HashMap<String, List<String>>): TokenResponse {
        customParams["grant_type"] = listOf("client_credentials")
        val tokenRequest = TokenRequest(
            URI("https://auth.apb.be/connect/token"),
            ClientSecretBasic(
                ClientID(customerId),
                Secret(password)
            ),
            ClientCredentialsGrant(),
            null,
            null,
            customParams
        )

        val httpRequest: HTTPRequest = tokenRequest.toHTTPRequest()
        httpRequest.accept = MediaType.APPLICATION_JSON_VALUE
        httpRequest.connectTimeout = 30000
        httpRequest.readTimeout = 30000
        return TokenResponse.parse(httpRequest.send())
    }

}
