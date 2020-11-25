//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicationstatement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.dosage.Dosage
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Record of medication being taken by a patient
 *
 * A record of a medication that is being consumed by a patient.   A MedicationStatement may
 * indicate that the patient may be taking the medication now or has taken the medication in the past
 * or will be taking the medication in the future.  The source of this information can be the patient,
 * significant other (such as a family member or spouse), or a clinician.  A common scenario where this
 * information is captured is during the history taking process during a patient visit or stay.   The
 * medication information may come from sources such as the patient's memory, from a prescription
 * bottle,  or from a list of medications the patient, clinician or other party maintains.
 *
 * The primary difference between a medication statement and a medication administration is that the
 * medication administration has complete administration information and is based on actual
 * administration information from the person who administered the medication.  A medication statement
 * is often, if not always, less specific.  There is no required date/time when the medication was
 * administered, in fact we only know that a source has reported the patient is taking this medication,
 * where details such as time, quantity, or rate or even medication product may be incomplete or
 * missing or less precise.  As stated earlier, the medication statement information may come from the
 * patient's memory, from a prescription bottle or from a list of medications the patient, clinician or
 * other party maintains.  Medication administration is more formal and is not missing detailed
 * information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicationStatement(
  var basedOn: List<Reference> = listOf(),
  /**
   * Type of medication usage
   */
  var category: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter / Episode associated with MedicationStatement
   */
  var context: Reference? = null,
  /**
   * When the statement was asserted?
   */
  var dateAsserted: String? = null,
  var derivedFrom: List<Reference> = listOf(),
  var dosage: List<Dosage> = listOf(),
  /**
   * The date/time or intervar when the medication is/was/will be taken
   */
  var effectiveDateTime: String? = null,
  /**
   * The date/time or intervar when the medication is/was/will be taken
   */
  var effectivePeriod: Period? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Person or organization that provided the information about the taking of this medication
   */
  var informationSource: Reference? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * What medication was taken
   */
  var medicationCodeableConcept: CodeableConcept,
  /**
   * What medication was taken
   */
  var medicationReference: Reference,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var partOf: List<Reference> = listOf(),
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * active | completed | entered-in-error | intended | stopped | on-hold | unknown | not-taken
   */
  var status: String? = null,
  var statusReason: List<CodeableConcept> = listOf(),
  /**
   * Who is/was taking  the medication
   */
  var subject: Reference,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
