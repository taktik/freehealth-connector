//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.measurereport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Measure results for each group
 *
 * The results of the calculation, one for each population group in the measure.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MeasureReportGroup(
  /**
   * Meaning of the group
   */
  var code: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * What score this group achieved
   */
  var measureScore: Quantity? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var population: List<MeasureReportGroupPopulation> = listOf(),
  var stratifier: List<MeasureReportGroupStratifier> = listOf()
) : BackboneElement
