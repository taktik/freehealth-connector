//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.explanationofbenefit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Clinical procedures performed
 *
 * Procedures performed on the patient relevant to the billing items with the claim.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ExplanationOfBenefitProcedure(
  /**
   * When the procedure was performed
   */
  var date: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Specific clinical procedure
   */
  var procedureCodeableConcept: CodeableConcept,
  /**
   * Specific clinical procedure
   */
  var procedureReference: Reference,
  /**
   * Procedure instance identifier
   */
  var sequence: Int? = null,
  var type: List<CodeableConcept> = listOf(),
  var udi: List<Reference> = listOf()
) : BackboneElement
