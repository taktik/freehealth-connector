//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.terminologycapabilities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Software that is covered by this terminology capability statement
 *
 * Software that is covered by this terminology capability statement.  It is used when the statement
 * describes the capabilities of a particular software version, independent of an installation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TerminologyCapabilitiesSoftware(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * A name the software is known by
   */
  var name: String? = null,
  /**
   * Version covered by this statement
   */
  var version: String? = null
) : BackboneElement
