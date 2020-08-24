package org.taktik.freehealth.middleware.domain.memberdata

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.dto.memberdata.MemberDataAckDto
import org.taktik.freehealth.middleware.dto.memberdata.MemberDataMessageDto
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.util.*

class MemberDataList (
    val mycarenetConversation: MycarenetConversation?,
    val acks: List<MemberDataAck>?,
    val memberDataMessageList: List<MemberDataMessage>?,
    val date: Date?,
    val genericErrors: List<FaultType>?
)
