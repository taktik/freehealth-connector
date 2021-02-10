//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contract

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Contract Term List
 *
 * One or more Contract Provisions, which may be related and conveyed as a group, and may contain
 * nested groups.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractTerm(
  var action: List<ContractTermAction> = listOf(),
  /**
   * Contract Term Effective Time
   */
  var applies: Period? = null,
  var asset: List<ContractTermAsset> = listOf(),
  override var extension: List<Extension> = listOf(),
  var group: List<ContractTerm> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Contract Term Number
   */
  var identifier: Identifier? = null,
  /**
   * Contract Term Issue Date Time
   */
  var issued: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Context of the Contract term
   */
  var offer: ContractTermOffer,
  var securityLabel: List<ContractTermSecurityLabel> = listOf(),
  /**
   * Contract Term Type specific classification
   */
  var subType: CodeableConcept? = null,
  /**
   * Term Statement
   */
  var text: String? = null,
  /**
   * Term Concern
   */
  var topicCodeableConcept: CodeableConcept? = null,
  /**
   * Term Concern
   */
  var topicReference: Reference? = null,
  /**
   * Contract Term Type or Form
   */
  var type: CodeableConcept? = null
) : BackboneElement
