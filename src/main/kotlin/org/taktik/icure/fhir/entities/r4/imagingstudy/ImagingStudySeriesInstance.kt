//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.imagingstudy

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * A single SOP instance from the series
 *
 * A single SOP instance within the series, e.g. an image, or presentation state.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImagingStudySeriesInstance(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The number of this instance in the series
   */
  var number: Int? = null,
  /**
   * DICOM class type
   */
  var sopClass: Coding,
  /**
   * Description of instance
   */
  var title: String? = null,
  /**
   * DICOM SOP Instance UID
   */
  var uid: String? = null
) : BackboneElement
