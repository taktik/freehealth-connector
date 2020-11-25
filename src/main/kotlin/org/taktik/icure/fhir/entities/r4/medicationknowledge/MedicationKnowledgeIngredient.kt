//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationknowledge

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Active or inactive ingredient
 *
 * Identifies a particular constituent of interest in the product.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationKnowledgeIngredient(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Active ingredient indicator
   */
  var isActive: Boolean? = null,
  /**
   * Medication(s) or substance(s) contained in the medication
   */
  var itemCodeableConcept: CodeableConcept,
  /**
   * Medication(s) or substance(s) contained in the medication
   */
  var itemReference: Reference,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Quantity of ingredient present
   */
  var strength: Ratio? = null
) : BackboneElement
