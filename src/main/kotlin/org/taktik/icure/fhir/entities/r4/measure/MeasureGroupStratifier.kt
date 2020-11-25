//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.measure

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.expression.Expression
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Stratifier criteria for the measure
 *
 * The stratifier criteria for the measure report, specified as either the name of a valid CQL
 * expression defined within a referenced library or a valid FHIR Resource Path.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MeasureGroupStratifier(
  /**
   * Meaning of the stratifier
   */
  var code: CodeableConcept? = null,
  var component: List<MeasureGroupStratifierComponent> = listOf(),
  /**
   * How the measure should be stratified
   */
  var criteria: Expression? = null,
  /**
   * The human readable description of this stratifier
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
