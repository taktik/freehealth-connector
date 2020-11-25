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
 * Named sections for reader convenience
 *
 * Organizes the mapping into manageable chunks for human review/ease of maintenance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureMapGroup(
  /**
   * Additional description/explanation for group
   */
  var documentation: String? = null,
  /**
   * Another group that this group adds rules to
   */
  var extends: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var input: List<StructureMapGroupInput> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Human-readable label
   */
  var name: String? = null,
  var rule: List<StructureMapGroupRule> = listOf(),
  /**
   * none | types | type-and-types
   */
  var typeMode: String? = null
) : BackboneElement
