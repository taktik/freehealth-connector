//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claimresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.address.Address
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Insurer added line items
 *
 * The first-tier service adjudications for payor added product or service lines.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimResponseAddItem(
  var adjudication: List<ClaimResponseItemAdjudication> = listOf(),
  /**
   * Anatomical location
   */
  var bodySite: CodeableConcept? = null,
  var detail: List<ClaimResponseAddItemDetail> = listOf(),
  var detailSequence: List<Int> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Price scaling factor
   */
  var factor: Float? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var itemSequence: List<Int> = listOf(),
  /**
   * Place of service or where product was supplied
   */
  var locationAddress: Address? = null,
  /**
   * Place of service or where product was supplied
   */
  var locationCodeableConcept: CodeableConcept? = null,
  /**
   * Place of service or where product was supplied
   */
  var locationReference: Reference? = null,
  var modifier: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Total item cost
   */
  var net: Money? = null,
  var noteNumber: List<Int> = listOf(),
  /**
   * Billing, service, product, or drug code
   */
  var productOrService: CodeableConcept,
  var programCode: List<CodeableConcept> = listOf(),
  var provider: List<Reference> = listOf(),
  /**
   * Count of products or services
   */
  var quantity: Quantity? = null,
  /**
   * Date or dates of service or product delivery
   */
  var servicedDate: String? = null,
  /**
   * Date or dates of service or product delivery
   */
  var servicedPeriod: Period? = null,
  var subSite: List<CodeableConcept> = listOf(),
  var subdetailSequence: List<Int> = listOf(),
  /**
   * Fee, charge or cost per item
   */
  var unitPrice: Money? = null
) : BackboneElement
