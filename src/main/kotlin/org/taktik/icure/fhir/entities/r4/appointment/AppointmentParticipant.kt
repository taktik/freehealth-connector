//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.appointment

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Participants involved in appointment
 *
 * List of participants involved in the appointment.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class AppointmentParticipant(
  /**
   * Person, Location/HealthcareService or Device
   */
  var actor: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Participation period of the actor
   */
  var period: Period? = null,
  /**
   * required | optional | information-only
   */
  var required: String? = null,
  /**
   * accepted | declined | tentative | needs-action
   */
  var status: String? = null,
  var type: List<CodeableConcept> = listOf()
) : BackboneElement
