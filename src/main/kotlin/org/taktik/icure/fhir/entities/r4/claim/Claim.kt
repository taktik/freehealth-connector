//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claim

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
 * Claim, Pre-determination or Pre-authorization
 *
 * A provider issued list of professional services and products which have been provided, or are to
 * be provided, to a patient which is sent to an insurer for reimbursement.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Claim(
  /**
   * Details of the event
   */
  var accident: ClaimAccident? = null,
  /**
   * Relevant time frame for the claim
   */
  var billablePeriod: Period? = null,
  var careTeam: List<ClaimCareTeam> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Resource creation date
   */
  var created: String? = null,
  var diagnosis: List<ClaimDiagnosis> = listOf(),
  /**
   * Author of the claim
   */
  var enterer: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Servicing facility
   */
  var facility: Reference? = null,
  /**
   * For whom to reserve funds
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
  var insurance: List<ClaimInsurance> = listOf(),
  /**
   * Target
   */
  var insurer: Reference? = null,
  var item: List<ClaimItem> = listOf(),
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
   * Original prescription if superseded by fulfiller
   */
  var originalPrescription: Reference? = null,
  /**
   * The recipient of the products and services
   */
  var patient: Reference,
  /**
   * Recipient of benefits payable
   */
  var payee: ClaimPayee? = null,
  /**
   * Prescription authorizing services and products
   */
  var prescription: Reference? = null,
  /**
   * Desired processing ugency
   */
  var priority: CodeableConcept,
  var procedure: List<ClaimProcedure> = listOf(),
  /**
   * Party responsible for the claim
   */
  var provider: Reference,
  /**
   * Treatment referral
   */
  var referral: Reference? = null,
  var related: List<ClaimRelated> = listOf(),
  /**
   * active | cancelled | draft | entered-in-error
   */
  var status: String? = null,
  /**
   * More granular claim type
   */
  var subType: CodeableConcept? = null,
  var supportingInfo: List<ClaimSupportingInfo> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Total claim cost
   */
  var total: Money? = null,
  /**
   * Category or discipline
   */
  var type: CodeableConcept,
  /**
   * claim | preauthorization | predetermination
   */
  var use: String? = null
) : DomainResource
