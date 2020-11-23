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
 * Named instance provided when invoking the map
 *
 * A name assigned to an instance of data. The instance must be provided when the mapping is
 * invoked.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureMapGroupInput(
  /**
   * Documentation for this instance of data
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * source | target
   */
  var mode: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this instance of data
   */
  var name: String? = null,
  /**
   * Type for this instance of data
   */
  var type: String? = null
) : BackboneElement
