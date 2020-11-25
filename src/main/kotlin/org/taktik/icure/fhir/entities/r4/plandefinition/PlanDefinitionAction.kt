//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.plandefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.datarequirement.DataRequirement
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.relatedartifact.RelatedArtifact
import org.taktik.icure.fhir.entities.r4.timing.Timing
import org.taktik.icure.fhir.entities.r4.triggerdefinition.TriggerDefinition

/**
 * Action defined by the plan
 *
 * An action or group of actions to be taken as part of the plan.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PlanDefinitionAction(
  var action: List<PlanDefinitionAction> = listOf(),
  /**
   * single | multiple
   */
  var cardinalityBehavior: String? = null,
  var code: List<CodeableConcept> = listOf(),
  var condition: List<PlanDefinitionActionCondition> = listOf(),
  /**
   * Description of the activity to be performed
   */
  var definitionCanonical: String? = null,
  /**
   * Description of the activity to be performed
   */
  var definitionUri: String? = null,
  /**
   * Brief description of the action
   */
  var description: String? = null,
  var documentation: List<RelatedArtifact> = listOf(),
  var dynamicValue: List<PlanDefinitionActionDynamicValue> = listOf(),
  override var extension: List<Extension> = listOf(),
  var goalId: List<String> = listOf(),
  /**
   * visual-group | logical-group | sentence-group
   */
  var groupingBehavior: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var input: List<DataRequirement> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var output: List<DataRequirement> = listOf(),
  var participant: List<PlanDefinitionActionParticipant> = listOf(),
  /**
   * yes | no
   */
  var precheckBehavior: String? = null,
  /**
   * User-visible prefix for the action (e.g. 1. or A.)
   */
  var prefix: String? = null,
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  var reason: List<CodeableConcept> = listOf(),
  var relatedAction: List<PlanDefinitionActionRelatedAction> = listOf(),
  /**
   * must | could | must-unless-documented
   */
  var requiredBehavior: String? = null,
  /**
   * any | all | all-or-none | exactly-one | at-most-one | one-or-more
   */
  var selectionBehavior: String? = null,
  /**
   * Type of individual the action is focused on
   */
  var subjectCodeableConcept: CodeableConcept? = null,
  /**
   * Type of individual the action is focused on
   */
  var subjectReference: Reference? = null,
  /**
   * Static text equivalent of the action, used if the dynamic aspects cannot be interpreted by the
   * receiving system
   */
  var textEquivalent: String? = null,
  /**
   * When the action should take place
   */
  var timingAge: Age? = null,
  /**
   * When the action should take place
   */
  var timingDateTime: String? = null,
  /**
   * When the action should take place
   */
  var timingDuration: Duration? = null,
  /**
   * When the action should take place
   */
  var timingPeriod: Period? = null,
  /**
   * When the action should take place
   */
  var timingRange: Range? = null,
  /**
   * When the action should take place
   */
  var timingTiming: Timing? = null,
  /**
   * User-visible title
   */
  var title: String? = null,
  /**
   * Transform to apply the template
   */
  var transform: String? = null,
  var trigger: List<TriggerDefinition> = listOf(),
  /**
   * create | update | remove | fire-event
   */
  var type: CodeableConcept? = null
) : BackboneElement
