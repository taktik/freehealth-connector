//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.riskevidencesynthesis

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * How precise the estimate is
 *
 * A description of the precision of the estimate for the effect.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RiskEvidenceSynthesisRiskEstimatePrecisionEstimate(
  override var extension: List<Extension> = listOf(),
  /**
   * Lower bound
   */
  @JsonProperty("from")
  var from_fhir: Float? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Level of confidence interval
   */
  var level: Float? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Upper bound
   */
  var to: Float? = null,
  /**
   * Type of precision estimate
   */
  var type: CodeableConcept? = null
) : BackboneElement
