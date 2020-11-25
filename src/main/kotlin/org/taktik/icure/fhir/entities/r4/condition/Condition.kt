//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.condition

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
 * Detailed information about conditions, problems or diagnoses
 *
 * A clinical condition, problem, diagnosis, or other event, situation, issue, or clinical concept
 * that has risen to a level of concern.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Condition(
  /**
   * When in resolution/remission
   */
  var abatementAge: Age? = null,
  /**
   * When in resolution/remission
   */
  var abatementDateTime: String? = null,
  /**
   * When in resolution/remission
   */
  var abatementPeriod: Period? = null,
  /**
   * When in resolution/remission
   */
  var abatementRange: Range? = null,
  /**
   * When in resolution/remission
   */
  var abatementString: String? = null,
  /**
   * Person who asserts this condition
   */
  var asserter: Reference? = null,
  var bodySite: List<CodeableConcept> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  /**
   * active | recurrence | relapse | inactive | remission | resolved
   */
  var clinicalStatus: CodeableConcept? = null,
  /**
   * Identification of the condition, problem or diagnosis
   */
  var code: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter created as part of
   */
  var encounter: Reference? = null,
  var evidence: List<ConditionEvidence> = listOf(),
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
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Estimated or actual date,  date-time, or age
   */
  var onsetAge: Age? = null,
  /**
   * Estimated or actual date,  date-time, or age
   */
  var onsetDateTime: String? = null,
  /**
   * Estimated or actual date,  date-time, or age
   */
  var onsetPeriod: Period? = null,
  /**
   * Estimated or actual date,  date-time, or age
   */
  var onsetRange: Range? = null,
  /**
   * Estimated or actual date,  date-time, or age
   */
  var onsetString: String? = null,
  /**
   * Date record was first recorded
   */
  var recordedDate: String? = null,
  /**
   * Who recorded the condition
   */
  var recorder: Reference? = null,
  /**
   * Subjective severity of condition
   */
  var severity: CodeableConcept? = null,
  var stage: List<ConditionStage> = listOf(),
  /**
   * Who has the condition?
   */
  var subject: Reference,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * unconfirmed | provisional | differential | confirmed | refuted | entered-in-error
   */
  var verificationStatus: CodeableConcept? = null
) : DomainResource
