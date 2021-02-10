//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contract

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Protection for the Term
 *
 * Security labels that protect the handling of information about the term and its elements, which
 * may be specifically identified..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractTermSecurityLabel(
  var category: List<Coding> = listOf(),
  /**
   * Confidentiality Protection
   */
  var classification: Coding,
  var control: List<Coding> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var number: List<Int> = listOf()
) : BackboneElement
