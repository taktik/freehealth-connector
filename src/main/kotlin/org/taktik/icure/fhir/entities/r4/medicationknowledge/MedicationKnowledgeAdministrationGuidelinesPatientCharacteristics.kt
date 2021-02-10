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
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Characteristics of the patient that are relevant to the administration guidelines
 *
 * Characteristics of the patient that are relevant to the administration guidelines (for example,
 * height, weight, gender, etc.).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationKnowledgeAdministrationGuidelinesPatientCharacteristics(
  /**
   * Specific characteristic that is relevant to the administration guideline
   */
  var characteristicCodeableConcept: CodeableConcept,
  /**
   * Specific characteristic that is relevant to the administration guideline
   */
  var characteristicQuantity: Quantity,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var value: List<String> = listOf()
) : BackboneElement
