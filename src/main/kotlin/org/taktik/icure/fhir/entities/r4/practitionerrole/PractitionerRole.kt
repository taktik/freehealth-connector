//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.practitionerrole

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
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Roles/organizations the practitioner is associated with
 *
 * A specific set of Roles/Locations/specialties/services that a practitioner may perform at an
 * organization for a period of time.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class PractitionerRole(
  /**
   * Whether this practitioner role record is in active use
   */
  var active: Boolean? = null,
  /**
   * Description of availability exceptions
   */
  var availabilityExceptions: String? = null,
  var availableTime: List<PractitionerRoleAvailableTime> = listOf(),
  var code: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  var endpoint: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  var healthcareService: List<Reference> = listOf(),
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
  var notAvailable: List<PractitionerRoleNotAvailable> = listOf(),
  /**
   * Organization where the roles are available
   */
  var organization: Reference? = null,
  /**
   * The period during which the practitioner is authorized to perform in these role(s)
   */
  var period: Period? = null,
  /**
   * Practitioner that is able to provide the defined services for the organization
   */
  var practitioner: Reference? = null,
  var specialty: List<CodeableConcept> = listOf(),
  var telecom: List<ContactPoint> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
