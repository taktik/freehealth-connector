//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coding

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * A reference to a code defined by a terminology system
 *
 * A reference to a code defined by a terminology system.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Coding(
  /**
   * Symbol in syntax defined by the system
   */
  var code: String? = null,
  /**
   * Representation defined by the system
   */
  var display: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Identity of the terminology system
   */
  var system: String? = null,
  /**
   * If this coding was chosen directly by the user
   */
  var userSelected: Boolean? = null,
  /**
   * Version of the system - if relevant
   */
  var version: String? = null
) : Element
