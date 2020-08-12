package org.taktik.freehealth.middleware.domain.memberdata

import be.cin.types.v1.FaultType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonOutputType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Assertion

class MemberDataBatchResponse (
    var assertions: List<Assertion> = ArrayList(),
    var status: MdaStatus? = null,
    var errors: List<FaultType>? = null,
    var myCarenetErrors: List<MycarenetError> = ArrayList(),
    var issuer: String? = null,
    var inResponseTo: String? = null,
    var responseId: String? = null
)
