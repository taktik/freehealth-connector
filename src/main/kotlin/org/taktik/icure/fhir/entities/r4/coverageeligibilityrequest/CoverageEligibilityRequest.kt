//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverageeligibilityrequest

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
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * CoverageEligibilityRequest resource
 *
 * The CoverageEligibilityRequest provides patient and insurance coverage information to an insurer
 * for them to respond, in the form of an CoverageEligibilityResponse, with information regarding
 * whether the stated coverage is valid and in-force and optionally to provide the insurance details of
 * the policy.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class CoverageEligibilityRequest(
  override var contained: List<Resource> = listOf(),
  /**
   * Creation date
   */
  var created: String? = null,
  /**
   * Author
   */
  var enterer: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Servicing facility
   */
  var facility: Reference? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var insurance: List<CoverageEligibilityRequestInsurance> = listOf(),
  /**
   * Coverage issuer
   */
  var insurer: Reference,
  var item: List<CoverageEligibilityRequestItem> = listOf(),
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
   * Intended recipient of products and services
   */
  var patient: Reference,
  /**
   * Desired processing priority
   */
  var priority: CodeableConcept? = null,
  /**
   * Party responsible for the request
   */
  var provider: Reference? = null,
  var purpose: List<String> = listOf(),
  /**
   * Estimated date or dates of service
   */
  var servicedDate: String? = null,
  /**
   * Estimated date or dates of service
   */
  var servicedPeriod: Period? = null,
  /**
   * active | cancelled | draft | entered-in-error
   */
  var status: String? = null,
  var supportingInfo: List<CoverageEligibilityRequestSupportingInfo> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
