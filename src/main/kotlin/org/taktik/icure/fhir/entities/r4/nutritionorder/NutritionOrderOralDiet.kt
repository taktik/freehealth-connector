//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.nutritionorder

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Oral diet components
 *
 * Diet given orally in contrast to enteral (tube) feeding.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class NutritionOrderOralDiet(
  override var extension: List<Extension> = listOf(),
  var fluidConsistencyType: List<CodeableConcept> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Instructions or additional information about the oral diet
   */
  var instruction: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var nutrient: List<NutritionOrderOralDietNutrient> = listOf(),
  var schedule: List<Timing> = listOf(),
  var texture: List<NutritionOrderOralDietTexture> = listOf(),
  var type: List<CodeableConcept> = listOf()
) : BackboneElement
