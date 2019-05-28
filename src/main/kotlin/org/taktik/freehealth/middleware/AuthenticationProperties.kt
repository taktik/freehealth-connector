package org.taktik.freehealth.middleware

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("freehealth.authentication")
class AuthenticationProperties {
    var username : String? = null
    var password : String? = null
    var mcnLicense : String? = null
    var mcnPassword : String? = null
}
