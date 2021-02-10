//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationrequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.dosage.Dosage
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Ordering of medication for patient or group
 *
 * An order or request for both supply of the medication and the instructions for administration of
 * the medication to a patient. The resource is called "MedicationRequest" rather than
 * "MedicationPrescription" or "MedicationOrder" to generalize the use across inpatient and outpatient
 * settings, including care plans, etc., and to harmonize with workflow patterns.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicationRequest(
  /**
   * When request was initially authored
   */
  var authoredOn: String? = null,
  var basedOn: List<Reference> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Overall pattern of medication administration
   */
  var courseOfTherapyType: CodeableConcept? = null,
  var detectedIssue: List<Reference> = listOf(),
  /**
   * Medication supply authorization
   */
  var dispenseRequest: MedicationRequestDispenseRequest? = null,
  /**
   * True if request is prohibiting action
   */
  var doNotPerform: Boolean? = null,
  var dosageInstruction: List<Dosage> = listOf(),
  /**
   * Encounter created as part of encounter/admission/stay
   */
  var encounter: Reference? = null,
  var eventHistory: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Composite request this is part of
   */
  var groupIdentifier: Identifier? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var instantiatesCanonical: List<String> = listOf(),
  var instantiatesUri: List<String> = listOf(),
  var insurance: List<Reference> = listOf(),
  /**
   * proposal | plan | order | original-order | reflex-order | filler-order | instance-order |
   * option
   */
  var intent: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Medication to be taken
   */
  var medicationCodeableConcept: CodeableConcept,
  /**
   * Medication to be taken
   */
  var medicationReference: Reference,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Intended performer of administration
   */
  var performer: Reference? = null,
  /**
   * Desired kind of performer of the medication administration
   */
  var performerType: CodeableConcept? = null,
  /**
   * An order/prescription that is being replaced
   */
  var priorPrescription: Reference? = null,
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * Person who entered the request
   */
  var recorder: Reference? = null,
  /**
   * Reported rather than primary record
   */
  var reportedBoolean: Boolean? = null,
  /**
   * Reported rather than primary record
   */
  var reportedReference: Reference? = null,
  /**
   * Who/What requested the Request
   */
  var requester: Reference? = null,
  /**
   * active | on-hold | cancelled | completed | entered-in-error | stopped | draft | unknown
   */
  var status: String? = null,
  /**
   * Reason for current status
   */
  var statusReason: CodeableConcept? = null,
  /**
   * Who or group medication request is for
   */
  var subject: Reference,
  /**
   * Any restrictions on medication substitution
   */
  var substitution: MedicationRequestSubstitution? = null,
  var supportingInformation: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
