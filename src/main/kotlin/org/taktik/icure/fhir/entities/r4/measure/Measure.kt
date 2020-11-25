//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.measure

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
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
 * A quality measure definition
 *
 * The Measure resource provides the definition of a quality measure.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Measure(
  /**
   * When the measure was approved by publisher
   */
  var approvalDate: String? = null,
  var author: List<ContactDetail> = listOf(),
  /**
   * Summary of clinical guidelines
   */
  var clinicalRecommendationStatement: String? = null,
  /**
   * opportunity | all-or-nothing | linear | weighted
   */
  var compositeScoring: CodeableConcept? = null,
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
  var definition: List<String> = listOf(),
  /**
   * Natural language description of the measure
   */
  var description: String? = null,
  /**
   * Disclaimer for use of the measure or its referenced content
   */
  var disclaimer: String? = null,
  var editor: List<ContactDetail> = listOf(),
  /**
   * When the measure is expected to be used
   */
  var effectivePeriod: Period? = null,
  var endorser: List<ContactDetail> = listOf(),
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var group: List<MeasureGroup> = listOf(),
  /**
   * Additional guidance for implementers
   */
  var guidance: String? = null,
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
   * increase | decrease
   */
  var improvementNotation: CodeableConcept? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * When the measure was last reviewed
   */
  var lastReviewDate: String? = null,
  var library: List<String> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this measure (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this measure is defined
   */
  var purpose: String? = null,
  /**
   * How is rate aggregation performed for this measure
   */
  var rateAggregation: String? = null,
  /**
   * Detailed description of why the measure exists
   */
  var rationale: String? = null,
  var relatedArtifact: List<RelatedArtifact> = listOf(),
  var reviewer: List<ContactDetail> = listOf(),
  /**
   * How risk adjustment is applied for this measure
   */
  var riskAdjustment: String? = null,
  /**
   * proportion | ratio | continuous-variable | cohort
   */
  var scoring: CodeableConcept? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * E.g. Patient, Practitioner, RelatedPerson, Organization, Location, Device
   */
  var subjectCodeableConcept: CodeableConcept? = null,
  /**
   * E.g. Patient, Practitioner, RelatedPerson, Organization, Location, Device
   */
  var subjectReference: Reference? = null,
  /**
   * Subordinate title of the measure
   */
  var subtitle: String? = null,
  var supplementalData: List<MeasureSupplementalData> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this measure (human friendly)
   */
  var title: String? = null,
  var topic: List<CodeableConcept> = listOf(),
  var type: List<CodeableConcept> = listOf(),
  /**
   * Canonical identifier for this measure, represented as a URI (globally unique)
   */
  var url: String? = null,
  /**
   * Describes the clinical usage of the measure
   */
  var usage: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the measure
   */
  var version: String? = null
) : DomainResource
