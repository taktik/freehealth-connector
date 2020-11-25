//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationdispense

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.dosage.Dosage
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Dispensing a medication to a named patient
 *
 * Indicates that a medication product is to be or has been dispensed for a named person/patient.
 * This includes a description of the medication product (supply) provided and the instructions for
 * administering the medication.  The medication dispense is the result of a pharmacy system responding
 * to a medication order.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicationDispense(
  var authorizingPrescription: List<Reference> = listOf(),
  /**
   * Type of medication dispense
   */
  var category: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter / Episode associated with event
   */
  var context: Reference? = null,
  /**
   * Amount of medication expressed as a timing amount
   */
  var daysSupply: Quantity? = null,
  /**
   * Where the medication was sent
   */
  var destination: Reference? = null,
  var detectedIssue: List<Reference> = listOf(),
  var dosageInstruction: List<Dosage> = listOf(),
  var eventHistory: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Where the dispense occurred
   */
  var location: Reference? = null,
  /**
   * What medication was supplied
   */
  var medicationCodeableConcept: CodeableConcept,
  /**
   * What medication was supplied
   */
  var medicationReference: Reference,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var partOf: List<Reference> = listOf(),
  var performer: List<MedicationDispensePerformer> = listOf(),
  /**
   * Amount dispensed
   */
  var quantity: Quantity? = null,
  var receiver: List<Reference> = listOf(),
  /**
   * preparation | in-progress | cancelled | on-hold | completed | entered-in-error | stopped |
   * declined | unknown
   */
  var status: String? = null,
  /**
   * Why a dispense was not performed
   */
  var statusReasonCodeableConcept: CodeableConcept? = null,
  /**
   * Why a dispense was not performed
   */
  var statusReasonReference: Reference? = null,
  /**
   * Who the dispense is for
   */
  var subject: Reference? = null,
  /**
   * Whether a substitution was performed on the dispense
   */
  var substitution: MedicationDispenseSubstitution? = null,
  var supportingInformation: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Trial fill, partial fill, emergency fill, etc.
   */
  var type: CodeableConcept? = null,
  /**
   * When product was given out
   */
  var whenHandedOver: String? = null,
  /**
   * When product was packaged and reviewed
   */
  var whenPrepared: String? = null
) : DomainResource
