//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claim

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Patient insurance information
 *
 * Financial instruments for reimbursement for the health care products and services specified on
 * the claim.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimInsurance(
  /**
   * Additional provider contract number
   */
  var businessArrangement: String? = null,
  /**
   * Adjudication results
   */
  var claimResponse: Reference? = null,
  /**
   * Insurance information
   */
  var coverage: Reference,
  override var extension: List<Extension> = listOf(),
  /**
   * Coverage to be used for adjudication
   */
  var focal: Boolean? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Pre-assigned Claim number
   */
  var identifier: Identifier? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var preAuthRef: List<String> = listOf(),
  /**
   * Insurance instance identifier
   */
  var sequence: Int? = null
) : BackboneElement
