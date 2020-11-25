//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.goal

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.ratio.Ratio

/**
 * Target outcome for the goal
 *
 * Indicates what should be done by when.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class GoalTarget(
  /**
   * The target value to be achieved
   */
  var detailBoolean: Boolean? = null,
  /**
   * The target value to be achieved
   */
  var detailCodeableConcept: CodeableConcept? = null,
  /**
   * The target value to be achieved
   */
  var detailInteger: Int? = null,
  /**
   * The target value to be achieved
   */
  var detailQuantity: Quantity? = null,
  /**
   * The target value to be achieved
   */
  var detailRange: Range? = null,
  /**
   * The target value to be achieved
   */
  var detailRatio: Ratio? = null,
  /**
   * The target value to be achieved
   */
  var detailString: String? = null,
  /**
   * Reach goal on or before
   */
  var dueDate: String? = null,
  /**
   * Reach goal on or before
   */
  var dueDuration: Duration? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * The parameter whose value is being tracked
   */
  var measure: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
