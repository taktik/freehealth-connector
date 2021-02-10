//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.riskevidencesynthesis

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * What was the estimated risk
 *
 * The estimated risk of the outcome.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RiskEvidenceSynthesisRiskEstimate(
  /**
   * Sample size for group measured
   */
  var denominatorCount: Int? = null,
  /**
   * Description of risk estimate
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Number with the outcome
   */
  var numeratorCount: Int? = null,
  var precisionEstimate: List<RiskEvidenceSynthesisRiskEstimatePrecisionEstimate> = listOf(),
  /**
   * Type of risk estimate
   */
  var type: CodeableConcept? = null,
  /**
   * What unit is the outcome described in?
   */
  var unitOfMeasure: CodeableConcept? = null,
  /**
   * Point estimate
   */
  var value: Float? = null
) : BackboneElement
