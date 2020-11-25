//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.riskassessment

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * Outcome predicted
 *
 * Describes the expected outcome for the subject.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RiskAssessmentPrediction(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Possible outcome for the subject
   */
  var outcome: CodeableConcept? = null,
  /**
   * Likelihood of specified outcome
   */
  var probabilityDecimal: Float? = null,
  /**
   * Likelihood of specified outcome
   */
  var probabilityRange: Range? = null,
  /**
   * Likelihood of specified outcome as a qualitative value
   */
  var qualitativeRisk: CodeableConcept? = null,
  /**
   * Explanation of prediction
   */
  var rationale: String? = null,
  /**
   * Relative likelihood
   */
  var relativeRisk: Float? = null,
  /**
   * Timeframe or age range
   */
  var whenPeriod: Period? = null,
  /**
   * Timeframe or age range
   */
  var whenRange: Range? = null
) : BackboneElement
