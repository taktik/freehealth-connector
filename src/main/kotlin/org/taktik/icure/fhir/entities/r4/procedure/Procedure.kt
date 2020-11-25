//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.procedure

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * An action that is being or was performed on a patient
 *
 * An action that is or was performed on or for a patient. This can be a physical intervention like
 * an operation, or less invasive like long term services, counseling, or hypnotherapy.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Procedure(
  /**
   * Person who asserts this procedure
   */
  var asserter: Reference? = null,
  var basedOn: List<Reference> = listOf(),
  var bodySite: List<CodeableConcept> = listOf(),
  /**
   * Classification of the procedure
   */
  var category: CodeableConcept? = null,
  /**
   * Identification of the procedure
   */
  var code: CodeableConcept? = null,
  var complication: List<CodeableConcept> = listOf(),
  var complicationDetail: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter created as part of
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  var focalDevice: List<ProcedureFocalDevice> = listOf(),
  var followUp: List<CodeableConcept> = listOf(),
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
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Where the procedure happened
   */
  var location: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * The result of procedure
   */
  var outcome: CodeableConcept? = null,
  var partOf: List<Reference> = listOf(),
  /**
   * When the procedure was performed
   */
  var performedAge: Age? = null,
  /**
   * When the procedure was performed
   */
  var performedDateTime: String? = null,
  /**
   * When the procedure was performed
   */
  var performedPeriod: Period? = null,
  /**
   * When the procedure was performed
   */
  var performedRange: Range? = null,
  /**
   * When the procedure was performed
   */
  var performedString: String? = null,
  var performer: List<ProcedurePerformer> = listOf(),
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * Who recorded the procedure
   */
  var recorder: Reference? = null,
  var report: List<Reference> = listOf(),
  /**
   * preparation | in-progress | not-done | on-hold | stopped | completed | entered-in-error |
   * unknown
   */
  var status: String? = null,
  /**
   * Reason for current status
   */
  var statusReason: CodeableConcept? = null,
  /**
   * Who the procedure was performed on
   */
  var subject: Reference,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var usedCode: List<CodeableConcept> = listOf(),
  var usedReference: List<Reference> = listOf()
) : DomainResource
