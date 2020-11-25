//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.healthcareservice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Times the Service Site is available
 *
 * A collection of times that the Service Site is available.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class HealthcareServiceAvailableTime(
  /**
   * Always available? e.g. 24 hour service
   */
  var allDay: Boolean? = null,
  /**
   * Closing time of day (ignored if allDay = true)
   */
  var availableEndTime: String? = null,
  /**
   * Opening time of day (ignored if allDay = true)
   */
  var availableStartTime: String? = null,
  var daysOfWeek: List<String> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
