//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.invoice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Line items of this Invoice
 *
 * Each line item represents one charge for goods and services rendered. Details such as date, code
 * and amount are found in the referenced ChargeItem resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class InvoiceLineItem(
  /**
   * Reference to ChargeItem containing details of this line item or an inline billing code
   */
  var chargeItemCodeableConcept: CodeableConcept,
  /**
   * Reference to ChargeItem containing details of this line item or an inline billing code
   */
  var chargeItemReference: Reference,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var priceComponent: List<InvoiceLineItemPriceComponent> = listOf(),
  /**
   * Sequence number of line item
   */
  var sequence: Int? = null
) : BackboneElement
