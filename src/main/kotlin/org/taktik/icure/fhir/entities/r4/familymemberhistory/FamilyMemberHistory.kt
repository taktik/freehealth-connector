//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.familymemberhistory

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Information about patient's relatives, relevant for patient
 *
 * Significant health conditions for a person related to the patient relevant in the context of care
 * for the patient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class FamilyMemberHistory(
  /**
   * (approximate) age
   */
  var ageAge: Age? = null,
  /**
   * (approximate) age
   */
  var ageRange: Range? = null,
  /**
   * (approximate) age
   */
  var ageString: String? = null,
  /**
   * (approximate) date of birth
   */
  var bornDate: String? = null,
  /**
   * (approximate) date of birth
   */
  var bornPeriod: Period? = null,
  /**
   * (approximate) date of birth
   */
  var bornString: String? = null,
  var condition: List<FamilyMemberHistoryCondition> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * subject-unknown | withheld | unable-to-obtain | deferred
   */
  var dataAbsentReason: CodeableConcept? = null,
  /**
   * When history was recorded or last updated
   */
  var date: String? = null,
  /**
   * Dead? How old/when?
   */
  var deceasedAge: Age? = null,
  /**
   * Dead? How old/when?
   */
  var deceasedBoolean: Boolean? = null,
  /**
   * Dead? How old/when?
   */
  var deceasedDate: String? = null,
  /**
   * Dead? How old/when?
   */
  var deceasedRange: Range? = null,
  /**
   * Dead? How old/when?
   */
  var deceasedString: String? = null,
  /**
   * Age is estimated?
   */
  var estimatedAge: Boolean? = null,
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
  var instantiatesCanonical: List<String> = listOf(),
  var instantiatesUri: List<String> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The family member described
   */
  var name: String? = null,
  var note: List<Annotation> = listOf(),
  /**
   * Patient history is about
   */
  var patient: Reference,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * Relationship to the subject
   */
  var relationship: CodeableConcept,
  /**
   * male | female | other | unknown
   */
  var sex: CodeableConcept? = null,
  /**
   * partial | completed | entered-in-error | health-unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
