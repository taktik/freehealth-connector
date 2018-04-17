package org.taktik.freehealth.middleware.dto.dmg

import org.apache.commons.lang.mutable.Mutable
import java.io.Serializable
import java.util.ArrayList
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
class DmgsList : DmgMessage(), Serializable {
	var oa: String? = null
	var inscriptions: MutableList<DmgInscription> = ArrayList()
	var date: Date? = null
}
