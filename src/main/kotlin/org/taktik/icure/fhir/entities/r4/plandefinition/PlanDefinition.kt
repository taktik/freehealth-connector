//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.plandefinition

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
 * The definition of a plan for a series of actions, independent of any specific patient or context
 *
 * This resource allows for the definition of various types of plans as a sharable, consumable, and
 * executable artifact. The resource is general enough to support the description of a broad range of
 * clinical artifacts such as clinical decision support rules, order sets and protocols.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class PlanDefinition(
  var action: List<PlanDefinitionAction> = listOf(),
  /**
   * When the plan definition was approved by publisher
   */
  var approvalDate: String? = null,
  var author: List<ContactDetail> = listOf(),
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
   * Natural language description of the plan definition
   */
  var description: String? = null,
  var editor: List<ContactDetail> = listOf(),
  /**
   * When the plan definition is expected to be used
   */
  var effectivePeriod: Period? = null,
  var endorser: List<ContactDetail> = listOf(),
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var goal: List<PlanDefinitionGoal> = listOf(),
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
   * When the plan definition was last reviewed
   */
  var lastReviewDate: String? = null,
  var library: List<String> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this plan definition (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this plan definition is defined
   */
  var purpose: String? = null,
  var relatedArtifact: List<RelatedArtifact> = listOf(),
  var reviewer: List<ContactDetail> = listOf(),
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Type of individual the plan definition is focused on
   */
  var subjectCodeableConcept: CodeableConcept? = null,
  /**
   * Type of individual the plan definition is focused on
   */
  var subjectReference: Reference? = null,
  /**
   * Subordinate title of the plan definition
   */
  var subtitle: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this plan definition (human friendly)
   */
  var title: String? = null,
  var topic: List<CodeableConcept> = listOf(),
  /**
   * order-set | clinical-protocol | eca-rule | workflow-definition
   */
  var type: CodeableConcept? = null,
  /**
   * Canonical identifier for this plan definition, represented as a URI (globally unique)
   */
  var url: String? = null,
  /**
   * Describes the clinical usage of the plan
   */
  var usage: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the plan definition
   */
  var version: String? = null
) : DomainResource
