//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.conceptmap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Same source and target systems
 *
 * A group of mappings that all have the same source and target system.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ConceptMapGroup(
  var element: List<ConceptMapGroupElement> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Source system where concepts to be mapped are defined
   */
  var source: String? = null,
  /**
   * Specific version of the  code system
   */
  var sourceVersion: String? = null,
  /**
   * Target system that the concepts are to be mapped to
   */
  var target: String? = null,
  /**
   * Specific version of the  code system
   */
  var targetVersion: String? = null,
  /**
   * What to do when there is no mapping for the source concept
   */
  var unmapped: ConceptMapGroupUnmapped? = null
) : BackboneElement
