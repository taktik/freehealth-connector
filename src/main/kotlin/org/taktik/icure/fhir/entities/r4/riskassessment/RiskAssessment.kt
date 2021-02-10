//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.riskassessment

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
 * Potential outcomes for a subject with likelihood
 *
 * An assessment of the likely outcome(s) for a patient or other subject as well as the likelihood
 * of each outcome.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class RiskAssessment(
  /**
   * Request fulfilled by this assessment
   */
  var basedOn: Reference? = null,
  var basis: List<Reference> = listOf(),
  /**
   * Type of assessment
   */
  var code: CodeableConcept? = null,
  /**
   * Condition assessed
   */
  var condition: Reference? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Where was assessment performed?
   */
  var encounter: Reference? = null,
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
  /**
   * Evaluation mechanism
   */
  var method: CodeableConcept? = null,
  /**
   * How to reduce risk
   */
  var mitigation: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * When was assessment made?
   */
  var occurrenceDateTime: String? = null,
  /**
   * When was assessment made?
   */
  var occurrencePeriod: Period? = null,
  /**
   * Part of this occurrence
   */
  var parent: Reference? = null,
  /**
   * Who did assessment?
   */
  var performer: Reference? = null,
  var prediction: List<RiskAssessmentPrediction> = listOf(),
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * registered | preliminary | final | amended +
   */
  var status: String? = null,
  /**
   * Who/what does assessment apply to?
   */
  var subject: Reference,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
