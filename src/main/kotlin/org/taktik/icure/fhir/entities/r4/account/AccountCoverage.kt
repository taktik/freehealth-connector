//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.account

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The party(s) that are responsible for covering the payment of this account, and what order should
 * they be applied to the account
 *
 * The party(s) that are responsible for covering the payment of this account, and what order should
 * they be applied to the account.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class AccountCoverage(
  /**
   * The party(s), such as insurances, that may contribute to the payment of this account
   */
  var coverage: Reference,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The priority of the coverage in the context of this account
   */
  var priority: Int? = null
) : BackboneElement
