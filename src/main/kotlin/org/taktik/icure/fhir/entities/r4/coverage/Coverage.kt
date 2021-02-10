//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Insurance or medical plan or a payment agreement
 *
 * Financial instrument which may be used to reimburse or pay for health care products and services.
 * Includes both insurance and self-payment.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Coverage(
  /**
   * Plan beneficiary
   */
  var beneficiary: Reference,
  @JsonProperty("class")
  var class_fhir: List<CoverageClass> = listOf(),
  override var contained: List<Resource> = listOf(),
  var contract: List<Reference> = listOf(),
  var costToBeneficiary: List<CoverageCostToBeneficiary> = listOf(),
  /**
   * Dependent number
   */
  var dependent: String? = null,
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
   * Insurer network
   */
  var network: String? = null,
  /**
   * Relative order of the coverage
   */
  var order: Int? = null,
  var payor: List<Reference> = listOf(),
  /**
   * Coverage start and end dates
   */
  var period: Period? = null,
  /**
   * Owner of the policy
   */
  var policyHolder: Reference? = null,
  /**
   * Beneficiary relationship to the subscriber
   */
  var relationship: CodeableConcept? = null,
  /**
   * active | cancelled | draft | entered-in-error
   */
  var status: String? = null,
  /**
   * Reimbursement to insurer
   */
  var subrogation: Boolean? = null,
  /**
   * Subscriber to the policy
   */
  var subscriber: Reference? = null,
  /**
   * ID assigned to the subscriber
   */
  var subscriberId: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Coverage category such as medical or accident
   */
  var type: CodeableConcept? = null
) : DomainResource
