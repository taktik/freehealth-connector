package org.taktik.freehealth.middleware.domain.memberdata

class MemberInfo(
    var ssin:String? = null,
    var io: String? = null,
    var ioMembership:String? = null,
    var hospitalized: Boolean = false,
    var uniqId: String? = null
)
