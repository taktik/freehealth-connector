//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claimresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Response to a claim predetermination or preauthorization
 *
 * This resource provides the adjudication details from the processing of a Claim resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ClaimResponse(
  var addItem: List<ClaimResponseAddItem> = listOf(),
  var adjudication: List<ClaimResponseItemAdjudication> = listOf(),
  var communicationRequest: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Response creation date
   */
  var created: String? = null,
  /**
   * Disposition Message
   */
  var disposition: String? = null,
  var error: List<ClaimResponseError> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Printed reference or actual form
   */
  var form: Attachment? = null,
  /**
   * Printed form identifier
   */
  var formCode: CodeableConcept? = null,
  /**
   * Funds reserved status
   */
  var fundsReserve: CodeableConcept? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var insurance: List<ClaimResponseInsurance> = listOf(),
  /**
   * Party responsible for reimbursement
   */
  var insurer: Reference,
  var item: List<ClaimResponseItem> = listOf(),
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
   * The recipient of the products and services
   */
  var patient: Reference,
  /**
   * Party to be paid any benefits payable
   */
  var payeeType: CodeableConcept? = null,
  /**
   * Payment Details
   */
  var payment: ClaimResponsePayment? = null,
  /**
   * Preauthorization reference effective period
   */
  var preAuthPeriod: Period? = null,
  /**
   * Preauthorization reference
   */
  var preAuthRef: String? = null,
  var processNote: List<ClaimResponseProcessNote> = listOf(),
  /**
   * Id of resource triggering adjudication
   */
  var request: Reference? = null,
  /**
   * Party responsible for the claim
   */
  var requestor: Reference? = null,
  /**
   * active | cancelled | draft | entered-in-error
   */
  var status: String? = null,
  /**
   * More granular claim type
   */
  var subType: CodeableConcept? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var total: List<ClaimResponseTotal> = listOf(),
  /**
   * More granular claim type
   */
  var type: CodeableConcept,
  /**
   * claim | preauthorization | predetermination
   */
  var use: String? = null
) : DomainResource
