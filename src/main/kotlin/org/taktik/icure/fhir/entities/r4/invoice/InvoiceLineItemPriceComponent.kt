//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.invoice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.money.Money

/**
 * Components of total line item price
 *
 * The price for a ChargeItem may be calculated as a base price with surcharges/deductions that
 * apply in certain conditions. A ChargeItemDefinition resource that defines the prices, factors and
 * conditions that apply to a billing code is currently under development. The priceComponent element
 * can be used to offer transparency to the recipient of the Invoice as to how the prices have been
 * calculated.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class InvoiceLineItemPriceComponent(
  /**
   * Monetary amount associated with this component
   */
  var amount: Money? = null,
  /**
   * Code identifying the specific component
   */
  var code: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Factor used for calculating this component
   */
  var factor: Float? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * base | surcharge | deduction | discount | tax | informational
   */
  var type: String? = null
) : BackboneElement
