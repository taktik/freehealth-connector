//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.activitydefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.dosage.Dosage
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.relatedartifact.RelatedArtifact
import org.taktik.icure.fhir.entities.r4.timing.Timing
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * The definition of a specific activity to be taken, independent of any particular patient or
 * context
 *
 * This resource allows for the definition of some activity to be performed, independent of a
 * particular patient, practitioner, or other performance context.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ActivityDefinition(
  /**
   * When the activity definition was approved by publisher
   */
  var approvalDate: String? = null,
  var author: List<ContactDetail> = listOf(),
  var bodySite: List<CodeableConcept> = listOf(),
  /**
   * Detail type of activity
   */
  var code: CodeableConcept? = null,
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
   * Natural language description of the activity definition
   */
  var description: String? = null,
  /**
   * True if the activity should not be performed
   */
  var doNotPerform: Boolean? = null,
  var dosage: List<Dosage> = listOf(),
  var dynamicValue: List<ActivityDefinitionDynamicValue> = listOf(),
  var editor: List<ContactDetail> = listOf(),
  /**
   * When the activity definition is expected to be used
   */
  var effectivePeriod: Period? = null,
  var endorser: List<ContactDetail> = listOf(),
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
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
   * proposal | plan | directive | order | original-order | reflex-order | filler-order |
   * instance-order | option
   */
  var intent: String? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * Kind of resource
   */
  var kind: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * When the activity definition was last reviewed
   */
  var lastReviewDate: String? = null,
  var library: List<String> = listOf(),
  /**
   * Where it should happen
   */
  var location: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this activity definition (computer friendly)
   */
  var name: String? = null,
  var observationRequirement: List<Reference> = listOf(),
  var observationResultRequirement: List<Reference> = listOf(),
  var participant: List<ActivityDefinitionParticipant> = listOf(),
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  /**
   * What's administered/supplied
   */
  var productCodeableConcept: CodeableConcept? = null,
  /**
   * What's administered/supplied
   */
  var productReference: Reference? = null,
  /**
   * What profile the resource needs to conform to
   */
  var profile: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this activity definition is defined
   */
  var purpose: String? = null,
  /**
   * How much is administered/consumed/supplied
   */
  var quantity: Quantity? = null,
  var relatedArtifact: List<RelatedArtifact> = listOf(),
  var reviewer: List<ContactDetail> = listOf(),
  var specimenRequirement: List<Reference> = listOf(),
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Type of individual the activity definition is intended for
   */
  var subjectCodeableConcept: CodeableConcept? = null,
  /**
   * Type of individual the activity definition is intended for
   */
  var subjectReference: Reference? = null,
  /**
   * Subordinate title of the activity definition
   */
  var subtitle: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * When activity is to occur
   */
  var timingAge: Age? = null,
  /**
   * When activity is to occur
   */
  var timingDateTime: String? = null,
  /**
   * When activity is to occur
   */
  var timingDuration: Duration? = null,
  /**
   * When activity is to occur
   */
  var timingPeriod: Period? = null,
  /**
   * When activity is to occur
   */
  var timingRange: Range? = null,
  /**
   * When activity is to occur
   */
  var timingTiming: Timing? = null,
  /**
   * Name for this activity definition (human friendly)
   */
  var title: String? = null,
  var topic: List<CodeableConcept> = listOf(),
  /**
   * Transform to apply the template
   */
  var transform: String? = null,
  /**
   * Canonical identifier for this activity definition, represented as a URI (globally unique)
   */
  var url: String? = null,
  /**
   * Describes the clinical usage of the activity definition
   */
  var usage: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the activity definition
   */
  var version: String? = null
) : DomainResource
