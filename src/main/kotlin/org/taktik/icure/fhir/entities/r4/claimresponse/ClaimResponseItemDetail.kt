//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claimresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Adjudication for claim details
 *
 * A claim detail. Either a simple (a product or service) or a 'group' of sub-details which are
 * simple items.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimResponseItemDetail(
  var adjudication: List<ClaimResponseItemAdjudication> = listOf(),
  /**
   * Claim detail instance identifier
   */
  var detailSequence: Int? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var noteNumber: List<Int> = listOf(),
  var subDetail: List<ClaimResponseItemDetailSubDetail> = listOf()
) : BackboneElement
