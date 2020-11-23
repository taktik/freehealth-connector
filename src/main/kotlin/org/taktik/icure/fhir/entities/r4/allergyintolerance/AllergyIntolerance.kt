//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.allergyintolerance

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
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
 * Allergy or Intolerance (generally: Risk of adverse reaction to a substance)
 *
 * Risk of harmful or undesirable, physiological response which is unique to an individual and
 * associated with exposure to a substance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class AllergyIntolerance(
  /**
   * Source of the information about the allergy
   */
  var asserter: Reference? = null,
  var category: List<String> = listOf(),
  /**
   * active | inactive | resolved
   */
  var clinicalStatus: CodeableConcept? = null,
  /**
   * Code that identifies the allergy or intolerance
   */
  var code: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * low | high | unable-to-assess
   */
  var criticality: String? = null,
  /**
   * Encounter when the allergy or intolerance was asserted
   */
  var encounter: Reference? = null,
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
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Date(/time) of last known occurrence of a reaction
   */
  var lastOccurrence: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * When allergy or intolerance was identified
   */
  var onsetAge: Age? = null,
  /**
   * When allergy or intolerance was identified
   */
  var onsetDateTime: String? = null,
  /**
   * When allergy or intolerance was identified
   */
  var onsetPeriod: Period? = null,
  /**
   * When allergy or intolerance was identified
   */
  var onsetRange: Range? = null,
  /**
   * When allergy or intolerance was identified
   */
  var onsetString: String? = null,
  /**
   * Who the sensitivity is for
   */
  var patient: Reference,
  var reaction: List<AllergyIntoleranceReaction> = listOf(),
  /**
   * Date first version of the resource instance was recorded
   */
  var recordedDate: String? = null,
  /**
   * Who recorded the sensitivity
   */
  var recorder: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * allergy | intolerance - Underlying mechanism (if known)
   */
  var type: String? = null,
  /**
   * unconfirmed | confirmed | refuted | entered-in-error
   */
  var verificationStatus: CodeableConcept? = null
) : DomainResource
