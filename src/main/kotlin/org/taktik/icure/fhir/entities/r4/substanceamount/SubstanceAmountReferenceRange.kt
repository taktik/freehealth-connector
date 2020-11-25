//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substanceamount

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Reference range of possible or expected values
 *
 * Reference range of possible or expected values.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceAmountReferenceRange(
  override var extension: List<Extension> = listOf(),
  /**
   * Upper limit possible or expected
   */
  var highLimit: Quantity? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Lower limit possible or expected
   */
  var lowLimit: Quantity? = null
) : Element
