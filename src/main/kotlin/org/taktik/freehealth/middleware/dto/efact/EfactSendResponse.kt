package org.taktik.freehealth.middleware.dto.efact

import be.cin.nip.async.generic.TAck

class EfactSendResponse(val success: Boolean, val inputReference: String, val tack: TAck?)
