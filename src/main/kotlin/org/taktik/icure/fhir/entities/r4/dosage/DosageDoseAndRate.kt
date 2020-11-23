//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.dosage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.ratio.Ratio

/**
 * Amount of medication administered
 *
 * The amount of medication administered.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DosageDoseAndRate(
  /**
   * Amount of medication per dose
   */
  var doseQuantity: Quantity? = null,
  /**
   * Amount of medication per dose
   */
  var doseRange: Range? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Amount of medication per unit of time
   */
  var rateQuantity: Quantity? = null,
  /**
   * Amount of medication per unit of time
   */
  var rateRange: Range? = null,
  /**
   * Amount of medication per unit of time
   */
  var rateRatio: Ratio? = null,
  /**
   * The kind of dose or rate specified
   */
  var type: CodeableConcept? = null
) : Element
