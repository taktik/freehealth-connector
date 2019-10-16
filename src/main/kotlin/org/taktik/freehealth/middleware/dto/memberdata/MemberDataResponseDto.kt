package org.taktik.freehealth.middleware.dto.memberdata

import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

class MemberDataResponseDto(var assertions: List<AssertionDto> = ArrayList(), var mycarenetConversation: MycarenetConversation? = null)
