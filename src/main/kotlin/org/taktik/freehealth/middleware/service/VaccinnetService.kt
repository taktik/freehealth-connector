package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
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
        patientId: String,
        softwareId: String,
        vaccinnetId: String,
        kmehrmessage: Kmehrmessage
    ): AddVaccinationsResponseType
}
