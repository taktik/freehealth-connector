package org.taktik.freehealth.middleware.dto.dmg

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType

import java.io.Serializable
import java.time.Instant

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 15/06/14
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
class DmgConsultation(complete: Boolean?) : DmgMessage(complete), Serializable {

    constructor() : this(null)

    var inss: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var birthday: Instant? = null
    var deceased: Instant? = null
    var sex: String? = null
    var regNrWithMut: String? = null
    var mutuality: String? = null

    var hcParty: HcpartyType? = null
    var from: Instant? = null
    var to: Instant? = null

    var payment: Boolean? = null
}
