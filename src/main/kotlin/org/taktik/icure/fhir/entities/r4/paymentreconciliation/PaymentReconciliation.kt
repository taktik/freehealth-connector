//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.paymentreconciliation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * PaymentReconciliation resource
 *
 * This resource provides the details including amount of a payment and allocates the payment items
 * being paid.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class PaymentReconciliation(
  override var contained: List<Resource> = listOf(),
  /**
   * Creation date
   */
  var created: String? = null,
  var detail: List<PaymentReconciliationDetail> = listOf(),
  /**
   * Disposition message
   */
  var disposition: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Printed form identifier
   */
  var formCode: CodeableConcept? = null,
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
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * queued | complete | error | partial
   */
  var outcome: String? = null,
  /**
   * Total amount of Payment
   */
  var paymentAmount: Money,
  /**
   * When payment issued
   */
  var paymentDate: String? = null,
  /**
   * Business identifier for the payment
   */
  var paymentIdentifier: Identifier? = null,
  /**
   * Party generating payment
   */
  var paymentIssuer: Reference? = null,
  /**
   * Period covered
   */
  var period: Period? = null,
  var processNote: List<PaymentReconciliationProcessNote> = listOf(),
  /**
   * Reference to requesting resource
   */
  var request: Reference? = null,
  /**
   * Responsible practitioner
   */
  var requestor: Reference? = null,
  /**
   * active | cancelled | draft | entered-in-error
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
