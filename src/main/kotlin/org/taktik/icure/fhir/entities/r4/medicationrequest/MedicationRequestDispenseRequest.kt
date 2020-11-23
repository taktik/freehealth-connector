//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationrequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Medication supply authorization
 *
 * Indicates the specific details for the dispense or medication supply part of a medication request
 * (also known as a Medication Prescription or Medication Order).  Note that this information is not
 * always sent with the order.  There may be in some settings (e.g. hospitals) institutional or system
 * support for completing the dispense details in the pharmacy department.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationRequestDispenseRequest(
  /**
   * Minimum period of time between dispenses
   */
  var dispenseInterval: Duration? = null,
  /**
   * Number of days supply per dispense
   */
  var expectedSupplyDuration: Duration? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * First fill details
   */
  var initialFill: MedicationRequestDispenseRequestInitialFill? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Number of refills authorized
   */
  var numberOfRepeatsAllowed: Int? = null,
  /**
   * Intended dispenser
   */
  var performer: Reference? = null,
  /**
   * Amount of medication to supply per dispense
   */
  var quantity: Quantity? = null,
  /**
   * Time period supply is authorized for
   */
  var validityPeriod: Period? = null
) : BackboneElement
