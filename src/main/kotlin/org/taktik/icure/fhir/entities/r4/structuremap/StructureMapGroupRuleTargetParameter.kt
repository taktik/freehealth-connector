//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.structuremap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Parameters to the transform
 *
 * Parameters to the transform.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureMapGroupRuleTargetParameter(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Parameter value - variable or literal
   */
  var valueBoolean: Boolean? = null,
  /**
   * Parameter value - variable or literal
   */
  var valueDecimal: Float? = null,
  /**
   * Parameter value - variable or literal
   */
  var valueId: String? = null,
  /**
   * Parameter value - variable or literal
   */
  var valueInteger: Int? = null,
  /**
   * Parameter value - variable or literal
   */
  var valueString: String? = null
) : BackboneElement
