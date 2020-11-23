//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverageeligibilityresponse

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
 * CoverageEligibilityResponse resource
 *
 * This resource provides eligibility and plan details from the processing of an
 * CoverageEligibilityRequest resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class CoverageEligibilityResponse(
  override var contained: List<Resource> = listOf(),
  /**
   * Response creation date
   */
  var created: String? = null,
  /**
   * Disposition Message
   */
  var disposition: String? = null,
  var error: List<CoverageEligibilityResponseError> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Printed form identifier
   */
  var form: CodeableConcept? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var insurance: List<CoverageEligibilityResponseInsurance> = listOf(),
  /**
   * Coverage issuer
   */
  var insurer: Reference,
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
   * Intended recipient of products and services
   */
  var patient: Reference,
  /**
   * Preauthorization reference
   */
  var preAuthRef: String? = null,
  var purpose: List<String> = listOf(),
  /**
   * Eligibility request reference
   */
  var request: Reference,
  /**
   * Party responsible for the request
   */
  var requestor: Reference? = null,
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
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
