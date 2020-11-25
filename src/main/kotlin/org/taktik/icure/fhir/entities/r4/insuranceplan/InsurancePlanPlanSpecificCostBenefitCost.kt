//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.insuranceplan

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * List of the costs
 *
 * List of the costs associated with a specific benefit.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class InsurancePlanPlanSpecificCostBenefitCost(
  /**
   * in-network | out-of-network | other
   */
  var applicability: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var qualifiers: List<CodeableConcept> = listOf(),
  /**
   * Type of cost
   */
  var type: CodeableConcept,
  /**
   * The actual cost value
   */
  var value: Quantity? = null
) : BackboneElement
