//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.devicemetric

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Measurement, calculation or setting capability of a medical device
 *
 * Describes a measurement, calculation or setting capability of a medical device.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class DeviceMetric(
  var calibration: List<DeviceMetricCalibration> = listOf(),
  /**
   * measurement | setting | calculation | unspecified
   */
  var category: String? = null,
  /**
   * black | red | green | yellow | blue | magenta | cyan | white
   */
  var color: String? = null,
  override var contained: List<Resource> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Describes the measurement repetition time
   */
  var measurementPeriod: Timing? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * on | off | standby | entered-in-error
   */
  var operationalStatus: String? = null,
  /**
   * Describes the link to the parent Device
   */
  var parent: Reference? = null,
  /**
   * Describes the link to the source Device
   */
  var source: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Identity of metric, for example Heart Rate or PEEP Setting
   */
  var type: CodeableConcept,
  /**
   * Unit of Measure for the Metric
   */
  var unit: CodeableConcept? = null
) : DomainResource
