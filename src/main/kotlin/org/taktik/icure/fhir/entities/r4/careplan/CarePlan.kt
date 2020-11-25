//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.careplan

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
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Healthcare plan for patient or group
 *
 * Describes the intention of how one or more practitioners intend to deliver care for a particular
 * patient, group or community for a period of time, possibly limited to care for a specific condition
 * or set of conditions.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class CarePlan(
  var activity: List<CarePlanActivity> = listOf(),
  var addresses: List<Reference> = listOf(),
  /**
   * Who is the designated responsible party
   */
  var author: Reference? = null,
  var basedOn: List<Reference> = listOf(),
  var careTeam: List<Reference> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  var contributor: List<Reference> = listOf(),
  /**
   * Date record was first recorded
   */
  var created: String? = null,
  /**
   * Summary of nature of plan
   */
  var description: String? = null,
  /**
   * Encounter created as part of
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  var goal: List<Reference> = listOf(),
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
   * proposal | plan | order | option
   */
  var intent: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var partOf: List<Reference> = listOf(),
  /**
   * Time period plan covers
   */
  var period: Period? = null,
  var replaces: List<Reference> = listOf(),
  /**
   * draft | active | on-hold | revoked | completed | entered-in-error | unknown
   */
  var status: String? = null,
  /**
   * Who the care plan is for
   */
  var subject: Reference,
  var supportingInfo: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Human-friendly name for the care plan
   */
  var title: String? = null
) : DomainResource
