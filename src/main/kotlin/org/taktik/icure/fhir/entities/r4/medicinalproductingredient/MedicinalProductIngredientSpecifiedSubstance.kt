//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductingredient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * A specified substance that comprises this ingredient
 *
 * A specified substance that comprises this ingredient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductIngredientSpecifiedSubstance(
  /**
   * The specified substance
   */
  var code: CodeableConcept,
  /**
   * Confidentiality level of the specified substance as the ingredient
   */
  var confidentiality: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * The group of specified substance, e.g. group 1 to 4
   */
  var group: CodeableConcept,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var strength: List<MedicinalProductIngredientSpecifiedSubstanceStrength> = listOf()
) : BackboneElement
