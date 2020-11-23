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
 * Specifies descriptive properties of the medicine
 *
 * Specifies descriptive properties of the medicine, such as color, shape, imprints, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationKnowledgeDrugCharacteristic(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Code specifying the type of characteristic of medication
   */
  var type: CodeableConcept? = null,
  /**
   * Description of the characteristic
   */
  var valueBase64Binary: String? = null,
  /**
   * Description of the characteristic
   */
  var valueCodeableConcept: CodeableConcept? = null,
  /**
   * Description of the characteristic
   */
  var valueQuantity: Quantity? = null,
  /**
   * Description of the characteristic
   */
  var valueString: String? = null
) : BackboneElement
