//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.terminologycapabilities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Version of Code System supported
 *
 * For the code system, a list of versions that are supported by the server.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TerminologyCapabilitiesCodeSystemVersion(
  /**
   * Version identifier for this version
   */
  var code: String? = null,
  /**
   * If compositional grammar is supported
   */
  var compositional: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var filter: List<TerminologyCapabilitiesCodeSystemVersionFilter> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * If this is the default version for this code system
   */
  var isDefault: Boolean? = null,
  var language: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var property: List<String> = listOf()
) : BackboneElement
