package org.taktik.freehealth.middleware.service.impl.examples

import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.dto.HealthcareParty
import java.time.LocalDateTime

data class PrescriptionExample(val hcp: HealthcareParty, val patient: Patient, val medications: List<Medication>, val deliveryDate: LocalDateTime? = null)
