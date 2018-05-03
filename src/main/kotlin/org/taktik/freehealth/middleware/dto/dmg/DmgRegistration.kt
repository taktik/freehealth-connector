package org.taktik.freehealth.middleware.dto.dmg

import java.util.Date

class DmgRegistration : DmgMessage() {
    var isSuccess: Boolean = false
    var date: Date? = null
}
