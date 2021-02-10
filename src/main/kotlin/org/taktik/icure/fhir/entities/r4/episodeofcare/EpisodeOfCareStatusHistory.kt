//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.episodeofcare

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Past list of status codes (the current status may be included to cover the start date of the
 * status)
 *
 * The history of statuses that the EpisodeOfCare has been through (without requiring processing the
 * history of the resource).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class EpisodeOfCareStatusHistory(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Duration the EpisodeOfCare was in the specified status
   */
  var period: Period,
  /**
   * planned | waitlist | active | onhold | finished | cancelled | entered-in-error
   */
  var status: String? = null
) : BackboneElement
