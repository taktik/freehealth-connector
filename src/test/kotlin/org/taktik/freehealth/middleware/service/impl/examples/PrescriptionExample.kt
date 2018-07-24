package org.taktik.freehealth.middleware.service.impl.examples

import org.taktik.freehealth.middleware.domain.Patient
import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.dto.HealthcareParty
import java.time.LocalDateTime
import java.util.Date

data class PrescriptionExample(val hcp: HealthcareParty, val patient: Patient, val medications: List<Medication>, val deliveryDate: LocalDateTime? = null)
