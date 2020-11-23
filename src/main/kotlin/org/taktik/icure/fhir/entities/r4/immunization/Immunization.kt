//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.immunization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Immunization event information
 *
 * Describes the event of a patient being administered a vaccine or a record of an immunization as
 * reported by a patient, a clinician or another party.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Immunization(
  override var contained: List<Resource> = listOf(),
  /**
   * Amount of vaccine administered
   */
  var doseQuantity: Quantity? = null,
  var education: List<ImmunizationEducation> = listOf(),
  /**
   * Encounter immunization was part of
   */
  var encounter: Reference? = null,
  /**
   * Vaccine expiration date
   */
  var expirationDate: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Funding source for the vaccine
   */
  var fundingSource: CodeableConcept? = null,
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
   * Dose potency
   */
  var isSubpotent: Boolean? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Where immunization occurred
   */
  var location: Reference? = null,
  /**
   * Vaccine lot number
   */
  var lotNumber: String? = null,
  /**
   * Vaccine manufacturer
   */
  var manufacturer: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Vaccine administration date
   */
  var occurrenceDateTime: String? = null,
  /**
   * Vaccine administration date
   */
  var occurrenceString: String? = null,
  /**
   * Who was immunized
   */
  var patient: Reference,
  var performer: List<ImmunizationPerformer> = listOf(),
  /**
   * Indicates context the data was recorded in
   */
  var primarySource: Boolean? = null,
  var programEligibility: List<CodeableConcept> = listOf(),
  var protocolApplied: List<ImmunizationProtocolApplied> = listOf(),
  var reaction: List<ImmunizationReaction> = listOf(),
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * When the immunization was first captured in the subject's record
   */
  var recorded: String? = null,
  /**
   * Indicates the source of a secondarily reported record
   */
  var reportOrigin: CodeableConcept? = null,
  /**
   * How vaccine entered body
   */
  var route: CodeableConcept? = null,
  /**
   * Body site vaccine  was administered
   */
  var site: CodeableConcept? = null,
  /**
   * completed | entered-in-error | not-done
   */
  var status: String? = null,
  /**
   * Reason not done
   */
  var statusReason: CodeableConcept? = null,
  var subpotentReason: List<CodeableConcept> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Vaccine product administered
   */
  var vaccineCode: CodeableConcept
) : DomainResource
