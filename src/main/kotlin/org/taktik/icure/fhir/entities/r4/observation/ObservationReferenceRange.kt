//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.observation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * Provides guide for interpretation
 *
 * Guidance on how to interpret the value by comparison to a normal or recommended range.  Multiple
 * reference ranges are interpreted as an "OR".   In other words, to represent two distinct target
 * populations, two `referenceRange` elements would be used.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ObservationReferenceRange(
  /**
   * Applicable age range, if relevant
   */
  var age: Range? = null,
  var appliesTo: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * High Range, if relevant
   */
  var high: Quantity? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Low Range, if relevant
   */
  var low: Quantity? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Text based reference range in an observation
   */
  var text: String? = null,
  /**
   * Reference range qualifier
   */
  var type: CodeableConcept? = null
) : BackboneElement
