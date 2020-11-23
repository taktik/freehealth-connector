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
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Context of the Contract term
 *
 * The matter of concern in the context of this provision of the agrement.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractTermOffer(
  var answer: List<ContractTermOfferAnswer> = listOf(),
  /**
   * Accepting party choice
   */
  var decision: CodeableConcept? = null,
  var decisionMode: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  var linkId: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var party: List<ContractTermOfferParty> = listOf(),
  var securityLabelNumber: List<Int> = listOf(),
  /**
   * Human readable offer text
   */
  var text: String? = null,
  /**
   * Negotiable offer asset
   */
  var topic: Reference? = null,
  /**
   * Contract Offer Type or Form
   */
  var type: CodeableConcept? = null
) : BackboneElement
