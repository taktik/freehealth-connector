//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.graphdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Potential target for the link
 *
 * Potential target for the link.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class GraphDefinitionLinkTarget(
  var compartment: List<GraphDefinitionLinkTargetCompartment> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var link: List<GraphDefinitionLink> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Criteria for reverse lookup
   */
  var params: String? = null,
  /**
   * Profile for the target resource
   */
  var profile: String? = null,
  /**
   * Type of resource this link refers to
   */
  var type: String? = null
) : BackboneElement
