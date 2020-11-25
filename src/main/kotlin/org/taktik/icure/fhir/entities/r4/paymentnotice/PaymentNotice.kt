//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.paymentnotice

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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * PaymentNotice request
 *
 * This resource provides the status of the payment for goods and services rendered, and the request
 * and response resource references.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class PaymentNotice(
  /**
   * Monetary amount of the payment
   */
  var amount: Money,
  override var contained: List<Resource> = listOf(),
  /**
   * Creation date
   */
  var created: String? = null,
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
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Party being paid
   */
  var payee: Reference? = null,
  /**
   * Payment reference
   */
  var payment: Reference,
  /**
   * Payment or clearing date
   */
  var paymentDate: String? = null,
  /**
   * Issued or cleared Status of the payment
   */
  var paymentStatus: CodeableConcept? = null,
  /**
   * Responsible practitioner
   */
  var provider: Reference? = null,
  /**
   * Party being notified
   */
  var recipient: Reference,
  /**
   * Request reference
   */
  var request: Reference? = null,
  /**
   * Response reference
   */
  var response: Reference? = null,
  /**
   * active | cancelled | draft | entered-in-error
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
