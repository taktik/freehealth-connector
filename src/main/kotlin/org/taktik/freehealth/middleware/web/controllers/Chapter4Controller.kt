/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.web.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.domain.chapter4.Appendix
import org.taktik.connector.business.domain.chapter4.RequestType
import org.taktik.freehealth.middleware.drugs.civics.AddedDocumentPreview
import org.taktik.freehealth.middleware.drugs.civics.ParagraphInfos
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview
import org.taktik.freehealth.middleware.service.Chapter4Service
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@RestController
@RequestMapping("/chap4")
class Chapter4Controller(private val chapter4Service: Chapter4Service) {
    val log = LoggerFactory.getLogger(this .javaClass)

    @GetMapping("/sam/docpreviews/{chapterName}/{paragraphName}")
    fun getAddedDocuments(
        @PathVariable chapterName: String,
        @PathVariable paragraphName: String): List<AddedDocumentPreview> =
        chapter4Service.getAddedDocuments(chapterName, paragraphName)

    @GetMapping("/sam/search/{searchString}/{language}")
    fun findParagraphs(
        @PathVariable searchString: String,
        @PathVariable language: String): List<ParagraphPreview> =
        chapter4Service.findParagraphs(searchString, language)

    @GetMapping("/sam/bycnk/{cnk}/{language}")
    fun findParagraphsWithCnk(
        @PathVariable cnk: Long,
        @PathVariable language: String): List<ParagraphPreview> =
        chapter4Service.findParagraphsWithCnk(cnk, language)

    @GetMapping("/sam/info/{chapterName}/{paragraphName}")
    fun getParagraphInfos(
        @PathVariable chapterName: String,
        @PathVariable paragraphName: String) : ParagraphInfos? =
        chapter4Service.getParagraphInfos(chapterName, paragraphName)

    @GetMapping("/consult/{patientSsin}")
    fun agreementRequestsConsultation(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable patientSsin: String,
        @RequestParam patientDateOfBirth: Long,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) civicsVersion: String? = null,
        @RequestParam(required = false) paragraph: String? = null,
        @RequestParam(required = false) start: Long? = null,
        @RequestParam(required = false) end: Long? = null,
        @RequestParam(required = false) reference: String? = null) = chapter4Service.agreementRequestsConsultation(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        patientSsin = patientSsin,
        patientDateOfBirth = patientDateOfBirth,
        patientFirstName = patientFirstName,
        patientLastName = patientLastName,
        patientGender = patientGender,
        civicsVersion = civicsVersion,
        paragraph = paragraph,
        start = start ?: LocalDate.now().minusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        end = end,
        reference = reference)

    @PostMapping("/new/{patientSsin}/{civicsVersion}/{requestType}/{paragraph}")
    fun requestAgreement(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
                         @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
                         @RequestParam hcpNihii: String,
                         @RequestParam hcpSsin: String,
                         @RequestParam hcpFirstName: String,
                         @RequestParam hcpLastName: String,
                         @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
                         @PathVariable patientSsin: String,
                         @RequestParam patientDateOfBirth: Long,
                         @RequestParam patientFirstName: String,
                         @RequestParam patientLastName: String,
                         @RequestParam patientGender: String,
                         @PathVariable requestType: String,
                         @PathVariable civicsVersion: String,
                         @PathVariable paragraph: String,
                         @RequestParam verses: String,
                         @RequestParam(required = false) incomplete: Boolean = false,
                         @RequestParam(required = false) start: Long? = null,
                         @RequestParam(required = false) end: Long? = null,
                         @RequestParam(required = false) decisionReference: String? = null,
                         @RequestParam(required = false) ioRequestReference: String? = null,
                         @RequestBody appendices: List<Appendix>
                        ) =
        chapter4Service.requestAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            patientSsin = patientSsin,
            patientDateOfBirth = patientDateOfBirth,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            requestType = RequestType.valueOf(requestType),
            civicsVersion = civicsVersion,
            paragraph = paragraph,
            verses = verses.split(","),
            incomplete = incomplete ?: false,
            start = start
                ?: LocalDate.now().minusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            end = end,
            decisionReference = decisionReference,
            ioRequestReference = ioRequestReference,
            appendices = appendices)

    @DeleteMapping("/cancel/{patientSsin}")
    fun cancelAgreement(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
                        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
                        @RequestParam hcpNihii: String,
                        @RequestParam hcpSsin: String,
                        @RequestParam hcpFirstName: String,
                        @RequestParam hcpLastName: String,
                        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
                        @PathVariable patientSsin: String,
                        @RequestParam decisionReference: String?,
                        @RequestParam iorequestReference: String?) =
        chapter4Service.cancelAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            passPhrase = passPhrase,
            patientSsin = patientSsin,
            decisionReference = decisionReference,
            iorequestReference = iorequestReference
                                       )

    @DeleteMapping("/close/{patientSsin}")
    fun closeAgreement(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
                       @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
                       @RequestParam hcpNihii: String,
                       @RequestParam hcpSsin: String,
                       @RequestParam hcpFirstName: String,
                       @RequestParam hcpLastName: String,
                       @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
                       @PathVariable patientSsin: String,
                       @RequestParam decisionReference: String) =
        chapter4Service.closeAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            passPhrase = passPhrase,
            patientSsin = patientSsin,
            decisionReference = decisionReference
                                      )
}
