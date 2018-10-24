package org.taktik.freehealth.middleware.web.controllers

import ma.glasnost.orika.MapperFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.dmg.DmgAcknowledge
import org.taktik.freehealth.middleware.dto.dmg.DmgMessage
import org.taktik.freehealth.middleware.service.DmgService
import java.util.*

@RestController
@RequestMapping("/gmd")
class DmgController(val dmgService: DmgService, val mapper: MapperFacade) {
    @PostMapping("/register/{oa}")
    fun registerDoctor(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @PathVariable oa: String, @RequestParam bic: String, @RequestParam iban: String) =
        dmgService.registerDoctor(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, oa = oa, bic = bic, iban = iban)

    @GetMapping("")
    fun consultDmg(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: String,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam(required = false) patientSsin: String? = null,
        @RequestParam(required = false) patientGender: String? = null,
        @RequestParam(required = false) oa: String? = null,
        @RequestParam(required = false) regNrWithMut: String? = null,
        @RequestParam(required = false) requestDate: Long? = null
    ) =
        dmgService.consultDmg(keystoreId = UUID.fromString(keystoreId), tokenId = UUID.fromString(tokenId), passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, patientSsin = patientSsin, patientGender = patientGender, oa = oa, regNrWithMut = regNrWithMut, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date()).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgConsultation::class.java)
        }

    @PostMapping("/notify/{nomenclature}")
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
        @RequestParam(required = false) patientFirstName: String,
        @RequestParam(required = false) patientLastName: String,
        @RequestParam(required = false) patientGender: String?,
        @PathVariable nomenclature: String,
        @RequestParam(required = false) requestDate: Long? = null
    ) =
        dmgService.notifyDmg(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, patientSsin = patientSsin, patientGender = patientGender, patientFirstName = patientFirstName, patientLastName = patientLastName, oa = oa, regNrWithMut = regNrWithMut, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date(), nomenclature = nomenclature).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgNotification::class.java)
        }

    @PostMapping("/reqlist")
    fun postDmgsListRequest(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam(required = false) oa: String?,
        @RequestParam(required = false) requestDate: Long? = null
    ) =
        dmgService.postDmgsListRequest(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, oa = oa, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date())

    @PostMapping("/messages")
    fun getDmgMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @RequestParam oa: String, @RequestBody messageNames: List<String>?) =
        dmgService.getDmgMessages(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, oa = oa, messageNames = messageNames).map {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgMessage::class.java)
        }

    @PostMapping("/confirm/messages")
    fun confirmDmgMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @RequestBody dmgMessages: List<DmgMessage>) =
        dmgService.confirmDmgMessages(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, dmgMessages = dmgMessages.map {
            mapper.map(it, org.taktik.connector.business.domain.dmg.DmgMessage::class.java)
        })

    @PostMapping("/confirm/acks")
    fun confirmAcks(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @RequestBody dmgTacks: List<DmgAcknowledge>) =
        dmgService.confirmAcks(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, dmgTacks = dmgTacks.map {
            mapper.map(it, org.taktik.connector.business.domain.dmg.DmgAcknowledge::class.java)
        })
}
