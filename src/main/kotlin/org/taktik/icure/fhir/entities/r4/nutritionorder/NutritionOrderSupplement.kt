//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.nutritionorder

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Supplement components
 *
 * Oral nutritional products given in order to add further nutritional value to the patient's diet.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class NutritionOrderSupplement(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Instructions or additional information about the oral supplement
   */
  var instruction: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Product or brand name of the nutritional supplement
   */
  var productName: String? = null,
  /**
   * Amount of the nutritional supplement
   */
  var quantity: Quantity? = null,
  var schedule: List<Timing> = listOf(),
  /**
   * Type of supplement product requested
   */
  var type: CodeableConcept? = null
) : BackboneElement
