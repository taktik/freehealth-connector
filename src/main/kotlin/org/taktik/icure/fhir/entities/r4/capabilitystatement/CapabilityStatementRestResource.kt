//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.capabilitystatement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Resource served on the REST interface
 *
 * A specification of the restful capabilities of the solution for a specific resource type.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CapabilityStatementRestResource(
  /**
   * If allows/uses conditional create
   */
  var conditionalCreate: Boolean? = null,
  /**
   * not-supported | single | multiple - how conditional delete is supported
   */
  var conditionalDelete: String? = null,
  /**
   * not-supported | modified-since | not-match | full-support
   */
  var conditionalRead: String? = null,
  /**
   * If allows/uses conditional update
   */
  var conditionalUpdate: Boolean? = null,
  /**
   * Additional information about the use of the resource type
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var interaction: List<CapabilityStatementRestResourceInteraction> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var operation: List<CapabilityStatementRestResourceOperation> = listOf(),
  /**
   * Base System profile for all uses of resource
   */
  var profile: String? = null,
  /**
   * Whether vRead can return past versions
   */
  var readHistory: Boolean? = null,
  var referencePolicy: List<String> = listOf(),
  var searchInclude: List<String> = listOf(),
  var searchParam: List<CapabilityStatementRestResourceSearchParam> = listOf(),
  var searchRevInclude: List<String> = listOf(),
  var supportedProfile: List<String> = listOf(),
  /**
   * A resource type that is supported
   */
  var type: String? = null,
  /**
   * If update can commit to a new identity
   */
  var updateCreate: Boolean? = null,
  /**
   * no-version | versioned | versioned-update
   */
  var versioning: String? = null
) : BackboneElement
