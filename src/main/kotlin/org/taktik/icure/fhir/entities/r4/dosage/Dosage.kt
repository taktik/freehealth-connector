//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.dosage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * How the medication is/was taken or should be taken
 *
 * Indicates how the medication is/was taken or should be taken by the patient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Dosage(
  var additionalInstruction: List<CodeableConcept> = listOf(),
  /**
   * Take "as needed" (for x)
   */
  var asNeededBoolean: Boolean? = null,
  /**
   * Take "as needed" (for x)
   */
  var asNeededCodeableConcept: CodeableConcept? = null,
  var doseAndRate: List<DosageDoseAndRate> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Upper limit on medication per administration
   */
  var maxDosePerAdministration: Quantity? = null,
  /**
   * Upper limit on medication per lifetime of the patient
   */
  var maxDosePerLifetime: Quantity? = null,
  /**
   * Upper limit on medication per unit of time
   */
  var maxDosePerPeriod: Ratio? = null,
  /**
   * Technique for administering medication
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Patient or consumer oriented instructions
   */
  var patientInstruction: String? = null,
  /**
   * How drug should enter body
   */
  var route: CodeableConcept? = null,
  /**
   * The order of the dosage instructions
   */
  var sequence: Int? = null,
  /**
   * Body site to administer to
   */
  var site: CodeableConcept? = null,
  /**
   * Free text dosage instructions e.g. SIG
   */
  var text: String? = null,
  /**
   * When medication should be administered
   */
  var timing: Timing? = null
) : BackboneElement
