package org.taktik.freehealth.middleware.dto.efact

import be.cin.nip.async.generic.TAck
import java.io.Serializable

class EfactSendResponse : Serializable {
    constructor()

    constructor(success: Boolean, inputReference: String, tack: TAck?) {
        this.success = success; this.inputReference = inputReference; this.tack = tack
    }

    var tack : TAck? = null
    var inputReference : String? = null
    var success : Boolean? = null
}
