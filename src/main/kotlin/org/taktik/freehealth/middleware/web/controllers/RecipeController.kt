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

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.domain.Feedback
import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.domain.Patient
import org.taktik.freehealth.middleware.domain.Prescription
import org.taktik.freehealth.middleware.domain.PrescriptionFullWithFeedback
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.dto.recipe.PrescriptionRequest
import org.taktik.freehealth.middleware.service.RecipeService
import java.util.*

@RestController
@RequestMapping("/recipe")
class RecipeController(val recipeService: RecipeService) {

    @PostMapping("")
    fun createPrescription(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam passPhrase: String, @RequestBody prescription : PrescriptionRequest): Prescription =
            recipeService.createPrescription(
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
                    deliveryDate = prescription.deliveryDate
            )

    @GetMapping("")
    fun listOpenPrescriptions(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam passPhrase: String): List<Prescription> =
            recipeService.listOpenPrescriptions(
                    keystoreId = keystoreId,
                    tokenId = tokenId,
                    hcpQuality = hcpQuality,
                    hcpNihii = hcpNihii,
                    hcpSsin = hcpSsin,
                    hcpName = hcpName,
                    passPhrase = passPhrase
            )

    @PostMapping("/notify/{rid}")
    fun sendNotification(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam passPhrase: String, patientId: String, executorId: String, @PathVariable rid: String, text: String) =
            recipeService.sendNotification(
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
    fun revokePrescription(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam passPhrase: String, @PathVariable rid: String, @RequestParam reason: String) =
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
    fun updateFeedbackFlag(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam passPhrase: String, @PathVariable rid: String, @PathVariable feedbackFlag: Boolean) =
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

    @GetMapping("/all/feedbacks")
    fun listFeedbacks(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam hcpQuality: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam passPhrase: String): List<Feedback> =
            recipeService.listFeedbacks(
                    keystoreId = keystoreId,
                    tokenId = tokenId,
                    hcpQuality = hcpQuality,
                    hcpNihii = hcpNihii,
                    hcpSsin = hcpSsin,
                    hcpName = hcpName,
                    passPhrase = passPhrase
            )

    @GetMapping("/gal/{galId}")
    fun getGalToAdministrationUnit( @PathVariable galId: String): Code? = recipeService.getGalToAdministrationUnit(galId)

    @GetMapping("/{rid}")
    fun getPrescription(@PathVariable rid: String): PrescriptionFullWithFeedback? = recipeService.getPrescription(rid)
}