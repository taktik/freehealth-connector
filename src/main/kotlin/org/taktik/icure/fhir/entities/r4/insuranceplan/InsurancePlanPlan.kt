//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.insuranceplan

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Plan details
 *
 * Details about an insurance plan.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class InsurancePlanPlan(
  var coverageArea: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  var generalCost: List<InsurancePlanPlanGeneralCost> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var network: List<Reference> = listOf(),
  var specificCost: List<InsurancePlanPlanSpecificCost> = listOf(),
  /**
   * Type of plan
   */
  var type: CodeableConcept? = null
) : BackboneElement
