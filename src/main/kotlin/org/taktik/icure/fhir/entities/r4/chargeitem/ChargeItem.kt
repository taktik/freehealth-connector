//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.chargeitem

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Item containing charge code(s) associated with the provision of healthcare provider products
 *
 * The resource ChargeItem describes the provision of healthcare provider products for a certain
 * patient, therefore referring not only to the product, but containing in addition details of the
 * provision, like date, time, amounts and participating organizations and persons. Main Usage of the
 * ChargeItem is to enable the billing process and internal cost allocation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ChargeItem(
  var account: List<Reference> = listOf(),
  var bodysite: List<CodeableConcept> = listOf(),
  /**
   * A code that identifies the charge, like a billing code
   */
  var code: CodeableConcept,
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter / Episode associated with event
   */
  var context: Reference? = null,
  /**
   * Organization that has ownership of the (potential, future) revenue
   */
  var costCenter: Reference? = null,
  var definitionCanonical: List<String> = listOf(),
  var definitionUri: List<String> = listOf(),
  /**
   * Date the charge item was entered
   */
  var enteredDate: String? = null,
  /**
   * Individual who was entering
   */
  var enterer: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Factor overriding the associated rules
   */
  var factorOverride: Float? = null,
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
  var note: List<Annotation> = listOf(),
  /**
   * When the charged service was applied
   */
  var occurrenceDateTime: String? = null,
  /**
   * When the charged service was applied
   */
  var occurrencePeriod: Period? = null,
  /**
   * When the charged service was applied
   */
  var occurrenceTiming: Timing? = null,
  /**
   * Reason for overriding the list price/factor
   */
  var overrideReason: String? = null,
  var partOf: List<Reference> = listOf(),
  var performer: List<ChargeItemPerformer> = listOf(),
  /**
   * Organization providing the charged service
   */
  var performingOrganization: Reference? = null,
  /**
   * Price overriding the associated rules
   */
  var priceOverride: Money? = null,
  /**
   * Product charged
   */
  var productCodeableConcept: CodeableConcept? = null,
  /**
   * Product charged
   */
  var productReference: Reference? = null,
  /**
   * Quantity of which the charge item has been serviced
   */
  var quantity: Quantity? = null,
  var reason: List<CodeableConcept> = listOf(),
  /**
   * Organization requesting the charged service
   */
  var requestingOrganization: Reference? = null,
  var service: List<Reference> = listOf(),
  /**
   * planned | billable | not-billable | aborted | billed | entered-in-error | unknown
   */
  var status: String? = null,
  /**
   * Individual service was done for/to
   */
  var subject: Reference,
  var supportingInformation: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
