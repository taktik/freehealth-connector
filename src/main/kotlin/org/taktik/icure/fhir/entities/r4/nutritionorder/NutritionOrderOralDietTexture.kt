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

/**
 * Required  texture modifications
 *
 * Class that describes any texture modifications required for the patient to safely consume various
 * types of solid foods.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class NutritionOrderOralDietTexture(
  override var extension: List<Extension> = listOf(),
  /**
   * Concepts that are used to identify an entity that is ingested for nutritional purposes
   */
  var foodType: CodeableConcept? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Code to indicate how to alter the texture of the foods, e.g. pureed
   */
  var modifier: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
