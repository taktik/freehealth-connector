package org.taktik.freehealth.middleware.dto.memberdata

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.domain.memberdata.MdaStatus
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Assertion

class MemberDataBatchResponseDto (
    var assertions: List<Assertion> = ArrayList(),
    var status: MdaStatus? = null,
    var errors: List<FaultType>? = null,
    var myCarenetErrors: List<MycarenetError> = ArrayList(),
    var issuer: String? = null,
    var inResponseTo: String? = null,
    var responseId: String? = null
)
