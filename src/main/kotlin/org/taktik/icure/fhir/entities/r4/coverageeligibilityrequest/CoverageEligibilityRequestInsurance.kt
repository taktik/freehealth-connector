//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.coverageeligibilityrequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Patient insurance information
 *
 * Financial instruments for reimbursement for the health care products and services.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CoverageEligibilityRequestInsurance(
  /**
   * Additional provider contract number
   */
  var businessArrangement: String? = null,
  /**
   * Insurance information
   */
  var coverage: Reference,
  override var extension: List<Extension> = listOf(),
  /**
   * Applicable coverage
   */
  var focal: Boolean? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
