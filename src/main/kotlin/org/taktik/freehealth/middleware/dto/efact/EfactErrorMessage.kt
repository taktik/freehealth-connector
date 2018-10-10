package org.taktik.freehealth.middleware.dto.efact

import be.cin.nip.async.generic.TAck

class EfactErrorMessage {
    var detail: String? = null
    var id: String? = null
    var name: String? = null

    var xades : String? = null
    var tAck: TAck? = null

    var hashValue: String? = null
}
