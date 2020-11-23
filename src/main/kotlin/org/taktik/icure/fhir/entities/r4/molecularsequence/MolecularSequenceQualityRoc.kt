//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.molecularsequence

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Receiver Operator Characteristic (ROC) Curve
 *
 * Receiver Operator Characteristic (ROC) Curve  to give sensitivity/specificity tradeoff.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MolecularSequenceQualityRoc(
  override var extension: List<Extension> = listOf(),
  var fMeasure: List<Float> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var numFN: List<Int> = listOf(),
  var numFP: List<Int> = listOf(),
  var numTP: List<Int> = listOf(),
  var precision: List<Float> = listOf(),
  var score: List<Int> = listOf(),
  var sensitivity: List<Float> = listOf()
) : BackboneElement
