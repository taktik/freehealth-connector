//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.structuremap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Content to create because of this mapping rule
 *
 * Content to create because of this mapping rule.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureMapGroupRuleTarget(
  /**
   * Type or variable this rule applies to
   */
  var context: String? = null,
  /**
   * type | variable
   */
  var contextType: String? = null,
  /**
   * Field to create in the context
   */
  var element: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var listMode: List<String> = listOf(),
  /**
   * Internal rule reference for shared list items
   */
  var listRuleId: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var parameter: List<StructureMapGroupRuleTargetParameter> = listOf(),
  /**
   * create | copy +
   */
  var transform: String? = null,
  /**
   * Named context for field, if desired, and a field is specified
   */
  var variable: String? = null
) : BackboneElement
