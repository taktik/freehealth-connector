package org.taktik.freehealth.middleware.dto.common

import be.cin.nip.async.generic.TAck
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

class GenAsyncResponse(var result: Boolean? = null, var commonOutput: CommonOutput? = null, var tack: TAck? = null, var mycarenetConversation: MycarenetConversation? = null)
