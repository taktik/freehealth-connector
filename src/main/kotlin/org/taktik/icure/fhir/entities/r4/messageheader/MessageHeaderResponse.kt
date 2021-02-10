//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.messageheader

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * If this is a reply to prior message
 *
 * Information about the message that this message is a response to.  Only present if this message
 * is a response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MessageHeaderResponse(
  /**
   * ok | transient-error | fatal-error
   */
  var code: String? = null,
  /**
   * Specific list of hints/warnings/errors
   */
  var details: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Id of original message
   */
  var identifier: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
