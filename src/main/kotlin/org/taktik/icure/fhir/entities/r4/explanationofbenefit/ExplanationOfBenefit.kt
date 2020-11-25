//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.explanationofbenefit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Int
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
 * Explanation of Benefit resource
 *
 * This resource provides: the claim details; adjudication details from the processing of a Claim;
 * and optionally account balance information, for informing the subscriber of the benefits provided.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ExplanationOfBenefit(
  /**
   * Details of the event
   */
  var accident: ExplanationOfBenefitAccident? = null,
  var addItem: List<ExplanationOfBenefitAddItem> = listOf(),
  var adjudication: List<ExplanationOfBenefitItemAdjudication> = listOf(),
  var benefitBalance: List<ExplanationOfBenefitBenefitBalance> = listOf(),
  /**
   * When the benefits are applicable
   */
  var benefitPeriod: Period? = null,
  /**
   * Relevant time frame for the claim
   */
  var billablePeriod: Period? = null,
  var careTeam: List<ExplanationOfBenefitCareTeam> = listOf(),
  /**
   * Claim reference
   */
  var claim: Reference? = null,
  /**
   * Claim response reference
   */
  var claimResponse: Reference? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Response creation date
   */
  var created: String? = null,
  var diagnosis: List<ExplanationOfBenefitDiagnosis> = listOf(),
  /**
   * Disposition Message
   */
  var disposition: String? = null,
  /**
   * Author of the claim
   */
  var enterer: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Servicing Facility
   */
  var facility: Reference? = null,
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
   * For whom to reserve funds
   */
  var fundsReserveRequested: CodeableConcept? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var insurance: List<ExplanationOfBenefitInsurance> = listOf(),
  /**
   * Party responsible for reimbursement
   */
  var insurer: Reference,
  var item: List<ExplanationOfBenefitItem> = listOf(),
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
   * Original prescription if superceded by fulfiller
   */
  var originalPrescription: Reference? = null,
  /**
   * queued | complete | error | partial
   */
  var outcome: String? = null,
  /**
   * The recipient of the products and services
   */
  var patient: Reference,
  /**
   * Recipient of benefits payable
   */
  var payee: ExplanationOfBenefitPayee? = null,
  /**
   * Payment Details
   */
  var payment: ExplanationOfBenefitPayment? = null,
  var preAuthRef: List<String> = listOf(),
  var preAuthRefPeriod: List<Period> = listOf(),
  /**
   * Precedence (primary, secondary, etc.)
   */
  var precedence: Int? = null,
  /**
   * Prescription authorizing services or products
   */
  var prescription: Reference? = null,
  /**
   * Desired processing urgency
   */
  var priority: CodeableConcept? = null,
  var procedure: List<ExplanationOfBenefitProcedure> = listOf(),
  var processNote: List<ExplanationOfBenefitProcessNote> = listOf(),
  /**
   * Party responsible for the claim
   */
  var provider: Reference,
  /**
   * Treatment Referral
   */
  var referral: Reference? = null,
  var related: List<ExplanationOfBenefitRelated> = listOf(),
  /**
   * active | cancelled | draft | entered-in-error
   */
  var status: String? = null,
  /**
   * More granular claim type
   */
  var subType: CodeableConcept? = null,
  var supportingInfo: List<ExplanationOfBenefitSupportingInfo> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var total: List<ExplanationOfBenefitTotal> = listOf(),
  /**
   * Category or discipline
   */
  var type: CodeableConcept,
  /**
   * claim | preauthorization | predetermination
   */
  var use: String? = null
) : DomainResource
