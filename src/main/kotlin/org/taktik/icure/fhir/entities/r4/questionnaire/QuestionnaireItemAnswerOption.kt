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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Permitted answer
 *
 * One of the permitted answers for a "choice" or "open-choice" question.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionnaireItemAnswerOption(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Whether option is selected by default
   */
  var initialSelected: Boolean? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Answer value
   */
  var valueCoding: Coding,
  /**
   * Answer value
   */
  var valueDate: String? = null,
  /**
   * Answer value
   */
  var valueInteger: Int? = null,
  /**
   * Answer value
   */
  var valueReference: Reference,
  /**
   * Answer value
   */
  var valueString: String? = null,
  /**
   * Answer value
   */
  var valueTime: String? = null
) : BackboneElement
