//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationknowledge

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Guidelines for administration of the medication
 *
 * Guidelines for the administration of the medication.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationKnowledgeAdministrationGuidelines(
  var dosage: List<MedicationKnowledgeAdministrationGuidelinesDosage> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Indication for use that apply to the specific administration guidelines
   */
  var indicationCodeableConcept: CodeableConcept? = null,
  /**
   * Indication for use that apply to the specific administration guidelines
   */
  var indicationReference: Reference? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var patientCharacteristics:
      List<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristics> = listOf()
) : BackboneElement
