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
 * Stratum results, one for each unique value, or set of values, in the stratifier, or stratifier
 * components
 *
 * This element contains the results for a single stratum within the stratifier. For example, when
 * stratifying on administrative gender, there will be four strata, one for each possible gender value.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MeasureReportGroupStratifierStratum(
  var component: List<MeasureReportGroupStratifierStratumComponent> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * What score this stratum achieved
   */
  var measureScore: Quantity? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var population: List<MeasureReportGroupStratifierStratumPopulation> = listOf(),
  /**
   * The stratum value, e.g. male
   */
  var value: CodeableConcept? = null
) : BackboneElement
