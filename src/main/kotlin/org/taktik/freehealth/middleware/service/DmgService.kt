package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.dmg.DmgAcknowledge
import org.taktik.connector.business.domain.dmg.DmgConsultation
import org.taktik.connector.business.domain.dmg.DmgMessage
import org.taktik.connector.business.domain.dmg.DmgNotification
import org.taktik.connector.business.domain.dmg.DmgRegistration
import org.taktik.connector.business.domain.dmg.DmgsList
import java.util.*

interface DmgService {
    fun registerDoctor(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        oa: String,
        bic: String,
        iban: String
    ): DmgRegistration

    fun consultDmg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String?,
        patientGender: String?,
        oa: String?,
        regNrWithMut: String?,
        requestDate: Date
    ): DmgConsultation

    fun notifyDmg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String?,
        oa: String?,
        regNrWithMut: String?,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String?,
        nomenclature: String,
        requestDate: Date
    ): DmgNotification

    fun postDmgsListRequest(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        oa: String?,
        requestDate: Date
    ): Boolean

    fun getDmgMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        oa: String,
        messageNames: List<String>?
    ): DmgsList

    fun confirmDmgMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        dmgMessagesHashes: List<String>
    ): Boolean

    fun confirmAcks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        dmgAcksHashes: List<String>
    ): Boolean

}
