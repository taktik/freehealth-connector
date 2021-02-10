//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancespecification

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier

/**
 * Moiety, for structural modifications
 *
 * Moiety, for structural modifications.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationMoiety(
  /**
   * Quantitative value for this moiety
   */
  var amountQuantity: Quantity? = null,
  /**
   * Quantitative value for this moiety
   */
  var amountString: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Identifier by which this moiety substance is known
   */
  var identifier: Identifier? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Molecular formula
   */
  var molecularFormula: String? = null,
  /**
   * Textual name for this moiety substance
   */
  var name: String? = null,
  /**
   * Optical activity type
   */
  var opticalActivity: CodeableConcept? = null,
  /**
   * Role that the moiety is playing
   */
  var role: CodeableConcept? = null,
  /**
   * Stereochemistry type
   */
  var stereochemistry: CodeableConcept? = null
) : BackboneElement
