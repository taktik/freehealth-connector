package org.taktik.freehealth.middleware.dto.efact

import be.cin.nip.async.generic.TAck
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput

class EfactMessage {
    var detail: String? = null
    var id: String? = null
    var name: String? = null

    var commonOutput: CommonOutput? = null

    var message: List<Record>? = null
    var xades : String? = null
    var tAck: TAck? = null

    var hashValue: String? = null
}
