//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.requestgroup

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.relatedartifact.RelatedArtifact
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Proposed actions, if any
 *
 * The actions, if any, produced by the evaluation of the artifact.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RequestGroupAction(
  var action: List<RequestGroupAction> = listOf(),
  /**
   * single | multiple
   */
  var cardinalityBehavior: String? = null,
  var code: List<CodeableConcept> = listOf(),
  var condition: List<RequestGroupActionCondition> = listOf(),
  /**
   * Short description of the action
   */
  var description: String? = null,
  var documentation: List<RelatedArtifact> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * visual-group | logical-group | sentence-group
   */
  var groupingBehavior: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var participant: List<Reference> = listOf(),
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
  var relatedAction: List<RequestGroupActionRelatedAction> = listOf(),
  /**
   * must | could | must-unless-documented
   */
  var requiredBehavior: String? = null,
  /**
   * The target of the action
   */
  var resource: Reference? = null,
  /**
   * any | all | all-or-none | exactly-one | at-most-one | one-or-more
   */
  var selectionBehavior: String? = null,
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
   * create | update | remove | fire-event
   */
  var type: CodeableConcept? = null
) : BackboneElement
