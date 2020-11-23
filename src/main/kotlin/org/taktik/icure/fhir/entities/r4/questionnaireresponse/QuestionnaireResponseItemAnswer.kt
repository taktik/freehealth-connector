//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.questionnaireresponse

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
 * The response(s) to the question
 *
 * The respondent's answer(s) to the question.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionnaireResponseItemAnswer(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var item: List<QuestionnaireResponseItem> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Single-valued answer to the question
   */
  var valueAttachment: Attachment? = null,
  /**
   * Single-valued answer to the question
   */
  var valueBoolean: Boolean? = null,
  /**
   * Single-valued answer to the question
   */
  var valueCoding: Coding? = null,
  /**
   * Single-valued answer to the question
   */
  var valueDate: String? = null,
  /**
   * Single-valued answer to the question
   */
  var valueDateTime: String? = null,
  /**
   * Single-valued answer to the question
   */
  var valueDecimal: Float? = null,
  /**
   * Single-valued answer to the question
   */
  var valueInteger: Int? = null,
  /**
   * Single-valued answer to the question
   */
  var valueQuantity: Quantity? = null,
  /**
   * Single-valued answer to the question
   */
  var valueReference: Reference? = null,
  /**
   * Single-valued answer to the question
   */
  var valueString: String? = null,
  /**
   * Single-valued answer to the question
   */
  var valueTime: String? = null,
  /**
   * Single-valued answer to the question
   */
  var valueUri: String? = null
) : BackboneElement
