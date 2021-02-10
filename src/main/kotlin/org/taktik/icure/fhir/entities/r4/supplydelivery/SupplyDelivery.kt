//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.supplydelivery

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Delivery of bulk Supplies
 *
 * Record of delivery of what is supplied.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class SupplyDelivery(
  var basedOn: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Where the Supply was sent
   */
  var destination: Reference? = null,
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
   * When event occurred
   */
  var occurrenceDateTime: String? = null,
  /**
   * When event occurred
   */
  var occurrencePeriod: Period? = null,
  /**
   * When event occurred
   */
  var occurrenceTiming: Timing? = null,
  var partOf: List<Reference> = listOf(),
  /**
   * Patient for whom the item is supplied
   */
  var patient: Reference? = null,
  var receiver: List<Reference> = listOf(),
  /**
   * in-progress | completed | abandoned | entered-in-error
   */
  var status: String? = null,
  /**
   * The item that is delivered or supplied
   */
  var suppliedItem: SupplyDeliverySuppliedItem? = null,
  /**
   * Dispenser
   */
  var supplier: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Category of dispense event
   */
  var type: CodeableConcept? = null
) : DomainResource
