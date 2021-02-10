//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationknowledge

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The maximum number of units of the medication that can be dispensed in a period
 *
 * The maximum number of units of the medication that can be dispensed in a period.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationKnowledgeRegulatoryMaxDispense(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The period that applies to the maximum number of units
   */
  var period: Duration? = null,
  /**
   * The maximum number of units of the medication that can be dispensed
   */
  var quantity: Quantity
) : BackboneElement
