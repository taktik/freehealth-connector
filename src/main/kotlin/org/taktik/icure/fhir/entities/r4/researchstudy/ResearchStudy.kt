//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.researchstudy

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
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.relatedartifact.RelatedArtifact

/**
 * Investigation to increase healthcare-related patient-independent knowledge
 *
 * A process where a researcher or organization plans and then executes a series of steps intended
 * to increase the field of healthcare-related knowledge.  This includes studies of safety, efficacy,
 * comparative effectiveness and other information about medications, devices, therapies and other
 * interventional and investigative techniques.  A ResearchStudy involves the gathering of information
 * about human or animal subjects.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ResearchStudy(
  var arm: List<ResearchStudyArm> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  var condition: List<CodeableConcept> = listOf(),
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * What this is study doing
   */
  var description: String? = null,
  var enrollment: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  var focus: List<CodeableConcept> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var keyword: List<CodeableConcept> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var location: List<CodeableConcept> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var objective: List<ResearchStudyObjective> = listOf(),
  var partOf: List<Reference> = listOf(),
  /**
   * When the study began and ended
   */
  var period: Period? = null,
  /**
   * n-a | early-phase-1 | phase-1 | phase-1-phase-2 | phase-2 | phase-2-phase-3 | phase-3 | phase-4
   */
  var phase: CodeableConcept? = null,
  /**
   * treatment | prevention | diagnostic | supportive-care | screening | health-services-research |
   * basic-science | device-feasibility
   */
  var primaryPurposeType: CodeableConcept? = null,
  /**
   * Researcher who oversees multiple aspects of the study
   */
  var principalInvestigator: Reference? = null,
  var protocol: List<Reference> = listOf(),
  /**
   * accrual-goal-met | closed-due-to-toxicity | closed-due-to-lack-of-study-progress |
   * temporarily-closed-per-study-design
   */
  var reasonStopped: CodeableConcept? = null,
  var relatedArtifact: List<RelatedArtifact> = listOf(),
  var site: List<Reference> = listOf(),
  /**
   * Organization that initiates and is legally responsible for the study
   */
  var sponsor: Reference? = null,
  /**
   * active | administratively-completed | approved | closed-to-accrual |
   * closed-to-accrual-and-intervention | completed | disapproved | in-review |
   * temporarily-closed-to-accrual | temporarily-closed-to-accrual-and-intervention | withdrawn
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this study
   */
  var title: String? = null
) : DomainResource
