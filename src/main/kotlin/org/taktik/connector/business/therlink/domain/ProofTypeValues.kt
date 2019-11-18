package org.taktik.connector.business.therlink.domain

enum class ProofTypeValues private constructor(val value: String) {
    EIDREADING("eidreading"),
    SISREADING("sisreading"),
    ISIREADING("isireading"),
    EIDSIGNING("eidsigning"),
    EIDENCODING_HOUSECALL("eidencoding_housecall"),
    EIDENCODING_NOCARD("eidencoding_nocard"),
    EIDENCODING_TECHPROBLEM("eidencoding_techproblem"),
}
