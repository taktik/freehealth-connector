//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationadministration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.ratio.Ratio

/**
 * Details of how medication was taken
 *
 * Describes the medication dosage information details e.g. dose, rate, site, route, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationAdministrationDosage(
  /**
   * Amount of medication per dose
   */
  var dose: Quantity? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * How drug was administered
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Dose quantity per unit of time
   */
  var rateQuantity: Quantity? = null,
  /**
   * Dose quantity per unit of time
   */
  var rateRatio: Ratio? = null,
  /**
   * Path of substance into body
   */
  var route: CodeableConcept? = null,
  /**
   * Body site administered to
   */
  var site: CodeableConcept? = null,
  /**
   * Free text dosage instructions e.g. SIG
   */
  var text: String? = null
) : BackboneElement
