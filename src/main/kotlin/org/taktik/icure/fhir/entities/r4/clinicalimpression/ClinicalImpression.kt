//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.clinicalimpression

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A clinical assessment performed when planning treatments and management strategies for a patient
 *
 * A record of a clinical assessment performed to determine what problem(s) may affect the patient
 * and before planning the treatments or management strategies that are best to manage a patient's
 * condition. Assessments are often 1:1 with a clinical consultation / encounter,  but this varies
 * greatly depending on the clinical workflow. This resource is called "ClinicalImpression" rather than
 * "ClinicalAssessment" to avoid confusion with the recording of assessment tools such as Apgar score.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ClinicalImpression(
  /**
   * The clinician performing the assessment
   */
  var assessor: Reference? = null,
  /**
   * Kind of assessment performed
   */
  var code: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * When the assessment was documented
   */
  var date: String? = null,
  /**
   * Why/how the assessment was performed
   */
  var description: String? = null,
  /**
   * Time of assessment
   */
  var effectiveDateTime: String? = null,
  /**
   * Time of assessment
   */
  var effectivePeriod: Period? = null,
  /**
   * Encounter created as part of
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  var finding: List<ClinicalImpressionFinding> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var investigation: List<ClinicalImpressionInvestigation> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Reference to last assessment
   */
  var previous: Reference? = null,
  var problem: List<Reference> = listOf(),
  var prognosisCodeableConcept: List<CodeableConcept> = listOf(),
  var prognosisReference: List<Reference> = listOf(),
  var protocol: List<String> = listOf(),
  /**
   * in-progress | completed | entered-in-error
   */
  var status: String? = null,
  /**
   * Reason for current status
   */
  var statusReason: CodeableConcept? = null,
  /**
   * Patient or group assessed
   */
  var subject: Reference,
  /**
   * Summary of the assessment
   */
  var summary: String? = null,
  var supportingInfo: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
