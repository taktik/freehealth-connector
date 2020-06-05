package org.taktik.freehealth.middleware.web.controllers

import ma.glasnost.orika.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.common.GenAsyncResponse
import org.taktik.freehealth.middleware.service.DmgService
import java.util.*

@RestController
@RequestMapping("/gmd")
class DmgController(val dmgService: DmgService, val mapper: MapperFacade) {
    @PostMapping("/register/{oa}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerDoctor(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @PathVariable oa: String, @RequestParam bic: String, @RequestParam iban: String) =
        dmgService.registerDoctor(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, oa = oa, bic = bic, iban = iban)

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultDmg(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: String,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientGender: String?,
        @RequestParam(required = false) oa: String?,
        @RequestParam(required = false) regNrWithMut: String?,
        @RequestParam(required = false) requestDate: Long?
    ) =
        dmgService.consultDmg(keystoreId = UUID.fromString(keystoreId), tokenId = UUID.fromString(tokenId), passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, patientSsin = patientSsin, patientGender = patientGender, oa = oa, regNrWithMut = regNrWithMut, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date()).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgConsultation::class.java)
        }

    @PostMapping("/notify/{nomenclature}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun notifyDmg(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) oa: String?,
        @RequestParam(required = false) regNrWithMut: String?,
        @RequestParam(required = false) patientFirstName: String?,
        @RequestParam(required = false) patientLastName: String?,
        @RequestParam(required = false) patientGender: String?,
        @PathVariable nomenclature: String,
        @RequestParam(required = false) requestDate: Long?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?

        ) =
        dmgService.notifyDmg(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, traineeSupervisorFirstName = traineeSupervisorFirstName, traineeSupervisorLastName = traineeSupervisorLastName, traineeSupervisorSsin = traineeSupervisorSsin, traineeSupervisorNihii = traineeSupervisorNihii, patientSsin = patientSsin, patientGender = patientGender, patientFirstName = patientFirstName ?: "", patientLastName = patientLastName ?: "", oa = oa, regNrWithMut = regNrWithMut, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date(), nomenclature = nomenclature).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgNotification::class.java)
        }

    @PostMapping("/reqlist", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun postDmgsListRequest(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam(required = false) oa: String?,
        @RequestParam(required = false) requestDate: Long?
    ) =
        dmgService.postDmgsListRequest(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, oa = oa, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date()).let { mapper.map(it, GenAsyncResponse::class.java)}

    @PostMapping("/messages", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getDmgMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @RequestBody messageNames: List<String>?) =
        dmgService.getDmgMessages(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, messageNames = messageNames).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgsList::class.java)
        }

    @PostMapping("/confirm/messages", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmDmgMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @RequestBody dmgMessagesHashes: List<String>) =
        dmgService.confirmDmgMessages(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, dmgMessagesHashes = dmgMessagesHashes)

    @PostMapping("/confirm/acks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmAcks(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @RequestBody dmgAcksHashes: List<String>) =
        dmgService.confirmAcks(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, dmgAcksHashes = dmgAcksHashes)
}
