//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.insuranceplan

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.money.Money

/**
 * Overall costs
 *
 * Overall costs associated with the plan.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class InsurancePlanPlanGeneralCost(
  /**
   * Additional cost information
   */
  var comment: String? = null,
  /**
   * Cost value
   */
  var cost: Money? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Number of enrollees
   */
  var groupSize: Int? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Type of cost
   */
  var type: CodeableConcept? = null
) : BackboneElement
