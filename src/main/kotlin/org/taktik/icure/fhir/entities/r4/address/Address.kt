//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.address

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * An address expressed using postal conventions (as opposed to GPS or other location definition
 * formats)
 *
 * An address expressed using postal conventions (as opposed to GPS or other location definition
 * formats).  This data type may be used to convey addresses for use in delivering mail as well as for
 * visiting locations which might not be valid for mail delivery.  There are a variety of postal
 * address formats defined around the world.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Address(
  /**
   * Name of city, town etc.
   */
  var city: String? = null,
  /**
   * Country (e.g. can be ISO 3166 2 or 3 letter code)
   */
  var country: String? = null,
  /**
   * District name (aka county)
   */
  var district: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var line: List<String> = listOf(),
  /**
   * Time period when address was/is in use
   */
  var period: Period? = null,
  /**
   * Postal code for area
   */
  var postalCode: String? = null,
  /**
   * Sub-unit of country (abbreviations ok)
   */
  var state: String? = null,
  /**
   * Text representation of the address
   */
  var text: String? = null,
  /**
   * postal | physical | both
   */
  var type: String? = null,
  /**
   * home | work | temp | old | billing - purpose of this address
   */
  var use: String? = null
) : Element
