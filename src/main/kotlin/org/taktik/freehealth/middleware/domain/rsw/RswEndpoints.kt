package org.taktik.freehealth.middleware.domain.rsw

data class RswEndpoints(
    var issuer: String? = null,
    var jwks_uri: String? = null,
    var authorization_endpoint: String? = null,
    var token_endpoint: String? = null,
    var userinfo_endpoint: String? = null,
    var end_session_endpoint: String? = null,
    var check_session_iframe: String? = null,
    var revocation_endpoint: String? = null,
    var introspection_endpoint: String? = null,
    var device_authorization_endpoint: String? = null,
    var frontchannel_logout_supported: Boolean? = null,
    var frontchannel_logout_session_supported: Boolean? = null,
    var backchannel_logout_supported: Boolean? = null,
    var backchannel_logout_session_supported: Boolean? = null,
    var scopes_supported: ArrayList<String>? = null,
    var claims_supported: ArrayList<String>? = null,
    var grant_types_supported: ArrayList<String>? = null,
    var response_types_supported: ArrayList<String>? = null,
    var response_modes_supported: ArrayList<String>? = null,
    var token_endpoint_auth_methods_supported: ArrayList<String>? = null,
    var id_token_signing_alg_values_supported: ArrayList<String>? = null,
    var subject_types_supported: ArrayList<String>? = null,
    var code_challenge_methods_supported: ArrayList<String>? = null,
    var request_parameter_supported: Boolean? = null
                       )
