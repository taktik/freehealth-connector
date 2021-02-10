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

/**
 * Enteral formula components
 *
 * Feeding provided through the gastrointestinal tract via a tube, catheter, or stoma that delivers
 * nutrition distal to the oral cavity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class NutritionOrderEnteralFormula(
  /**
   * Product or brand name of the modular additive
   */
  var additiveProductName: String? = null,
  /**
   * Type of modular component to add to the feeding
   */
  var additiveType: CodeableConcept? = null,
  var administration: List<NutritionOrderEnteralFormulaAdministration> = listOf(),
  /**
   * Formula feeding instructions expressed as text
   */
  var administrationInstruction: String? = null,
  /**
   * Product or brand name of the enteral or infant formula
   */
  var baseFormulaProductName: String? = null,
  /**
   * Type of enteral or infant formula
   */
  var baseFormulaType: CodeableConcept? = null,
  /**
   * Amount of energy per specified volume that is required
   */
  var caloricDensity: Quantity? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Upper limit on formula volume per unit of time
   */
  var maxVolumeToDeliver: Quantity? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * How the formula should enter the patient's gastrointestinal tract
   */
  var routeofAdministration: CodeableConcept? = null
) : BackboneElement
