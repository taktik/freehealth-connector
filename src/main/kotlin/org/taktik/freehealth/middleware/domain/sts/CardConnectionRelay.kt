package org.taktik.freehealth.middleware.domain.sts

data class CardConnectionRelay(val action: String, val data: String, val digestType: String? = null)
