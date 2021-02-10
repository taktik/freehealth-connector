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
 * A code system supported by the server
 *
 * Identifies a code system that is supported by the server. If there is a no code system URL, then
 * this declares the general assumptions a client can make about support for any CodeSystem resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TerminologyCapabilitiesCodeSystem(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Whether subsumption is supported
   */
  var subsumption: Boolean? = null,
  /**
   * URI for the Code System
   */
  var uri: String? = null,
  var version: List<TerminologyCapabilitiesCodeSystemVersion> = listOf()
) : BackboneElement
