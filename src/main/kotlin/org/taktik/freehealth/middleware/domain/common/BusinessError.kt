package org.taktik.freehealth.middleware.domain.common

class BusinessError {
    var context: String? = null
    var subcontext: String? = null
    var zone: String? = null
    var code: String? = null
    var message: MutableMap<String, String> = HashMap()
}
