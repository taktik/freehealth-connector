//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claim

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
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
 * Product or service provided
 *
 * A claim detail line. Either a simple (a product or service) or a 'group' of sub-details which are
 * simple items.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimItemDetail(
  /**
   * Benefit classification
   */
  var category: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Price scaling factor
   */
  var factor: Float? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var modifier: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Total item cost
   */
  var net: Money? = null,
  /**
   * Billing, service, product, or drug code
   */
  var productOrService: CodeableConcept,
  var programCode: List<CodeableConcept> = listOf(),
  /**
   * Count of products or services
   */
  var quantity: Quantity? = null,
  /**
   * Revenue or cost center code
   */
  var revenue: CodeableConcept? = null,
  /**
   * Item instance identifier
   */
  var sequence: Int? = null,
  var subDetail: List<ClaimItemDetailSubDetail> = listOf(),
  var udi: List<Reference> = listOf(),
  /**
   * Fee, charge or cost per item
   */
  var unitPrice: Money? = null
) : BackboneElement
