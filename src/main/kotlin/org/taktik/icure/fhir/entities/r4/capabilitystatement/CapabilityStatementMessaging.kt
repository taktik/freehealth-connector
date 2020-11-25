//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.capabilitystatement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * If messaging is supported
 *
 * A description of the messaging capabilities of the solution.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CapabilityStatementMessaging(
  /**
   * Messaging interface behavior details
   */
  var documentation: String? = null,
  var endpoint: List<CapabilityStatementMessagingEndpoint> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Reliable Message Cache Length (min)
   */
  var reliableCache: Int? = null,
  var supportedMessage: List<CapabilityStatementMessagingSupportedMessage> = listOf()
) : BackboneElement
