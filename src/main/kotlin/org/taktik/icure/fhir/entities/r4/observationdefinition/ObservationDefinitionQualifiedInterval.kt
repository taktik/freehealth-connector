//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.observationdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * Qualified range for continuous and ordinal observation results
 *
 * Multiple  ranges of results qualified by different contexts for ordinal or continuous
 * observations conforming to this ObservationDefinition.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ObservationDefinitionQualifiedInterval(
  /**
   * Applicable age range, if relevant
   */
  var age: Range? = null,
  var appliesTo: List<CodeableConcept> = listOf(),
  /**
   * reference | critical | absolute
   */
  var category: String? = null,
  /**
   * Condition associated with the reference range
   */
  var condition: String? = null,
  /**
   * Range context qualifier
   */
  var context: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * male | female | other | unknown
   */
  var gender: String? = null,
  /**
   * Applicable gestational age range, if relevant
   */
  var gestationalAge: Range? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The intervar itself, for continuous or ordinal observations
   */
  var range: Range? = null
) : BackboneElement
