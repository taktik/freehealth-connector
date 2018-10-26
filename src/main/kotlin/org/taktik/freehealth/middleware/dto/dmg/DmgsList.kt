package org.taktik.freehealth.middleware.dto.dmg

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

    var lists: MutableList<DmgsList> = ArrayList()

    var acks: MutableList<DmgAcknowledge> = ArrayList()
    var inscriptions: MutableList<DmgInscription> = ArrayList()
    var closures: MutableList<DmgClosure> = ArrayList()
    var extensions: MutableList<DmgExtension> = ArrayList()

    var date: Date? = null
}
