//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claimresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.money.Money

/**
 * Adjudication details
 *
 * If this item is a group then the values here are a summary of the adjudication of the detail
 * items. If this item is a simple product or service then this is the result of the adjudication of
 * this item.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimResponseItemAdjudication(
  /**
   * Monetary amount
   */
  var amount: Money? = null,
  /**
   * Type of adjudication information
   */
  var category: CodeableConcept,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Explanation of adjudication outcome
   */
  var reason: CodeableConcept? = null,
  /**
   * Non-monetary value
   */
  var value: Float? = null
) : BackboneElement
