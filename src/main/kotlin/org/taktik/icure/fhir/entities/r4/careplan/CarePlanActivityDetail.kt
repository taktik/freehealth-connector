//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.careplan

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * In-line definition of activity
 *
 * A simple summary of a planned activity suitable for a general care plan system (e.g. form driven)
 * that doesn't know about specific resources such as procedure etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CarePlanActivityDetail(
  /**
   * Detail type of activity
   */
  var code: CodeableConcept? = null,
  /**
   * How to consume/day?
   */
  var dailyAmount: Quantity? = null,
  /**
   * Extra info describing activity to perform
   */
  var description: String? = null,
  /**
   * If true, activity is prohibiting action
   */
  var doNotPerform: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var goal: List<Reference> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var instantiatesCanonical: List<String> = listOf(),
  var instantiatesUri: List<String> = listOf(),
  /**
   * Appointment | CommunicationRequest | DeviceRequest | MedicationRequest | NutritionOrder | Task
   * | ServiceRequest | VisionPrescription
   */
  var kind: String? = null,
  /**
   * Where it should happen
   */
  var location: Reference? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var performer: List<Reference> = listOf(),
  /**
   * What is to be administered/supplied
   */
  var productCodeableConcept: CodeableConcept? = null,
  /**
   * What is to be administered/supplied
   */
  var productReference: Reference? = null,
  /**
   * How much to administer/supply/consume
   */
  var quantity: Quantity? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * When activity is to occur
   */
  var scheduledPeriod: Period? = null,
  /**
   * When activity is to occur
   */
  var scheduledString: String? = null,
  /**
   * When activity is to occur
   */
  var scheduledTiming: Timing? = null,
  /**
   * not-started | scheduled | in-progress | on-hold | completed | cancelled | stopped | unknown |
   * entered-in-error
   */
  var status: String? = null,
  /**
   * Reason for current status
   */
  var statusReason: CodeableConcept? = null
) : BackboneElement
