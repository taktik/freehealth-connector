package org.taktik.freehealth.middleware.dto.dmg

import java.io.Serializable
import java.util.Date

open class DmgMessageWithPatient : DmgMessage(), Serializable {
	var inss: String? = null
	var firstName: String? = null
	var lastName: String? = null
	var birthday: Date? = null
	var deceased: Date? = null
	var sex: String? = null
	var regNrWithMut: String? = null
	var mutuality: String? = null
}
