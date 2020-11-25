package org.taktik.freehealth.middleware.domain.rsw

data class Jwk(
    var kty: String? = null,
    var use: String? = null,
    var kid: String? = null,
    var x5t: String? = null,
    var e: String? = null,
    var n: String? = null,
    var x5c: List<String>? = null,
    var alg: String? = null
)
