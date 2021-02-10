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
import org.taktik.icure.fhir.entities.r4.ratio.Ratio

/**
 * Strength expressed in terms of a reference substance
 *
 * Strength expressed in terms of a reference substance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductIngredientSpecifiedSubstanceStrengthReferenceStrength(
  var country: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * For when strength is measured at a particular point or distance
   */
  var measurementPoint: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Strength expressed in terms of a reference substance
   */
  var strength: Ratio,
  /**
   * Strength expressed in terms of a reference substance
   */
  var strengthLowLimit: Ratio? = null,
  /**
   * Relevant reference substance
   */
  var substance: CodeableConcept? = null
) : BackboneElement
