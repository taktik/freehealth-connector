package org.taktik.freehealth.middleware.domain.memberdata

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError

class MemberDataMessage (
    var commonOutput: CommonOutput? = null,
    var complete: Boolean? = false,
    var errors: List<MycarenetError>? = null,
    var genericErrors: List<FaultType>? = null,
    var memberDataResponse: List<MemberDataResponse>? = null,
    var io: String? = null,
    var appliesTo: String? = null,
    var reference: String? = null,
    var valueHash: String? = null
)
