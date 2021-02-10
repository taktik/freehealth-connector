//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.appointmentresponse

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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A reply to an appointment request for a patient and/or practitioner(s), such as a confirmation or
 * rejection
 *
 * A reply to an appointment request for a patient and/or practitioner(s), such as a confirmation or
 * rejection.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class AppointmentResponse(
  /**
   * Person, Location, HealthcareService, or Device
   */
  var actor: Reference? = null,
  /**
   * Appointment this response relates to
   */
  var appointment: Reference,
  /**
   * Additional comments
   */
  var comment: String? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Time from appointment, or requested new end time
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
   * accepted | declined | tentative | needs-action
   */
  var participantStatus: String? = null,
  var participantType: List<CodeableConcept> = listOf(),
  /**
   * Time from appointment, or requested new start time
   */
  var start: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
