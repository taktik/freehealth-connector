//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.location

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * What days/times during a week is this location usually open
 *
 * What days/times during a week is this location usually open.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class LocationHoursOfOperation(
  /**
   * The Location is open all day
   */
  var allDay: Boolean? = null,
  /**
   * Time that the Location closes
   */
  var closingTime: String? = null,
  var daysOfWeek: List<String> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Time that the Location opens
   */
  var openingTime: String? = null
) : BackboneElement
