//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.task

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.annotation.JsonProperty
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
 * A task to be performed
 *
 * A task to be performed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Task(
  /**
   * Task Creation Date
   */
  var authoredOn: String? = null,
  var basedOn: List<Reference> = listOf(),
  /**
   * E.g. "Specimen collected", "IV prepped"
   */
  var businessStatus: CodeableConcept? = null,
  /**
   * Task Type
   */
  var code: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Human-readable explanation of task
   */
  var description: String? = null,
  /**
   * Healthcare event during which this task originated
   */
  var encounter: Reference? = null,
  /**
   * Start and end time of execution
   */
  var executionPeriod: Period? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * What task is acting on
   */
  var focus: Reference? = null,
  /**
   * Beneficiary of the Task
   */
  @JsonProperty("for")
  var for_fhir: Reference? = null,
  /**
   * Requisition or grouper id
   */
  var groupIdentifier: Identifier? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var input: List<TaskInput> = listOf(),
  /**
   * Formal definition of task
   */
  var instantiatesCanonical: String? = null,
  /**
   * Formal definition of task
   */
  var instantiatesUri: String? = null,
  var insurance: List<Reference> = listOf(),
  /**
   * unknown | proposal | plan | order | original-order | reflex-order | filler-order |
   * instance-order | option
   */
  var intent: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Task Last Modified Date
   */
  var lastModified: String? = null,
  /**
   * Where task occurs
   */
  var location: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var output: List<TaskOutput> = listOf(),
  /**
   * Responsible individual
   */
  var owner: Reference? = null,
  var partOf: List<Reference> = listOf(),
  var performerType: List<CodeableConcept> = listOf(),
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  /**
   * Why task is needed
   */
  var reasonCode: CodeableConcept? = null,
  /**
   * Why task is needed
   */
  var reasonReference: Reference? = null,
  var relevantHistory: List<Reference> = listOf(),
  /**
   * Who is asking for task to be done
   */
  var requester: Reference? = null,
  /**
   * Constraints on fulfillment tasks
   */
  var restriction: TaskRestriction? = null,
  /**
   * draft | requested | received | accepted | +
   */
  var status: String? = null,
  /**
   * Reason for current status
   */
  var statusReason: CodeableConcept? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
