//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.imagingstudy

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A set of images produced in single study (one or more series of references images)
 *
 * Representation of the content produced in a DICOM imaging study. A study comprises a set of
 * series, each of which includes a set of Service-Object Pair Instances (SOP Instances - images or
 * other data) acquired or produced in a common context.  A series is of only one modality (e.g. X-ray,
 * CT, MR, ultrasound), but a study may have multiple series of different modalities.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ImagingStudy(
  var basedOn: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Institution-generated description
   */
  var description: String? = null,
  /**
   * Encounter with which this imaging study is associated
   */
  var encounter: Reference? = null,
  var endpoint: List<Reference> = listOf(),
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
  var interpreter: List<Reference> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Where ImagingStudy occurred
   */
  var location: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  var modality: List<Coding> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Number of Study Related Instances
   */
  var numberOfInstances: Int? = null,
  /**
   * Number of Study Related Series
   */
  var numberOfSeries: Int? = null,
  var procedureCode: List<CodeableConcept> = listOf(),
  /**
   * The performed Procedure reference
   */
  var procedureReference: Reference? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * Referring physician
   */
  var referrer: Reference? = null,
  var series: List<ImagingStudySeries> = listOf(),
  /**
   * When the study was started
   */
  var started: String? = null,
  /**
   * registered | available | cancelled | entered-in-error | unknown
   */
  var status: String? = null,
  /**
   * Who or what is the subject of the study
   */
  var subject: Reference,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
