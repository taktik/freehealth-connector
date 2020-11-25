//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.immunization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Protocol followed by the provider
 *
 * The protocol (set of recommendations) being followed by the provider who administered the dose.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImmunizationProtocolApplied(
  /**
   * Who is responsible for publishing the recommendations
   */
  var authority: Reference? = null,
  /**
   * Dose number within series
   */
  var doseNumberPositiveInt: Int? = null,
  /**
   * Dose number within series
   */
  var doseNumberString: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name of vaccine series
   */
  var series: String? = null,
  /**
   * Recommended number of doses for immunity
   */
  var seriesDosesPositiveInt: Int? = null,
  /**
   * Recommended number of doses for immunity
   */
  var seriesDosesString: String? = null,
  var targetDisease: List<CodeableConcept> = listOf()
) : BackboneElement
