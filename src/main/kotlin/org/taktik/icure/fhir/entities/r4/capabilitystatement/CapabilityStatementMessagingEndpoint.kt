//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.capabilitystatement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Where messages should be sent
 *
 * An endpoint (network accessible address) to which messages and/or replies are to be sent.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CapabilityStatementMessagingEndpoint(
  /**
   * Network address or identifier of the end-point
   */
  var address: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * http | ftp | mllp +
   */
  var protocol: Coding
) : BackboneElement
