package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.vaccinnet.VaccineInjection
import org.taktik.connector.business.vaccinnet.AddVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.GetVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationResponseType
import java.util.*

interface VaccinnetService {
    fun getVaccinations(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        patientId: String,
        softwareId: String,
        vaccinnetId: String,
        since: Long
    ): GetVaccinationsResponseType

    fun removeVaccination(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        patientId: String,
        softwareId: String,
        vaccinnetId: String,
        vaccinationId: String
    ): RemoveVaccinationResponseType

    fun addVaccinations(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpQuality: String,
        patientId: String,
        patientFirstName: String,
        patientLastName: String,
        patientDateOfBirth: Long,
        softwareId: String,
        vaccinnetId: String,
        injections: List<VaccineInjection>
    ): AddVaccinationsResponseType
}
