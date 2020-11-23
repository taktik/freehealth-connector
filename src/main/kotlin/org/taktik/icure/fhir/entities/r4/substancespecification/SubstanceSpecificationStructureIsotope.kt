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
 * Applicable for single substances that contain a radionuclide or a non-natural isotopic ratio
 *
 * Applicable for single substances that contain a radionuclide or a non-natural isotopic ratio.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationStructureIsotope(
  override var extension: List<Extension> = listOf(),
  /**
   * Half life - for a non-natural nuclide
   */
  var halfLife: Quantity? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Substance identifier for each non-natural or radioisotope
   */
  var identifier: Identifier? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The molecular weight or weight range (for proteins, polymers or nucleic acids)
   */
  var molecularWeight: SubstanceSpecificationStructureIsotopeMolecularWeight? = null,
  /**
   * Substance name for each non-natural or radioisotope
   */
  var name: CodeableConcept? = null,
  /**
   * The type of isotopic substitution present in a single substance
   */
  var substitution: CodeableConcept? = null
) : BackboneElement
