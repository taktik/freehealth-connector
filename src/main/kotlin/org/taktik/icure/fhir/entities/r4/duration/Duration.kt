//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.duration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * A length of time
 *
 * A length of time.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Duration(
  /**
   * Coded form of the unit
   */
  override var code: String? = null,
  /**
   * < | <= | >= | > - how to understand the value
   */
  override var comparator: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * System that defines coded unit form
   */
  override var system: String? = null,
  /**
   * Unit representation
   */
  override var unit: String? = null,
  /**
   * Numerical value (with implicit precision)
   */
  override var value: Float? = null
) : Quantity
