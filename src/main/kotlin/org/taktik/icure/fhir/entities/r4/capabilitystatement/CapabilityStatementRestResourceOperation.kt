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
 * Definition of a resource operation
 *
 * Definition of an operation or a named query together with its parameters and their meaning and
 * type. Consult the definition of the operation for details about how to invoke the operation, and the
 * parameters.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CapabilityStatementRestResourceOperation(
  /**
   * The defined operation/query
   */
  var definition: String? = null,
  /**
   * Specific details about operation behavior
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name by which the operation/query is invoked
   */
  var name: String? = null
) : BackboneElement
