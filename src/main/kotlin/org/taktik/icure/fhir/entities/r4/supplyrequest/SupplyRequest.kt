//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.supplyrequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Request for a medication, substance or device
 *
 * A record of a request for a medication, substance or device used in the healthcare setting.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class SupplyRequest(
  /**
   * When the request was made
   */
  var authoredOn: String? = null,
  /**
   * The kind of supply (central, non-stock, etc.)
   */
  var category: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * The origin of the supply
   */
  var deliverFrom: Reference? = null,
  /**
   * The destination of the supply
   */
  var deliverTo: Reference? = null,
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
   * Medication, Substance, or Device requested to be supplied
   */
  var itemCodeableConcept: CodeableConcept,
  /**
   * Medication, Substance, or Device requested to be supplied
   */
  var itemReference: Reference,
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
   * When the request should be fulfilled
   */
  var occurrenceDateTime: String? = null,
  /**
   * When the request should be fulfilled
   */
  var occurrencePeriod: Period? = null,
  /**
   * When the request should be fulfilled
   */
  var occurrenceTiming: Timing? = null,
  var parameter: List<SupplyRequestParameter> = listOf(),
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  /**
   * The requested amount of the item indicated
   */
  var quantity: Quantity,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * Individual making the request
   */
  var requester: Reference? = null,
  /**
   * draft | active | suspended +
   */
  var status: String? = null,
  var supplier: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
