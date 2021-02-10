//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.sampleddata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * A series of measurements taken by a device
 *
 * A series of measurements taken by a device, with upper and lower limits. There may be more than
 * one dimension in the data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SampledData(
  /**
   * Decimal values with spaces, or "E" | "U" | "L"
   */
  var data: String? = null,
  /**
   * Number of sample points at each time point
   */
  var dimensions: Int? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Multiply data by this before adding to origin
   */
  var factor: Float? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Lower limit of detection
   */
  var lowerLimit: Float? = null,
  /**
   * Zero value and units
   */
  var origin: Quantity,
  /**
   * Number of milliseconds between samples
   */
  var period: Float? = null,
  /**
   * Upper limit of detection
   */
  var upperLimit: Float? = null
) : Element
