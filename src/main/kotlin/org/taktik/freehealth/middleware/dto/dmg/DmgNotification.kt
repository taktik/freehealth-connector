package org.taktik.freehealth.middleware.dto.dmg

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType

import java.io.Serializable
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 07:55
 * To change this template use File | Settings | File Templates.
 */
class DmgNotification(complete: Boolean?) : DmgMessage(complete = complete), Serializable {
    constructor() : this(null)

    var hcParty: HcpartyType? = null
    var payment: Boolean? = null
    var from: Date? = null

    var requestXML: String? = null
    var gmdRequestXML: String? = null
    var responseXML: String? = null
}
