package org.taktik.freehealth.middleware.dto.dmg

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType

import java.io.Serializable
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
class DmgClosure : DmgMessageWithPatient(), Serializable {
    var previousHcParty: HcpartyType? = null
    var newHcParty: HcpartyType? = null
    var endOfPreviousDmg: Date? = null
    var beginOfNewDmg: Date? = null
    var newHcPartyId: String? = null
}
