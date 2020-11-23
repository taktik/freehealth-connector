//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.slot

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
 * A slot of time on a schedule that may be available for booking appointments
 *
 * A slot of time on a schedule that may be available for booking appointments.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Slot(
  /**
   * The style of appointment or patient that may be booked in the slot (not service type)
   */
  var appointmentType: CodeableConcept? = null,
  /**
   * Comments on the slot to describe any extended information. Such as custom constraints on the
   * slot
   */
  var comment: String? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Date/Time that the slot is to conclude
   */
  var end: String? = null,
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
   * This slot has already been overbooked, appointments are unlikely to be accepted for this time
   */
  var overbooked: Boolean? = null,
  /**
   * The schedule resource that this slot defines an intervar of status information
   */
  var schedule: Reference,
  var serviceCategory: List<CodeableConcept> = listOf(),
  var serviceType: List<CodeableConcept> = listOf(),
  var specialty: List<CodeableConcept> = listOf(),
  /**
   * Date/Time that the slot is to begin
   */
  var start: String? = null,
  /**
   * busy | free | busy-unavailable | busy-tentative | entered-in-error
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
