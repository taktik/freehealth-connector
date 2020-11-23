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
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Contract Term Asset List
 *
 * Contract Term Asset List.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractTermAsset(
  var answer: List<ContractTermOfferAnswer> = listOf(),
  /**
   * Quality desctiption of asset
   */
  var condition: String? = null,
  var context: List<ContractTermAssetContext> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var linkId: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var period: List<Period> = listOf(),
  var periodType: List<CodeableConcept> = listOf(),
  /**
   * Kinship of the asset
   */
  var relationship: Coding? = null,
  /**
   * Range of asset
   */
  var scope: CodeableConcept? = null,
  var securityLabelNumber: List<Int> = listOf(),
  var subtype: List<CodeableConcept> = listOf(),
  /**
   * Asset clause or question text
   */
  var text: String? = null,
  var type: List<CodeableConcept> = listOf(),
  var typeReference: List<Reference> = listOf(),
  var usePeriod: List<Period> = listOf(),
  var valuedItem: List<ContractTermAssetValuedItem> = listOf()
) : BackboneElement
