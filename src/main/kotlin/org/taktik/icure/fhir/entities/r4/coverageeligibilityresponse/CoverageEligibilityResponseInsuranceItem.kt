//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverageeligibilityresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Benefits and authorization details
 *
 * Benefits and optionally current balances, and authorization details by category or service.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CoverageEligibilityResponseInsuranceItem(
  /**
   * Authorization required flag
   */
  var authorizationRequired: Boolean? = null,
  var authorizationSupporting: List<CodeableConcept> = listOf(),
  /**
   * Preauthorization requirements endpoint
   */
  var authorizationUrl: String? = null,
  var benefit: List<CoverageEligibilityResponseInsuranceItemBenefit> = listOf(),
  /**
   * Benefit classification
   */
  var category: CodeableConcept? = null,
  /**
   * Description of the benefit or services covered
   */
  var description: String? = null,
  /**
   * Excluded from the plan
   */
  var excluded: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var modifier: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Short name for the benefit
   */
  var name: String? = null,
  /**
   * In or out of network
   */
  var network: CodeableConcept? = null,
  /**
   * Billing, service, product, or drug code
   */
  var productOrService: CodeableConcept? = null,
  /**
   * Performing practitioner
   */
  var provider: Reference? = null,
  /**
   * Annual or lifetime
   */
  var term: CodeableConcept? = null,
  /**
   * Individual or family
   */
  var unit: CodeableConcept? = null
) : BackboneElement
