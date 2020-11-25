//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.observationdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Characteristics of quantitative results
 *
 * Characteristics for quantitative results of this observation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ObservationDefinitionQuantitativeDetails(
  /**
   * SI to Customary unit conversion factor
   */
  var conversionFactor: Float? = null,
  /**
   * Customary unit for quantitative results
   */
  var customaryUnit: CodeableConcept? = null,
  /**
   * Decimal precision of observation quantitative results
   */
  var decimalPrecision: Int? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * SI unit for quantitative results
   */
  var unit: CodeableConcept? = null
) : BackboneElement
