//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancespecification

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Structural information
 *
 * Structural information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationStructure(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var isotope: List<SubstanceSpecificationStructureIsotope> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Molecular formula
   */
  var molecularFormula: String? = null,
  /**
   * Specified per moiety according to the Hill system, i.e. first C, then H, then alphabetical,
   * each moiety separated by a dot
   */
  var molecularFormulaByMoiety: String? = null,
  /**
   * The molecular weight or weight range (for proteins, polymers or nucleic acids)
   */
  var molecularWeight: SubstanceSpecificationStructureIsotopeMolecularWeight? = null,
  /**
   * Optical activity type
   */
  var opticalActivity: CodeableConcept? = null,
  var representation: List<SubstanceSpecificationStructureRepresentation> = listOf(),
  var source: List<Reference> = listOf(),
  /**
   * Stereochemistry type
   */
  var stereochemistry: CodeableConcept? = null
) : BackboneElement
