//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.appointment

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
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A booking of a healthcare event among patient(s), practitioner(s), related person(s) and/or
 * device(s) for a specific date/time. This may result in one or more Encounter(s)
 *
 * A booking of a healthcare event among patient(s), practitioner(s), related person(s) and/or
 * device(s) for a specific date/time. This may result in one or more Encounter(s).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Appointment(
  /**
   * The style of appointment or patient that has been booked in the slot (not service type)
   */
  var appointmentType: CodeableConcept? = null,
  var basedOn: List<Reference> = listOf(),
  /**
   * The coded reason for the appointment being cancelled
   */
  var cancelationReason: CodeableConcept? = null,
  /**
   * Additional comments
   */
  var comment: String? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * The date that this appointment was initially created
   */
  var created: String? = null,
  /**
   * Shown on a subject line in a meeting request, or appointment list
   */
  var description: String? = null,
  /**
   * When appointment is to conclude
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
  /**
   * Can be less than start/end (e.g. estimate)
   */
  var minutesDuration: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var participant: List<AppointmentParticipant> = listOf(),
  /**
   * Detailed information and instructions for the patient
   */
  var patientInstruction: String? = null,
  /**
   * Used to make informed decisions if needing to re-prioritize
   */
  var priority: Int? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  var requestedPeriod: List<Period> = listOf(),
  var serviceCategory: List<CodeableConcept> = listOf(),
  var serviceType: List<CodeableConcept> = listOf(),
  var slot: List<Reference> = listOf(),
  var specialty: List<CodeableConcept> = listOf(),
  /**
   * When appointment is to take place
   */
  var start: String? = null,
  /**
   * proposed | pending | booked | arrived | fulfilled | cancelled | noshow | entered-in-error |
   * checked-in | waitlist
   */
  var status: String? = null,
  var supportingInformation: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
