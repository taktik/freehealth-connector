//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.biologicallyderivedproduct

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Any processing of the product during collection
 *
 * Any processing of the product during collection that does not change the fundamental nature of
 * the product. For example adding anti-coagulants during the collection of Peripheral Blood Stem
 * Cells.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class BiologicallyDerivedProductProcessing(
  /**
   * Substance added during processing
   */
  var additive: Reference? = null,
  /**
   * Description of of processing
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Procesing code
   */
  var procedure: CodeableConcept? = null,
  /**
   * Time of processing
   */
  var timeDateTime: String? = null,
  /**
   * Time of processing
   */
  var timePeriod: Period? = null
) : BackboneElement
