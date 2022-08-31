package org.taktik.freehealth.middleware.service

import com.nimbusds.oauth2.sdk.TokenResponse

interface APBService {
    fun getAPBBearerToken(): TokenResponse
    fun getFTMBearerToken(): TokenResponse
}
