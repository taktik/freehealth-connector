//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.explanationofbenefit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Balance by Benefit Category
 *
 * Balance by Benefit Category.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ExplanationOfBenefitBenefitBalance(
  /**
   * Benefit classification
   */
  var category: CodeableConcept,
  /**
   * Description of the benefit or services covered
   */
  var description: String? = null,
  /**
   * Excluded from the plan
   */
  var excluded: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var financial: List<ExplanationOfBenefitBenefitBalanceFinancial> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
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
   * Annual or lifetime
   */
  var term: CodeableConcept? = null,
  /**
   * Individual or family
   */
  var unit: CodeableConcept? = null
) : BackboneElement
