package org.taktik.freehealth.utils

import org.taktik.connector.technical.service.sts.security.SAMLToken

/**
 * Map HealthcareParty type from the SAMLToken quality
 */
fun hcpTypeFromSamlToken(samlToken: SAMLToken) = when (samlToken.quality) {
    "nurse" -> "persnurse"
    "groupofnurses" -> "persnurse"
    "doctor" -> "persphysician"
    "medicalhouse" -> "persphysician"
    "guardpost" -> "persphysician"
    "sortingcenter" -> "persphysician"
    "officedoctors" -> "persphysician"
    "dentist" -> "persdentist"
    "physiotherapist" -> "persphysiotherapist"
    "midwife" -> "persmidwife"
    else -> null
}
