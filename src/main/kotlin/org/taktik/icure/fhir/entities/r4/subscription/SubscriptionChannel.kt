//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.subscription

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The channel on which to report matches to the criteria
 *
 * Details where to send notifications when resources are received that meet the criteria.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubscriptionChannel(
  /**
   * Where the channel points to
   */
  var endpoint: String? = null,
  override var extension: List<Extension> = listOf(),
  var header: List<String> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * MIME type to send, or omit for no payload
   */
  var payload: String? = null,
  /**
   * rest-hook | websocket | email | sms | message
   */
  var type: String? = null
) : BackboneElement
