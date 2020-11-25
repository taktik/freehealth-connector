//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationknowledge

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Regulatory information about a medication
 *
 * Regulatory information about a medication.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicationKnowledgeRegulatory(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * The maximum number of units of the medication that can be dispensed in a period
   */
  var maxDispense: MedicationKnowledgeRegulatoryMaxDispense? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Specifies the authority of the regulation
   */
  var regulatoryAuthority: Reference,
  var schedule: List<MedicationKnowledgeRegulatorySchedule> = listOf(),
  var substitution: List<MedicationKnowledgeRegulatorySubstitution> = listOf()
) : BackboneElement
