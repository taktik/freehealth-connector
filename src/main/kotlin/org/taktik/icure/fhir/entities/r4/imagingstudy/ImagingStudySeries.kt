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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Each study has one or more series of instances
 *
 * Each study has one or more series of images or other content.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImagingStudySeries(
  /**
   * Body part examined
   */
  var bodySite: Coding? = null,
  /**
   * A short human readable summary of the series
   */
  var description: String? = null,
  var endpoint: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var instance: List<ImagingStudySeriesInstance> = listOf(),
  /**
   * Body part laterality
   */
  var laterality: Coding? = null,
  /**
   * The modality of the instances in the series
   */
  var modality: Coding,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Numeric identifier of this series
   */
  var number: Int? = null,
  /**
   * Number of Series Related Instances
   */
  var numberOfInstances: Int? = null,
  var performer: List<ImagingStudySeriesPerformer> = listOf(),
  var specimen: List<Reference> = listOf(),
  /**
   * When the series started
   */
  var started: String? = null,
  /**
   * DICOM Series Instance UID for the series
   */
  var uid: String? = null
) : BackboneElement
