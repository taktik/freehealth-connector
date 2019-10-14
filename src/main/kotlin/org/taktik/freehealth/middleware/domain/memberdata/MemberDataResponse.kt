package org.taktik.freehealth.middleware.dto.memberdata

import be.cin.types.v1.FaultType
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonOutputType
import org.taktik.freehealth.middleware.domain.memberdata.Status
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.Assertion

class MemberDataResponse(var assertions: List<Assertion> = ArrayList(), var status: Status? = null, var mycarenetConversation: MycarenetConversation? = null,
                         var errors: List<FaultType>? = null, var commonOutput: CommonOutputType? = null)
