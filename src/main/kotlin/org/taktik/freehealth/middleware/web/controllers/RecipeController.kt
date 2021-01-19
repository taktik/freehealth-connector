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

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.domain.recipe.Feedback
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.domain.recipe.PrescriptionFullWithFeedback
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.recipe.PrescriptionRequest
import org.taktik.freehealth.middleware.service.RecipeService
import org.taktik.freehealth.middleware.service.RecipeV4Service
import org.taktik.freehealth.utils.FuzzyValues
import java.util.*

@RestController
@RequestMapping("/recipe")
class RecipeController(val recipeService: RecipeService, val recipeV4Service: RecipeV4Service) {
    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createPrescription(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody prescription: PrescriptionRequest): Prescription =
        recipeV4Service.createPrescription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase,
            patient = prescription.patient!!,
            hcp = prescription.hcp!!,
            feedback = prescription.feedback!!,
            medications = prescription.medications!!,
            prescriptionType = prescription.prescriptionType,
            notification = prescription.notification,
            executorId = prescription.executorId,
            samVersion = prescription.samVersion,
            deliveryDate = prescription.deliveryDate?.let {FuzzyValues.getLocalDateTime(it)},
            expirationDate = prescription.expirationDate?.let {FuzzyValues.getLocalDateTime(it)},
            vendorName = prescription.vendorName,
            packageName = prescription.packageName,
            packageVersion = prescription.packageVersion,
            vendorEmail = prescription.vendorEmail,
            vendorPhone = prescription.vendorPhone,
            vision = prescription.vision
                                          )

    @PostMapping("/v4", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createPrescriptionV4(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody prescription: PrescriptionRequest): Prescription =
        recipeV4Service.createPrescription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase,
            patient = prescription.patient!!,
            hcp = prescription.hcp!!,
            feedback = prescription.feedback!!,
            medications = prescription.medications!!,
            prescriptionType = prescription.prescriptionType,
            notification = prescription.notification,
            executorId = prescription.executorId,
            samVersion = prescription.samVersion,
            deliveryDate = prescription.deliveryDate?.let {FuzzyValues.getLocalDateTime(it)},
            expirationDate = prescription.expirationDate?.let {FuzzyValues.getLocalDateTime(it)},
            vendorName = prescription.vendorName,
            packageName = prescription.packageName,
            packageVersion = prescription.packageVersion,
            vendorEmail = prescription.vendorEmail,
            vendorPhone = prescription.vendorPhone,
            vision = prescription.vision
                                          )

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun listOpenPrescriptions(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String): List<Prescription> =
        recipeService.listOpenPrescriptions(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase
        )

    @GetMapping("/patient", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun listOpenPrescriptionsByPatient(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam patientId: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String): List<Prescription> =
        recipeV4Service.listOpenPrescriptions(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase,
            patientId = patientId
        )

    @PostMapping("/notify/{rid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendNotification(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam patientId: String,
        @RequestParam executorId: String,
        @PathVariable rid: String,
        @RequestParam text: String
    ) = recipeService.sendNotification(
        keystoreId = keystoreId,
        tokenId = tokenId,
        hcpQuality = hcpQuality,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpName = hcpName,
        passPhrase = passPhrase,
        patientId = patientId,
        executorId = executorId,
        rid = rid,
        text = text
    )

    @DeleteMapping("/{rid}")
    fun revokePrescription(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable rid: String, @RequestParam reason: String) =
        recipeService.revokePrescription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase,
            rid = rid,
            reason = reason
        )

    @PutMapping("/{rid}/feedback/{feedbackFlag}")
    fun updateFeedbackFlag(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable rid: String, @PathVariable feedbackFlag: Boolean) =
        recipeService.updateFeedbackFlag(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase,
            rid = rid,
            feedbackFlag = feedbackFlag
        )

    @GetMapping("/all/feedbacks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun listFeedbacks(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String): List<Feedback> =
        recipeService.listFeedbacks(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase
        )

    @GetMapping("/gal/{galId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getGalToAdministrationUnit(@PathVariable galId: String): Code? = recipeService.getGalToAdministrationUnit(galId)

    @GetMapping("/{rid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPrescription(@PathVariable rid: String): PrescriptionFullWithFeedback? = recipeService.getPrescription(rid)

    @GetMapping("/prescription/{rid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPrescriptionMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable rid: String): org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage? =
        recipeService.getPrescriptionMessage(keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase,
            rid = rid)
}
