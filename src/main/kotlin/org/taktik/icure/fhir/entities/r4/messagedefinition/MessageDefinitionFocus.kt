//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.messagedefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Resource(s) that are the subject of the event
 *
 * Identifies the resource (or resources) that are being addressed by the event.  For example, the
 * Encounter for an admit message or two Account records for a merge.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MessageDefinitionFocus(
  /**
   * Type of resource
   */
  var code: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Maximum number of focuses of this type
   */
  var max: String? = null,
  /**
   * Minimum number of focuses of this type
   */
  var min: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Profile that must be adhered to by focus
   */
  var profile: String? = null
) : BackboneElement
