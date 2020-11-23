//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.adverseevent

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

/**
 * Medical care, research study or other healthcare event causing physical injury
 *
 * Actual or  potential/avoided event causing unintended physical injury resulting from or
 * contributed to by medical care, a research study or other healthcare setting factors that requires
 * additional monitoring, treatment, or hospitalization, or that results in death.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class AdverseEvent(
  /**
   * actual | potential
   */
  var actuality: String? = null,
  var category: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  var contributor: List<Reference> = listOf(),
  /**
   * When the event occurred
   */
  var date: String? = null,
  /**
   * When the event was detected
   */
  var detected: String? = null,
  /**
   * Encounter created as part of
   */
  var encounter: Reference? = null,
  /**
   * Type of the event itself in relation to the subject
   */
  var event: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Business identifier for the event
   */
  var identifier: Identifier? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Location where adverse event occurred
   */
  var location: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * resolved | recovering | ongoing | resolvedWithSequelae | fatal | unknown
   */
  var outcome: CodeableConcept? = null,
  /**
   * When the event was recorded
   */
  var recordedDate: String? = null,
  /**
   * Who recorded the adverse event
   */
  var recorder: Reference? = null,
  var referenceDocument: List<Reference> = listOf(),
  var resultingCondition: List<Reference> = listOf(),
  /**
   * Seriousness of the event
   */
  var seriousness: CodeableConcept? = null,
  /**
   * mild | moderate | severe
   */
  var severity: CodeableConcept? = null,
  var study: List<Reference> = listOf(),
  /**
   * Subject impacted by event
   */
  var subject: Reference,
  var subjectMedicalHistory: List<Reference> = listOf(),
  var suspectEntity: List<AdverseEventSuspectEntity> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
