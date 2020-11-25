//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverageeligibilityrequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Item to be evaluated for eligibiity
 *
 * Service categories or billable services for which benefit details and/or an authorization prior
 * to service delivery may be required by the payor.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CoverageEligibilityRequestItem(
  /**
   * Benefit classification
   */
  var category: CodeableConcept? = null,
  var detail: List<Reference> = listOf(),
  var diagnosis: List<CoverageEligibilityRequestItemDiagnosis> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Servicing facility
   */
  var facility: Reference? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var modifier: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Billing, service, product, or drug code
   */
  var productOrService: CodeableConcept? = null,
  /**
   * Perfoming practitioner
   */
  var provider: Reference? = null,
  /**
   * Count of products or services
   */
  var quantity: Quantity? = null,
  var supportingInfoSequence: List<Int> = listOf(),
  /**
   * Fee, charge or cost per item
   */
  var unitPrice: Money? = null
) : BackboneElement
