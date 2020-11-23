//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.immunizationevaluation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Immunization evaluation information
 *
 * Describes a comparison of an immunization event against published recommendations to determine if
 * the administration is "valid" in relation to those  recommendations.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ImmunizationEvaluation(
  /**
   * Who is responsible for publishing the recommendations
   */
  var authority: Reference? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Date evaluation was performed
   */
  var date: String? = null,
  /**
   * Evaluation notes
   */
  var description: String? = null,
  /**
   * Dose number within series
   */
  var doseNumberPositiveInt: Int? = null,
  /**
   * Dose number within series
   */
  var doseNumberString: String? = null,
  /**
   * Status of the dose relative to published recommendations
   */
  var doseStatus: CodeableConcept,
  var doseStatusReason: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * Immunization being evaluated
   */
  var immunizationEvent: Reference,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Who this evaluation is for
   */
  var patient: Reference,
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
  /**
   * completed | entered-in-error
   */
  var status: String? = null,
  /**
   * Evaluation target disease
   */
  var targetDisease: CodeableConcept,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
