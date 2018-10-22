package org.taktik.freehealth.middleware.dto.dmg

import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError

import java.io.Serializable
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 08:38
 * To change this template use File | Settings | File Templates.
 */
open class DmgMessage(
    var isComplete: Boolean? = null,
    var io: String? = null,
    var reference: String? = null,
    var valueHash: String? = null,
    var errors: MutableList<MycarenetError> = ArrayList()
) : Serializable
