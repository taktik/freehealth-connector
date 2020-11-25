//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationknowledge

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Definition of Medication Knowledge
 *
 * Information about a medication that is used to support knowledge.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicationKnowledge(
  var administrationGuidelines: List<MedicationKnowledgeAdministrationGuidelines> = listOf(),
  /**
   * Amount of drug in package
   */
  var amount: Quantity? = null,
  var associatedMedication: List<Reference> = listOf(),
  /**
   * Code that identifies this medication
   */
  var code: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  var contraindication: List<Reference> = listOf(),
  var cost: List<MedicationKnowledgeCost> = listOf(),
  /**
   * powder | tablets | capsule +
   */
  var doseForm: CodeableConcept? = null,
  var drugCharacteristic: List<MedicationKnowledgeDrugCharacteristic> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var ingredient: List<MedicationKnowledgeIngredient> = listOf(),
  var intendedRoute: List<CodeableConcept> = listOf(),
  var kinetics: List<MedicationKnowledgeKinetics> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Manufacturer of the item
   */
  var manufacturer: Reference? = null,
  var medicineClassification: List<MedicationKnowledgeMedicineClassification> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var monitoringProgram: List<MedicationKnowledgeMonitoringProgram> = listOf(),
  var monograph: List<MedicationKnowledgeMonograph> = listOf(),
  /**
   * Details about packaged medications
   */
  var packaging: MedicationKnowledgePackaging? = null,
  /**
   * The instructions for preparing the medication
   */
  var preparationInstruction: String? = null,
  var productType: List<CodeableConcept> = listOf(),
  var regulatory: List<MedicationKnowledgeRegulatory> = listOf(),
  var relatedMedicationKnowledge: List<MedicationKnowledgeRelatedMedicationKnowledge> = listOf(),
  /**
   * active | inactive | entered-in-error
   */
  var status: String? = null,
  var synonym: List<String> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
