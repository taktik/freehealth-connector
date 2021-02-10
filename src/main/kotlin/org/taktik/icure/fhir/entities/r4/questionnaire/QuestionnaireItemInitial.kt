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
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Initial value(s) when item is first rendered
 *
 * One or more values that should be pre-populated in the answer when initially rendering the
 * questionnaire for user input.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionnaireItemInitial(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Actual value for initializing the question
   */
  var valueAttachment: Attachment,
  /**
   * Actual value for initializing the question
   */
  var valueBoolean: Boolean? = null,
  /**
   * Actual value for initializing the question
   */
  var valueCoding: Coding,
  /**
   * Actual value for initializing the question
   */
  var valueDate: String? = null,
  /**
   * Actual value for initializing the question
   */
  var valueDateTime: String? = null,
  /**
   * Actual value for initializing the question
   */
  var valueDecimal: Float? = null,
  /**
   * Actual value for initializing the question
   */
  var valueInteger: Int? = null,
  /**
   * Actual value for initializing the question
   */
  var valueQuantity: Quantity,
  /**
   * Actual value for initializing the question
   */
  var valueReference: Reference,
  /**
   * Actual value for initializing the question
   */
  var valueString: String? = null,
  /**
   * Actual value for initializing the question
   */
  var valueTime: String? = null,
  /**
   * Actual value for initializing the question
   */
  var valueUri: String? = null
) : BackboneElement
