//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.invoice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Invoice containing ChargeItems from an Account
 *
 * Invoice containing collected ChargeItems from an Account with calculated individual and total
 * price for Billing purpose.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Invoice(
  /**
   * Account that is being balanced
   */
  var account: Reference? = null,
  /**
   * Reason for cancellation of this Invoice
   */
  var cancelledReason: String? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Invoice date / posting date
   */
  var date: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Issuing Organization of Invoice
   */
  var issuer: Reference? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var lineItem: List<InvoiceLineItem> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var participant: List<InvoiceParticipant> = listOf(),
  /**
   * Payment details
   */
  var paymentTerms: String? = null,
  /**
   * Recipient of this invoice
   */
  var recipient: Reference? = null,
  /**
   * draft | issued | balanced | cancelled | entered-in-error
   */
  var status: String? = null,
  /**
   * Recipient(s) of goods and services
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Gross total of this Invoice
   */
  var totalGross: Money? = null,
  /**
   * Net total of this Invoice
   */
  var totalNet: Money? = null,
  var totalPriceComponent: List<InvoiceLineItemPriceComponent> = listOf(),
  /**
   * Type of Invoice
   */
  var type: CodeableConcept? = null
) : DomainResource
