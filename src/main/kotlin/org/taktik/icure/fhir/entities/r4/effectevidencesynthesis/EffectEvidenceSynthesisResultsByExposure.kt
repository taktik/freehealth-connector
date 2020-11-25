//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.effectevidencesynthesis

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * What was the result per exposure?
 *
 * A description of the results for each exposure considered in the effect estimate.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class EffectEvidenceSynthesisResultsByExposure(
  /**
   * Description of results by exposure
   */
  var description: String? = null,
  /**
   * exposure | exposure-alternative
   */
  var exposureState: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Risk evidence synthesis
   */
  var riskEvidenceSynthesis: Reference,
  /**
   * Variant exposure states
   */
  var variantState: CodeableConcept? = null
) : BackboneElement
