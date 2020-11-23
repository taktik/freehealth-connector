//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.population

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * A definition of a set of people that apply to some clinically related context, for example people
 * contraindicated for a certain medication
 *
 * A populatioof people with some set of grouping criteria.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Population(
  /**
   * The age of the specific population
   */
  var ageCodeableConcept: CodeableConcept? = null,
  /**
   * The age of the specific population
   */
  var ageRange: Range? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * The gender of the specific population
   */
  var gender: CodeableConcept? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The existing physiological conditions of the specific population to which this applies
   */
  var physiologicalCondition: CodeableConcept? = null,
  /**
   * Race of the specific population
   */
  var race: CodeableConcept? = null
) : BackboneElement
