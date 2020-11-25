//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.paymentreconciliation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Settlement particulars
 *
 * Distribution of the payment amount for a previously acknowledged payable.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PaymentReconciliationDetail(
  /**
   * Amount allocated to this payable
   */
  var amount: Money? = null,
  /**
   * Date of commitment to pay
   */
  var date: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Business identifier of the payment detail
   */
  var identifier: Identifier? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Recipient of the payment
   */
  var payee: Reference? = null,
  /**
   * Business identifier of the prior payment detail
   */
  var predecessor: Identifier? = null,
  /**
   * Request giving rise to the payment
   */
  var request: Reference? = null,
  /**
   * Response committing to a payment
   */
  var response: Reference? = null,
  /**
   * Contact for the response
   */
  var responsible: Reference? = null,
  /**
   * Submitter of the request
   */
  var submitter: Reference? = null,
  /**
   * Category of payment
   */
  var type: CodeableConcept
) : BackboneElement
