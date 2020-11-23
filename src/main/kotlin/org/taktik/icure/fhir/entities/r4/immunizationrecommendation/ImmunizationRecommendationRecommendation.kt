//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.immunizationrecommendation

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
 * Vaccine administration recommendations
 *
 * Vaccine administration recommendations.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImmunizationRecommendationRecommendation(
  var contraindicatedVaccineCode: List<CodeableConcept> = listOf(),
  var dateCriterion: List<ImmunizationRecommendationRecommendationDateCriterion> = listOf(),
  /**
   * Protocol details
   */
  var description: String? = null,
  /**
   * Recommended dose number within series
   */
  var doseNumberPositiveInt: Int? = null,
  /**
   * Recommended dose number within series
   */
  var doseNumberString: String? = null,
  override var extension: List<Extension> = listOf(),
  var forecastReason: List<CodeableConcept> = listOf(),
  /**
   * Vaccine recommendation status
   */
  var forecastStatus: CodeableConcept,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name of vaccination series
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
  var supportingImmunization: List<Reference> = listOf(),
  var supportingPatientInformation: List<Reference> = listOf(),
  /**
   * Disease to be immunized against
   */
  var targetDisease: CodeableConcept? = null,
  var vaccineCode: List<CodeableConcept> = listOf()
) : BackboneElement
