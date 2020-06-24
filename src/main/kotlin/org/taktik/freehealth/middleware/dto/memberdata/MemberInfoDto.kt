package org.taktik.freehealth.middleware.dto.memberdata

class MemberInfoDto(
    var ssin: String? = null,
    var io: String? = null,
    var ioMembership: String? = null,
    var hospitalized: Boolean = false,
    var uniqId: String
)
