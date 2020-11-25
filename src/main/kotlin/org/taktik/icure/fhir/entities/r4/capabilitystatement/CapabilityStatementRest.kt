//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.capabilitystatement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * If the endpoint is a RESTful one
 *
 * A definition of the restful capabilities of the solution, if any.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CapabilityStatementRest(
  var compartment: List<String> = listOf(),
  /**
   * General description of implementation
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var interaction: List<CapabilityStatementRestInteraction> = listOf(),
  /**
   * client | server
   */
  var mode: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var operation: List<CapabilityStatementRestResourceOperation> = listOf(),
  var resource: List<CapabilityStatementRestResource> = listOf(),
  var searchParam: List<CapabilityStatementRestResourceSearchParam> = listOf(),
  /**
   * Information about security of implementation
   */
  var security: CapabilityStatementRestSecurity? = null
) : BackboneElement
