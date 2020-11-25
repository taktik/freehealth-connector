//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.humanname

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Name of a human - parts and usage
 *
 * A human's name with the ability to identify parts and usage.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class HumanName(
  override var extension: List<Extension> = listOf(),
  /**
   * Family name (often called 'Surname')
   */
  var family: String? = null,
  var given: List<String> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Time period when name was/is in use
   */
  var period: Period? = null,
  var prefix: List<String> = listOf(),
  var suffix: List<String> = listOf(),
  /**
   * Text representation of the full name
   */
  var text: String? = null,
  /**
   * usual | official | temp | nickname | anonymous | old | maiden
   */
  var use: String? = null
) : Element
