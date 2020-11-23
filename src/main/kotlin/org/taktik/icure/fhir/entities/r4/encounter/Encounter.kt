//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.encounter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * An interaction during which services are provided to the patient
 *
 * An interaction between a patient and healthcare provider(s) for the purpose of providing
 * healthcare service(s) or assessing the health status of a patient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Encounter(
  var account: List<Reference> = listOf(),
  var appointment: List<Reference> = listOf(),
  var basedOn: List<Reference> = listOf(),
  var classHistory: List<EncounterClassHistory> = listOf(),
  /**
   * Classification of patient encounter
   */
  @JsonProperty("class")
  var class_fhir: Coding,
  override var contained: List<Resource> = listOf(),
  var diagnosis: List<EncounterDiagnosis> = listOf(),
  var episodeOfCare: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Details about the admission to a healthcare service
   */
  var hospitalization: EncounterHospitalization? = null,
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
   * Quantity of time the encounter lasted (less time absent)
   */
  var length: Duration? = null,
  var location: List<EncounterLocation> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Another Encounter this encounter is part of
   */
  var partOf: Reference? = null,
  var participant: List<EncounterParticipant> = listOf(),
  /**
   * The start and end time of the encounter
   */
  var period: Period? = null,
  /**
   * Indicates the urgency of the encounter
   */
  var priority: CodeableConcept? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * The organization (facility) responsible for this encounter
   */
  var serviceProvider: Reference? = null,
  /**
   * Specific type of service
   */
  var serviceType: CodeableConcept? = null,
  /**
   * planned | arrived | triaged | in-progress | onleave | finished | cancelled +
   */
  var status: String? = null,
  var statusHistory: List<EncounterStatusHistory> = listOf(),
  /**
   * The patient or group present at the encounter
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var type: List<CodeableConcept> = listOf()
) : DomainResource
