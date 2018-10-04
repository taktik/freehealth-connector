package org.taktik.freehealth.middleware.dto.efact

import be.cin.nip.async.generic.TAck
import java.io.Serializable

class EfactSendResponse(var success: Boolean? = null, var inputReference: String? = null, var tack: TAck? = null, var detail: String? = null) : Serializable
