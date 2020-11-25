//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.riskevidencesynthesis

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
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * A quantified estimate of risk based on a body of evidence
 *
 * The RiskEvidenceSynthesis resource describes the likelihood of an outcome in a population plus
 * exposure state where the risk estimate is derived from a combination of research studies.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class RiskEvidenceSynthesis(
  /**
   * When the risk evidence synthesis was approved by publisher
   */
  var approvalDate: String? = null,
  var author: List<ContactDetail> = listOf(),
  var certainty: List<RiskEvidenceSynthesisCertainty> = listOf(),
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Use and/or publishing restrictions
   */
  var copyright: String? = null,
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * Natural language description of the risk evidence synthesis
   */
  var description: String? = null,
  var editor: List<ContactDetail> = listOf(),
  /**
   * When the risk evidence synthesis is expected to be used
   */
  var effectivePeriod: Period? = null,
  var endorser: List<ContactDetail> = listOf(),
  /**
   * What exposure?
   */
  var exposure: Reference? = null,
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
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * When the risk evidence synthesis was last reviewed
   */
  var lastReviewDate: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this risk evidence synthesis (computer friendly)
   */
  var name: String? = null,
  var note: List<Annotation> = listOf(),
  /**
   * What outcome?
   */
  var outcome: Reference,
  /**
   * What population?
   */
  var population: Reference,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  var relatedArtifact: List<RelatedArtifact> = listOf(),
  var reviewer: List<ContactDetail> = listOf(),
  /**
   * What was the estimated risk
   */
  var riskEstimate: RiskEvidenceSynthesisRiskEstimate? = null,
  /**
   * What sample size was involved?
   */
  var sampleSize: RiskEvidenceSynthesisSampleSize? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Type of study
   */
  var studyType: CodeableConcept? = null,
  /**
   * Type of synthesis
   */
  var synthesisType: CodeableConcept? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this risk evidence synthesis (human friendly)
   */
  var title: String? = null,
  var topic: List<CodeableConcept> = listOf(),
  /**
   * Canonical identifier for this risk evidence synthesis, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the risk evidence synthesis
   */
  var version: String? = null
) : DomainResource
