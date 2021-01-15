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
import org.taktik.freehealth.middleware.dto.consultrn.PersonMid
import org.taktik.freehealth.middleware.service.ConsultRnV2Service
import java.util.*

@RestController
@RequestMapping("/consultrnv2")
class ConsultrnV2Controller(val consultRnV2Service: ConsultRnV2Service, val mapper: MapperFacade){

    @GetMapping("/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchPersonBySsin(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "ssin") ssin: String
    ) = consultRnV2Service.searchPersonBySsin(
        keystoreId,
        tokenId,
        passPhrase,
        ssin
    )

    @GetMapping("/{dateOfBirth}/{lastName}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchPersonPhonetically(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "dateOfBirth") dateOfBirth: Int,
        @PathVariable(value = "lastName") lastName: String,
        @RequestParam(required = false) firstName: String?,
        @RequestParam(required = false) middleName: String?,
        @RequestParam(required = false) gender: String?,
        @RequestParam(required = false) countryCode: Int?,
        @RequestParam(required = false) cityCode: String?,
        @RequestParam(required = false) tolerance: Int?,
        @RequestParam(required = false) limit: Int?
    ) = consultRnV2Service.searchPersonPhonetically(
        keystoreId,
        tokenId,
        passPhrase,
        dateOfBirth,
        lastName,
        firstName,
        middleName,
        gender,
        countryCode,
        cityCode,
        tolerance,
        limit
    )

    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerPerson(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody mid: PersonMid
    ) = consultRnV2Service.registerPerson(
        keystoreId,
        tokenId,
        passPhrase,
        mid
    )

    @GetMapping("/history/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultCurrentSsin(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "ssin") ssin: String
    ) = consultRnV2Service.consultCurrentSsin(
        keystoreId,
        tokenId,
        passPhrase,
        ssin
    )

}
