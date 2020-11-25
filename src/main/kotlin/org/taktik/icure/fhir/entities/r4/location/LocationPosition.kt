//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.location

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The absolute geographic location
 *
 * The absolute geographic location of the Location, expressed using the WGS84 datum (This is the
 * same co-ordinate system used in KML).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class LocationPosition(
  /**
   * Altitude with WGS84 datum
   */
  var altitude: Float? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Latitude with WGS84 datum
   */
  var latitude: Float? = null,
  /**
   * Longitude with WGS84 datum
   */
  var longitude: Float? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
