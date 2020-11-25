//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.observation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.sampleddata.SampledData

/**
 * Component results
 *
 * Some observations have multiple component observations.  These component observations are
 * expressed as separate code value pairs that share the same attributes.  Examples include systolic
 * and diastolic component observations for blood pressure measurement and multiple component
 * observations for genetics observations.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ObservationComponent(
  /**
   * Type of component observation (code / type)
   */
  var code: CodeableConcept,
  /**
   * Why the component result is missing
   */
  var dataAbsentReason: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var interpretation: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var referenceRange: List<ObservationReferenceRange> = listOf(),
  /**
   * Actual component result
   */
  var valueBoolean: Boolean? = null,
  /**
   * Actual component result
   */
  var valueCodeableConcept: CodeableConcept? = null,
  /**
   * Actual component result
   */
  var valueDateTime: String? = null,
  /**
   * Actual component result
   */
  var valueInteger: Int? = null,
  /**
   * Actual component result
   */
  var valuePeriod: Period? = null,
  /**
   * Actual component result
   */
  var valueQuantity: Quantity? = null,
  /**
   * Actual component result
   */
  var valueRange: Range? = null,
  /**
   * Actual component result
   */
  var valueRatio: Ratio? = null,
  /**
   * Actual component result
   */
  var valueSampledData: SampledData? = null,
  /**
   * Actual component result
   */
  var valueString: String? = null,
  /**
   * Actual component result
   */
  var valueTime: String? = null
) : BackboneElement
