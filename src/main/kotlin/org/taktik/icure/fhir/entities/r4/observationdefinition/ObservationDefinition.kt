//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.observationdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
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
 * Definition of an observation
 *
 * Set of definitional characteristics for a kind of observation or measurement produced or consumed
 * by an orderable health care service.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ObservationDefinition(
  /**
   * Value set of abnormal coded values for the observations conforming to this
   * ObservationDefinition
   */
  var abnormalCodedValueSet: Reference? = null,
  var category: List<CodeableConcept> = listOf(),
  /**
   * Type of observation (code / type)
   */
  var code: CodeableConcept,
  override var contained: List<Resource> = listOf(),
  /**
   * Value set of critical coded values for the observations conforming to this
   * ObservationDefinition
   */
  var criticalCodedValueSet: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
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
  /**
   * Method used to produce the observation
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Multiple results allowed
   */
  var multipleResultsAllowed: Boolean? = null,
  /**
   * Value set of normal coded values for the observations conforming to this ObservationDefinition
   */
  var normalCodedValueSet: Reference? = null,
  var permittedDataType: List<String> = listOf(),
  /**
   * Preferred report name
   */
  var preferredReportName: String? = null,
  var qualifiedInterval: List<ObservationDefinitionQualifiedInterval> = listOf(),
  /**
   * Characteristics of quantitative results
   */
  var quantitativeDetails: ObservationDefinitionQuantitativeDetails? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Value set of valid coded values for the observations conforming to this ObservationDefinition
   */
  var validCodedValueSet: Reference? = null
) : DomainResource
