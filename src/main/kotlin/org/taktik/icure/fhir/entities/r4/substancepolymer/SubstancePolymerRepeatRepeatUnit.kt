//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancepolymer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.substanceamount.SubstanceAmount

/**
 * Todo
 *
 * Todo.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstancePolymerRepeatRepeatUnit(
  /**
   * Todo
   */
  var amount: SubstanceAmount? = null,
  var degreeOfPolymerisation: List<SubstancePolymerRepeatRepeatUnitDegreeOfPolymerisation> =
      listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Todo
   */
  var orientationOfPolymerisation: CodeableConcept? = null,
  /**
   * Todo
   */
  var repeatUnit: String? = null,
  var structuralRepresentation: List<SubstancePolymerRepeatRepeatUnitStructuralRepresentation> =
      listOf()
) : BackboneElement
