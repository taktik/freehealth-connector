//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testscript

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Capabilities  that are assumed to function correctly on the FHIR server being tested
 *
 * Capabilities that must exist and are assumed to function correctly on the FHIR server being
 * tested.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TestScriptMetadataCapability(
  /**
   * Required Capability Statement
   */
  var capabilities: String? = null,
  /**
   * The expected capabilities of the server
   */
  var description: String? = null,
  /**
   * Which server these requirements apply to
   */
  var destination: Int? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var link: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var origin: List<Int> = listOf(),
  /**
   * Are the capabilities required?
   */
  var required: Boolean? = null,
  /**
   * Are the capabilities validated?
   */
  var validated: Boolean? = null
) : BackboneElement
