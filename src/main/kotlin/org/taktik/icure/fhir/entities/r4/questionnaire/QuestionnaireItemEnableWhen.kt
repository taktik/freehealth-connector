//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.questionnaire

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Only allow data when
 *
 * A constraint indicating that this item should only be enabled (displayed/allow answers to be
 * captured) when the specified condition is true.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionnaireItemEnableWhen(
  /**
   * Value for question comparison based on operator
   */
  var answerBoolean: Boolean? = null,
  /**
   * Value for question comparison based on operator
   */
  var answerCoding: Coding,
  /**
   * Value for question comparison based on operator
   */
  var answerDate: String? = null,
  /**
   * Value for question comparison based on operator
   */
  var answerDateTime: String? = null,
  /**
   * Value for question comparison based on operator
   */
  var answerDecimal: Float? = null,
  /**
   * Value for question comparison based on operator
   */
  var answerInteger: Int? = null,
  /**
   * Value for question comparison based on operator
   */
  var answerQuantity: Quantity,
  /**
   * Value for question comparison based on operator
   */
  var answerReference: Reference,
  /**
   * Value for question comparison based on operator
   */
  var answerString: String? = null,
  /**
   * Value for question comparison based on operator
   */
  var answerTime: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * exists | = | != | > | < | >= | <=
   */
  var operator: String? = null,
  /**
   * Question that determines whether item is enabled
   */
  var question: String? = null
) : BackboneElement
