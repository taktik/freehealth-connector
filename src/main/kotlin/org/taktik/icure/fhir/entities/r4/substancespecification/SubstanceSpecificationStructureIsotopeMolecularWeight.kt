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

/**
 * The molecular weight or weight range (for proteins, polymers or nucleic acids)
 *
 * The molecular weight or weight range (for proteins, polymers or nucleic acids).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationStructureIsotopeMolecularWeight(
  /**
   * Used to capture quantitative values for a variety of elements. If only limits are given, the
   * arithmetic mean would be the average. If only a single definite value for a given element is
   * given, it would be captured in this field
   */
  var amount: Quantity? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * The method by which the molecular weight was determined
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Type of molecular weight such as exact, average (also known as. number average), weight average
   */
  var type: CodeableConcept? = null
) : BackboneElement
