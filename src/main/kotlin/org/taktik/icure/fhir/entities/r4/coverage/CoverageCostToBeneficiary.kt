//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.money.Money

/**
 * Patient payments for services/products
 *
 * A suite of codes indicating the cost category and associated amount which have been detailed in
 * the policy and may have been  included on the health card.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CoverageCostToBeneficiary(
  var exception: List<CoverageCostToBeneficiaryException> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Cost category
   */
  var type: CodeableConcept? = null,
  /**
   * The amount or percentage due from the beneficiary
   */
  var valueMoney: Money,
  /**
   * The amount or percentage due from the beneficiary
   */
  var valueQuantity: Quantity
) : BackboneElement
