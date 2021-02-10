//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.episodeofcare

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * An association of a Patient with an Organization and  Healthcare Provider(s) for a period of time
 * that the Organization assumes some level of responsibility
 *
 * An association between a patient and an organization / healthcare provider(s) during which time
 * encounters may occur. The managing organization assumes a level of responsibility for the patient
 * during this time.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class EpisodeOfCare(
  var account: List<Reference> = listOf(),
  /**
   * Care manager/care coordinator for the patient
   */
  var careManager: Reference? = null,
  override var contained: List<Resource> = listOf(),
  var diagnosis: List<EpisodeOfCareDiagnosis> = listOf(),
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
   * Organization that assumes care
   */
  var managingOrganization: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The patient who is the focus of this episode of care
   */
  var patient: Reference,
  /**
   * Intervar during responsibility is assumed
   */
  var period: Period? = null,
  var referralRequest: List<Reference> = listOf(),
  /**
   * planned | waitlist | active | onhold | finished | cancelled | entered-in-error
   */
  var status: String? = null,
  var statusHistory: List<EpisodeOfCareStatusHistory> = listOf(),
  var team: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var type: List<CodeableConcept> = listOf()
) : DomainResource
