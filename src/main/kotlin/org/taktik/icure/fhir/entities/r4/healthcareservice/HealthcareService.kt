//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.healthcareservice

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
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The details of a healthcare service available at a location
 *
 * The details of a healthcare service available at a location.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class HealthcareService(
  /**
   * Whether this HealthcareService record is in active use
   */
  var active: Boolean? = null,
  /**
   * If an appointment is required for access to this service
   */
  var appointmentRequired: Boolean? = null,
  /**
   * Description of availability exceptions
   */
  var availabilityExceptions: String? = null,
  var availableTime: List<HealthcareServiceAvailableTime> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  var characteristic: List<CodeableConcept> = listOf(),
  /**
   * Additional description and/or any specific issues not covered elsewhere
   */
  var comment: String? = null,
  var communication: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  var coverageArea: List<Reference> = listOf(),
  var eligibility: List<HealthcareServiceEligibility> = listOf(),
  var endpoint: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Extra details about the service that can't be placed in the other fields
   */
  var extraDetails: String? = null,
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
  var location: List<Reference> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Description of service as presented to a consumer while searching
   */
  var name: String? = null,
  var notAvailable: List<HealthcareServiceNotAvailable> = listOf(),
  /**
   * Facilitates quick identification of the service
   */
  var photo: Attachment? = null,
  var program: List<CodeableConcept> = listOf(),
  /**
   * Organization that provides this service
   */
  var providedBy: Reference? = null,
  var referralMethod: List<CodeableConcept> = listOf(),
  var serviceProvisionCode: List<CodeableConcept> = listOf(),
  var specialty: List<CodeableConcept> = listOf(),
  var telecom: List<ContactPoint> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var type: List<CodeableConcept> = listOf()
) : DomainResource
