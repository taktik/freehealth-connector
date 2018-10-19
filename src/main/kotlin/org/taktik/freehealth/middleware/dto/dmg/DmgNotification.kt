package org.taktik.freehealth.middleware.dto.dmg

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.Error
import org.taktik.freehealth.middleware.dto.MycarenetError

import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 07:55
 * To change this template use File | Settings | File Templates.
 */
class DmgNotification(
    isComplete: Boolean? = null,
    io: String? = null,
    reference: String? = null,
    valueHash: String? = null,
    errors: MutableList<MycarenetError> = ArrayList(),
    var hcParty: HcpartyType? = null,
    var payment: Boolean? = null,
    var from: Long? = null
                     ) : DmgMessage(isComplete, io, reference, valueHash, errors), Serializable {
}
