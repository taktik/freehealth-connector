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
 * Structure Definition used by this map
 *
 * A structure definition used by this map. The structure definition may describe instances that are
 * converted, or the instances that are produced.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureMapStructure(
  /**
   * Name for type in this map
   */
  var alias: String? = null,
  /**
   * Documentation on use of structure
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * source | queried | target | produced
   */
  var mode: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Canonical reference to structure definition
   */
  var url: String? = null
) : BackboneElement
