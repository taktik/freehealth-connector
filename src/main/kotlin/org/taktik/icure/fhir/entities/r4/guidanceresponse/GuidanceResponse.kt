//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.guidanceresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.datarequirement.DataRequirement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The formal response to a guidance request
 *
 * A guidance response is the formal response to a guidance request, including any output parameters
 * returned by the evaluation, as well as the description of any proposed actions to be taken.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class GuidanceResponse(
  override var contained: List<Resource> = listOf(),
  var dataRequirement: List<DataRequirement> = listOf(),
  /**
   * Encounter during which the response was returned
   */
  var encounter: Reference? = null,
  var evaluationMessage: List<Reference> = listOf(),
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
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * What guidance was requested
   */
  var moduleCanonical: String? = null,
  /**
   * What guidance was requested
   */
  var moduleCodeableConcept: CodeableConcept,
  /**
   * What guidance was requested
   */
  var moduleUri: String? = null,
  var note: List<Annotation> = listOf(),
  /**
   * When the guidance response was processed
   */
  var occurrenceDateTime: String? = null,
  /**
   * The output parameters of the evaluation, if any
   */
  var outputParameters: Reference? = null,
  /**
   * Device returning the guidance
   */
  var performer: Reference? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * The identifier of the request associated with this response, if any
   */
  var requestIdentifier: Identifier? = null,
  /**
   * Proposed actions, if any
   */
  var result: Reference? = null,
  /**
   * success | data-requested | data-required | in-progress | failure | entered-in-error
   */
  var status: String? = null,
  /**
   * Patient the request was performed for
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
