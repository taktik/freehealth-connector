//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.questionnaire

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Questions and sections within the Questionnaire
 *
 * A particular question, question grouping or display text that is part of the questionnaire.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionnaireItem(
  var answerOption: List<QuestionnaireItemAnswerOption> = listOf(),
  /**
   * Valueset containing permitted answers
   */
  var answerValueSet: String? = null,
  var code: List<Coding> = listOf(),
  /**
   * ElementDefinition - details for the item
   */
  var definition: String? = null,
  /**
   * all | any
   */
  var enableBehavior: String? = null,
  var enableWhen: List<QuestionnaireItemEnableWhen> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var initial: List<QuestionnaireItemInitial> = listOf(),
  var item: List<QuestionnaireItem> = listOf(),
  /**
   * Unique id for item in questionnaire
   */
  var linkId: String? = null,
  /**
   * No more than this many characters
   */
  var maxLength: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * E.g. "1(a)", "2.5.3"
   */
  var prefix: String? = null,
  /**
   * Don't allow human editing
   */
  var readOnly: Boolean? = null,
  /**
   * Whether the item may repeat
   */
  var repeats: Boolean? = null,
  /**
   * Whether the item must be included in data results
   */
  var required: Boolean? = null,
  /**
   * Primary text for the item
   */
  var text: String? = null,
  /**
   * group | display | boolean | decimal | integer | date | dateTime +
   */
  var type: String? = null
) : BackboneElement
