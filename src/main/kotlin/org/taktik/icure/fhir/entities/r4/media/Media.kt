//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.media

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A photo, video, or audio recording acquired or used in healthcare. The actual content may be
 * inline or provided by direct reference
 *
 * A photo, video, or audio recording acquired or used in healthcare. The actual content may be
 * inline or provided by direct reference.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Media(
  var basedOn: List<Reference> = listOf(),
  /**
   * Observed body part
   */
  var bodySite: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Actual Media - reference or data
   */
  var content: Attachment,
  /**
   * When Media was collected
   */
  var createdDateTime: String? = null,
  /**
   * When Media was collected
   */
  var createdPeriod: Period? = null,
  /**
   * Observing Device
   */
  var device: Reference? = null,
  /**
   * Name of the device/manufacturer
   */
  var deviceName: String? = null,
  /**
   * Length in seconds (audio / video)
   */
  var duration: Float? = null,
  /**
   * Encounter associated with media
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Number of frames if > 1 (photo)
   */
  var frames: Int? = null,
  /**
   * Height of the image in pixels (photo/video)
   */
  var height: Int? = null,
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
   * The type of acquisition equipment/process
   */
  var modality: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * The person who generated the image
   */
  var operator: Reference? = null,
  var partOf: List<Reference> = listOf(),
  var reasonCode: List<CodeableConcept> = listOf(),
  /**
   * preparation | in-progress | not-done | on-hold | stopped | completed | entered-in-error |
   * unknown
   */
  var status: String? = null,
  /**
   * Who/What this Media is a record of
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Classification of media as image, video, or audio
   */
  var type: CodeableConcept? = null,
  /**
   * Imaging view, e.g. Lateral or Antero-posterior
   */
  var view: CodeableConcept? = null,
  /**
   * Width of the image in pixels (photo/video)
   */
  var width: Int? = null
) : DomainResource
