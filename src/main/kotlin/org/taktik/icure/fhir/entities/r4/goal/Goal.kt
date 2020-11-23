//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.goal

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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Describes the intended objective(s) for a patient, group or organization
 *
 * Describes the intended objective(s) for a patient, group or organization care, for example,
 * weight loss, restoring an activity of daily living, obtaining herd immunity via immunization,
 * meeting a process improvement objective, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Goal(
  /**
   * in-progress | improving | worsening | no-change | achieved | sustaining | not-achieved |
   * no-progress | not-attainable
   */
  var achievementStatus: CodeableConcept? = null,
  var addresses: List<Reference> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Code or text describing goal
   */
  var description: CodeableConcept,
  /**
   * Who's responsible for creating Goal?
   */
  var expressedBy: Reference? = null,
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
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * proposed | planned | accepted | active | on-hold | completed | cancelled | entered-in-error |
   * rejected
   */
  var lifecycleStatus: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var outcomeCode: List<CodeableConcept> = listOf(),
  var outcomeReference: List<Reference> = listOf(),
  /**
   * high-priority | medium-priority | low-priority
   */
  var priority: CodeableConcept? = null,
  /**
   * When goal pursuit begins
   */
  var startCodeableConcept: CodeableConcept? = null,
  /**
   * When goal pursuit begins
   */
  var startDate: String? = null,
  /**
   * When goal status took effect
   */
  var statusDate: String? = null,
  /**
   * Reason for current status
   */
  var statusReason: String? = null,
  /**
   * Who this goal is intended for
   */
  var subject: Reference,
  var target: List<GoalTarget> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
