package org.taktik.freehealth.middleware.domain.rsw

data class Jwt(
    var accessToken: String? = null,
    var refreshToken: String? = null,
    var tokenType: String? = null,
    var expiresIn: Int? = null,
    var error: Int? = null,
    var errorDescription: String? = null)
