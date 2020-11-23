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
 * Transform Rule from source to target
 *
 * Transform Rule from source to target.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureMapGroupRule(
  var dependent: List<StructureMapGroupRuleDependent> = listOf(),
  /**
   * Documentation for this instance of data
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name of the rule for internal references
   */
  var name: String? = null,
  var rule: List<StructureMapGroupRule> = listOf(),
  var source: List<StructureMapGroupRuleSource> = listOf(),
  var target: List<StructureMapGroupRuleTarget> = listOf()
) : BackboneElement
