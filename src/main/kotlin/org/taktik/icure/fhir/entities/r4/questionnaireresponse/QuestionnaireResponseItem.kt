//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.questionnaireresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Groups and questions
 *
 * A group or question item from the original questionnaire for which answers are provided.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionnaireResponseItem(
  var answer: List<QuestionnaireResponseItemAnswer> = listOf(),
  /**
   * ElementDefinition - details for the item
   */
  var definition: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var item: List<QuestionnaireResponseItem> = listOf(),
  /**
   * Pointer to specific item from Questionnaire
   */
  var linkId: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for group or question text
   */
  var text: String? = null
) : BackboneElement
