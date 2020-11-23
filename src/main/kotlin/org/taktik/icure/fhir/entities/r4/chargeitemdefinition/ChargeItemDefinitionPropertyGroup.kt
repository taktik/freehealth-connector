//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.chargeitemdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Group of properties which are applicable under the same conditions
 *
 * Group of properties which are applicable under the same conditions. If no applicability rules are
 * established for the group, then all properties always apply.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ChargeItemDefinitionPropertyGroup(
  var applicability: List<ChargeItemDefinitionApplicability> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var priceComponent: List<ChargeItemDefinitionPropertyGroupPriceComponent> = listOf()
) : BackboneElement
