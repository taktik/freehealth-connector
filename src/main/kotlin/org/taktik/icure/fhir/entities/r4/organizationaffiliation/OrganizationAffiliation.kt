//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.organizationaffiliation

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
 * Defines an affiliation/assotiation/relationship between 2 distinct oganizations, that is not a
 * part-of relationship/sub-division relationship
 *
 * Defines an affiliation/assotiation/relationship between 2 distinct oganizations, that is not a
 * part-of relationship/sub-division relationship.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class OrganizationAffiliation(
  /**
   * Whether this organization affiliation record is in active use
   */
  var active: Boolean? = null,
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
  var network: List<Reference> = listOf(),
  /**
   * Organization where the role is available
   */
  var organization: Reference? = null,
  /**
   * Organization that provides/performs the role (e.g. providing services or is a member of)
   */
  var participatingOrganization: Reference? = null,
  /**
   * The period during which the participatingOrganization is affiliated with the primary
   * organization
   */
  var period: Period? = null,
  var specialty: List<CodeableConcept> = listOf(),
  var telecom: List<ContactPoint> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
