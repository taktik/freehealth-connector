//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contract

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Contract Valued Item List
 *
 * Contract Valued Item List.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractTermAssetValuedItem(
  /**
   * Contract Valued Item Effective Tiem
   */
  var effectiveTime: String? = null,
  /**
   * Contract Valued Item Type
   */
  var entityCodeableConcept: CodeableConcept? = null,
  /**
   * Contract Valued Item Type
   */
  var entityReference: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Contract Valued Item Price Scaling Factor
   */
  var factor: Float? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Contract Valued Item Number
   */
  var identifier: Identifier? = null,
  var linkId: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Total Contract Valued Item Value
   */
  var net: Money? = null,
  /**
   * Terms of valuation
   */
  var payment: String? = null,
  /**
   * When payment is due
   */
  var paymentDate: String? = null,
  /**
   * Contract Valued Item Difficulty Scaling Factor
   */
  var points: Float? = null,
  /**
   * Count of Contract Valued Items
   */
  var quantity: Quantity? = null,
  /**
   * Who will receive payment
   */
  var recipient: Reference? = null,
  /**
   * Who will make payment
   */
  var responsible: Reference? = null,
  var securityLabelNumber: List<Int> = listOf(),
  /**
   * Contract Valued Item fee, charge, or cost
   */
  var unitPrice: Money? = null
) : BackboneElement
