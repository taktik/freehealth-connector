package org.taktik.freehealth.middleware.dto.dmg

import org.taktik.connector.business.domain.Error

import java.io.Serializable
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 08:38
 * To change this template use File | Settings | File Templates.
 */
open class DmgMessage(complete: Boolean? = null) : Serializable {
	constructor() : this(null)

	var io: String? = null
	var reference: String? = null
	var valueHash: String? = null
	var isComplete: Boolean? = complete
	var errors: MutableList<Error> = ArrayList()
}
