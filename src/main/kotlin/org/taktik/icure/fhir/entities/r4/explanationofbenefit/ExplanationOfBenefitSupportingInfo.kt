//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.explanationofbenefit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Supporting information
 *
 * Additional information codes regarding exceptions, special considerations, the condition,
 * situation, prior or concurrent issues.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ExplanationOfBenefitSupportingInfo(
  /**
   * Classification of the supplied information
   */
  var category: CodeableConcept,
  /**
   * Type of information
   */
  var code: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Explanation for the information
   */
  var reason: Coding? = null,
  /**
   * Information instance identifier
   */
  var sequence: Int? = null,
  /**
   * When it occurred
   */
  var timingDate: String? = null,
  /**
   * When it occurred
   */
  var timingPeriod: Period? = null,
  /**
   * Data to be provided
   */
  var valueAttachment: Attachment? = null,
  /**
   * Data to be provided
   */
  var valueBoolean: Boolean? = null,
  /**
   * Data to be provided
   */
  var valueQuantity: Quantity? = null,
  /**
   * Data to be provided
   */
  var valueReference: Reference? = null,
  /**
   * Data to be provided
   */
  var valueString: String? = null
) : BackboneElement
