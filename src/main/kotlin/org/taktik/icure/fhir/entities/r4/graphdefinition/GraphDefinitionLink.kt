//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.graphdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Links this graph makes rules about
 *
 * Links this graph makes rules about.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class GraphDefinitionLink(
  /**
   * Why this link is specified
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Maximum occurrences for this link
   */
  var max: String? = null,
  /**
   * Minimum occurrences for this link
   */
  var min: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Path in the resource that contains the link
   */
  var path: String? = null,
  /**
   * Which slice (if profiled)
   */
  var sliceName: String? = null,
  var target: List<GraphDefinitionLinkTarget> = listOf()
) : BackboneElement
