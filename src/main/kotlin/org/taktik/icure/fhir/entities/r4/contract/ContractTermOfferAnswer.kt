//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contract

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
 * Response to offer text
 *
 * Response to offer text.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractTermOfferAnswer(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The actual answer response
   */
  var valueAttachment: Attachment,
  /**
   * The actual answer response
   */
  var valueBoolean: Boolean? = null,
  /**
   * The actual answer response
   */
  var valueCoding: Coding,
  /**
   * The actual answer response
   */
  var valueDate: String? = null,
  /**
   * The actual answer response
   */
  var valueDateTime: String? = null,
  /**
   * The actual answer response
   */
  var valueDecimal: Float? = null,
  /**
   * The actual answer response
   */
  var valueInteger: Int? = null,
  /**
   * The actual answer response
   */
  var valueQuantity: Quantity,
  /**
   * The actual answer response
   */
  var valueReference: Reference,
  /**
   * The actual answer response
   */
  var valueString: String? = null,
  /**
   * The actual answer response
   */
  var valueTime: String? = null,
  /**
   * The actual answer response
   */
  var valueUri: String? = null
) : BackboneElement
