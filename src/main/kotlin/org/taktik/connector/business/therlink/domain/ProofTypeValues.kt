package org.taktik.connector.business.therlink.domain

enum class ProofTypeValues private constructor(val value: String) {
    EIDREADING("eidreading"), SISREADING("sisreading"), ISIREADING("isireading"), EIDSIGNING("eidsigning")
}
