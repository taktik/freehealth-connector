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
 * The time course of drug absorption, distribution, metabolism and excretion of a medication from
 * the body
 *
 * The time course of drug absorption, distribution, metabolism and excretion of a medication from
 * the body.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationKnowledgeKinetics(
  var areaUnderCurve: List<Quantity> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Time required for concentration in the body to decrease by half
   */
  var halfLifePeriod: Duration? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var lethalDose50: List<Quantity> = listOf(),
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
