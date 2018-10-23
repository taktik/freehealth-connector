package org.taktik.freehealth.middleware.dto.dmg

import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.util.Date

class DmgRegistration : DmgMessage() {
    var isSuccess: Boolean = false
    var date: Date? = null
}
