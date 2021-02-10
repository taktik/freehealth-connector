//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverageeligibilityresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.money.Money

/**
 * Benefit Summary
 *
 * Benefits used to date.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CoverageEligibilityResponseInsuranceItemBenefit(
  /**
   * Benefits allowed
   */
  var allowedMoney: Money? = null,
  /**
   * Benefits allowed
   */
  var allowedString: String? = null,
  /**
   * Benefits allowed
   */
  var allowedUnsignedInt: Int? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Benefit classification
   */
  var type: CodeableConcept,
  /**
   * Benefits used
   */
  var usedMoney: Money? = null,
  /**
   * Benefits used
   */
  var usedString: String? = null,
  /**
   * Benefits used
   */
  var usedUnsignedInt: Int? = null
) : BackboneElement
