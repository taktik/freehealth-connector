//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationadministration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Administration of medication to a patient
 *
 * Describes the event of a patient consuming or otherwise being administered a medication.  This
 * may be as simple as swallowing a tablet or it may be a long running infusion.  Related resources tie
 * this event to the authorizing prescription, and the specific encounter between patient and health
 * care practitioner.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicationAdministration(
  /**
   * Type of medication usage
   */
  var category: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter or Episode of Care administered as part of
   */
  var context: Reference? = null,
  var device: List<Reference> = listOf(),
  /**
   * Details of how medication was taken
   */
  var dosage: MedicationAdministrationDosage? = null,
  /**
   * Start and end time of administration
   */
  var effectiveDateTime: String? = null,
  /**
   * Start and end time of administration
   */
  var effectivePeriod: Period,
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
  var instantiates: List<String> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * What was administered
   */
  var medicationCodeableConcept: CodeableConcept,
  /**
   * What was administered
   */
  var medicationReference: Reference,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var partOf: List<Reference> = listOf(),
  var performer: List<MedicationAdministrationPerformer> = listOf(),
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * Request administration performed against
   */
  var request: Reference? = null,
  /**
   * in-progress | not-done | on-hold | completed | entered-in-error | stopped | unknown
   */
  var status: String? = null,
  var statusReason: List<CodeableConcept> = listOf(),
  /**
   * Who received medication
   */
  var subject: Reference,
  var supportingInformation: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
