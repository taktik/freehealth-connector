//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contactpoint

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Details of a Technology mediated contact point (phone, fax, email, etc.)
 *
 * Details for all kinds of technology mediated contact points for a person or organization,
 * including telephone, email, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContactPoint(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Time period when the contact point was/is in use
   */
  var period: Period? = null,
  /**
   * Specify preferred order of use (1 = highest)
   */
  var rank: Int? = null,
  /**
   * phone | fax | email | pager | url | sms | other
   */
  var system: String? = null,
  /**
   * home | work | temp | old | mobile - purpose of this contact point
   */
  var use: String? = null,
  /**
   * The actual contact point details
   */
  var value: String? = null
) : Element
