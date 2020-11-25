//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.observation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.sampleddata.SampledData
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Measurements and simple assertions
 *
 * Measurements and simple assertions made about a patient, device or other subject.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Observation(
  var basedOn: List<Reference> = listOf(),
  /**
   * Observed body part
   */
  var bodySite: CodeableConcept? = null,
  var category: List<CodeableConcept> = listOf(),
  /**
   * Type of observation (code / type)
   */
  var code: CodeableConcept,
  var component: List<ObservationComponent> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Why the result is missing
   */
  var dataAbsentReason: CodeableConcept? = null,
  var derivedFrom: List<Reference> = listOf(),
  /**
   * (Measurement) Device
   */
  var device: Reference? = null,
  /**
   * Clinically relevant time/time-period for observation
   */
  var effectiveDateTime: String? = null,
  /**
   * Clinically relevant time/time-period for observation
   */
  var effectiveInstant: String? = null,
  /**
   * Clinically relevant time/time-period for observation
   */
  var effectivePeriod: Period? = null,
  /**
   * Clinically relevant time/time-period for observation
   */
  var effectiveTiming: Timing? = null,
  /**
   * Healthcare event during which this observation is made
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  var focus: List<Reference> = listOf(),
  var hasMember: List<Reference> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var interpretation: List<CodeableConcept> = listOf(),
  /**
   * Date/Time this version was made available
   */
  var issued: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  /**
   * How it was done
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var partOf: List<Reference> = listOf(),
  var performer: List<Reference> = listOf(),
  var referenceRange: List<ObservationReferenceRange> = listOf(),
  /**
   * Specimen used for this observation
   */
  var specimen: Reference? = null,
  /**
   * registered | preliminary | final | amended +
   */
  var status: String? = null,
  /**
   * Who and/or what the observation is about
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Actual result
   */
  var valueBoolean: Boolean? = null,
  /**
   * Actual result
   */
  var valueCodeableConcept: CodeableConcept? = null,
  /**
   * Actual result
   */
  var valueDateTime: String? = null,
  /**
   * Actual result
   */
  var valueInteger: Int? = null,
  /**
   * Actual result
   */
  var valuePeriod: Period? = null,
  /**
   * Actual result
   */
  var valueQuantity: Quantity? = null,
  /**
   * Actual result
   */
  var valueRange: Range? = null,
  /**
   * Actual result
   */
  var valueRatio: Ratio? = null,
  /**
   * Actual result
   */
  var valueSampledData: SampledData? = null,
  /**
   * Actual result
   */
  var valueString: String? = null,
  /**
   * Actual result
   */
  var valueTime: String? = null
) : DomainResource
