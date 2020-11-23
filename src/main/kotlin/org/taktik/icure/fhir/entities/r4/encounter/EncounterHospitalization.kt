//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.encounter

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
 * Details about the admission to a healthcare service
 *
 * Details about the admission to a healthcare service.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class EncounterHospitalization(
  /**
   * From where patient was admitted (physician referral, transfer)
   */
  var admitSource: CodeableConcept? = null,
  /**
   * Location/organization to which the patient is discharged
   */
  var destination: Reference? = null,
  var dietPreference: List<CodeableConcept> = listOf(),
  /**
   * Category or kind of location after discharge
   */
  var dischargeDisposition: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The location/organization from which the patient came before admission
   */
  var origin: Reference? = null,
  /**
   * Pre-admission identifier
   */
  var preAdmissionIdentifier: Identifier? = null,
  /**
   * The type of hospital re-admission that has occurred (if any). If the value is absent, then this
   * is not identified as a readmission
   */
  var reAdmission: CodeableConcept? = null,
  var specialArrangement: List<CodeableConcept> = listOf(),
  var specialCourtesy: List<CodeableConcept> = listOf()
) : BackboneElement
