//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.plandefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.relatedartifact.RelatedArtifact

/**
 * What the plan is trying to accomplish
 *
 * Goals that describe what the activities within the plan are intended to achieve. For example,
 * weight loss, restoring an activity of daily living, obtaining herd immunity via immunization,
 * meeting a process improvement objective, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PlanDefinitionGoal(
  var addresses: List<CodeableConcept> = listOf(),
  /**
   * E.g. Treatment, dietary, behavioral
   */
  var category: CodeableConcept? = null,
  /**
   * Code or text describing the goal
   */
  var description: CodeableConcept,
  var documentation: List<RelatedArtifact> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * high-priority | medium-priority | low-priority
   */
  var priority: CodeableConcept? = null,
  /**
   * When goal pursuit begins
   */
  var start: CodeableConcept? = null,
  var target: List<PlanDefinitionGoalTarget> = listOf()
) : BackboneElement
