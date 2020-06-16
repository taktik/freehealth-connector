package org.taktik.freehealth.middleware.dto.memberdata

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.util.*

class MemberDataListDto (
     val mycarenetConversation: MycarenetConversation?,
     val acks: List<MemberDataAckDto>?,
     val memberDataMessageList: List<MemberDataMessageDto>?,
     val date: Date?,
     val genericErrors: List<FaultType>?
)
