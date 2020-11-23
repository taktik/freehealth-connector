//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.servicerequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * A request for a service to be performed
 *
 * A record of a request for service such as diagnostic investigations, treatments, or operations to
 * be performed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ServiceRequest(
  /**
   * Preconditions for service
   */
  var asNeededBoolean: Boolean? = null,
  /**
   * Preconditions for service
   */
  var asNeededCodeableConcept: CodeableConcept? = null,
  /**
   * Date request signed
   */
  var authoredOn: String? = null,
  var basedOn: List<Reference> = listOf(),
  var bodySite: List<CodeableConcept> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  /**
   * What is being requested/ordered
   */
  var code: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * True if service/procedure should not be performed
   */
  var doNotPerform: Boolean? = null,
  /**
   * Encounter in which the request was created
   */
  var encounter: Reference? = null,
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
  var locationCode: List<CodeableConcept> = listOf(),
  var locationReference: List<Reference> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * When service should occur
   */
  var occurrenceDateTime: String? = null,
  /**
   * When service should occur
   */
  var occurrencePeriod: Period? = null,
  /**
   * When service should occur
   */
  var occurrenceTiming: Timing? = null,
  var orderDetail: List<CodeableConcept> = listOf(),
  /**
   * Patient or consumer-oriented instructions
   */
  var patientInstruction: String? = null,
  var performer: List<Reference> = listOf(),
  /**
   * Performer role
   */
  var performerType: CodeableConcept? = null,
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  /**
   * Service amount
   */
  var quantityQuantity: Quantity? = null,
  /**
   * Service amount
   */
  var quantityRange: Range? = null,
  /**
   * Service amount
   */
  var quantityRatio: Ratio? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  var relevantHistory: List<Reference> = listOf(),
  var replaces: List<Reference> = listOf(),
  /**
   * Who/what is requesting service
   */
  var requester: Reference? = null,
  /**
   * Composite Request ID
   */
  var requisition: Identifier? = null,
  var specimen: List<Reference> = listOf(),
  /**
   * draft | active | on-hold | revoked | completed | entered-in-error | unknown
   */
  var status: String? = null,
  /**
   * Individual or Entity the service is ordered for
   */
  var subject: Reference,
  var supportingInfo: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
