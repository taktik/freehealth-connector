//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.devicerequest

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
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Medical device request
 *
 * Represents a request for a patient to employ a medical device. The device may be an implantable
 * device, or an external assistive device, such as a walker.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class DeviceRequest(
  /**
   * When recorded
   */
  var authoredOn: String? = null,
  var basedOn: List<Reference> = listOf(),
  /**
   * Device requested
   */
  var codeCodeableConcept: CodeableConcept,
  /**
   * Device requested
   */
  var codeReference: Reference,
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter motivating request
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Identifier of composite request
   */
  var groupIdentifier: Identifier? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var instantiatesCanonical: List<String> = listOf(),
  var instantiatesUri: List<String> = listOf(),
  var insurance: List<Reference> = listOf(),
  /**
   * proposal | plan | directive | order | original-order | reflex-order | filler-order |
   * instance-order | option
   */
  var intent: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Desired time or schedule for use
   */
  var occurrenceDateTime: String? = null,
  /**
   * Desired time or schedule for use
   */
  var occurrencePeriod: Period? = null,
  /**
   * Desired time or schedule for use
   */
  var occurrenceTiming: Timing? = null,
  var parameter: List<DeviceRequestParameter> = listOf(),
  /**
   * Requested Filler
   */
  var performer: Reference? = null,
  /**
   * Filler role
   */
  var performerType: CodeableConcept? = null,
  var priorRequest: List<Reference> = listOf(),
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  var relevantHistory: List<Reference> = listOf(),
  /**
   * Who/what is requesting diagnostics
   */
  var requester: Reference? = null,
  /**
   * draft | active | on-hold | revoked | completed | entered-in-error | unknown
   */
  var status: String? = null,
  /**
   * Focus of request
   */
  var subject: Reference,
  var supportingInfo: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
