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
 * Pertinent diagnosis information
 *
 * Information about diagnoses relevant to the claim items.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ExplanationOfBenefitDiagnosis(
  /**
   * Nature of illness or problem
   */
  var diagnosisCodeableConcept: CodeableConcept,
  /**
   * Nature of illness or problem
   */
  var diagnosisReference: Reference,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Present on admission
   */
  var onAdmission: CodeableConcept? = null,
  /**
   * Package billing code
   */
  var packageCode: CodeableConcept? = null,
  /**
   * Diagnosis instance identifier
   */
  var sequence: Int? = null,
  var type: List<CodeableConcept> = listOf()
) : BackboneElement
