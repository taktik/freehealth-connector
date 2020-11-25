//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.location

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.address.Address
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Details and position information for a physical place
 *
 * Details and position information for a physical place where services are provided and resources
 * and participants may be stored, found, contained, or accommodated.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Location(
  /**
   * Physical location
   */
  var address: Address? = null,
  var alias: List<String> = listOf(),
  /**
   * Description of availability exceptions
   */
  var availabilityExceptions: String? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Additional details about the location that could be displayed as further information to
   * identify the location beyond its name
   */
  var description: String? = null,
  var endpoint: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  var hoursOfOperation: List<LocationHoursOfOperation> = listOf(),
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
   * Organization responsible for provisioning and upkeep
   */
  var managingOrganization: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  /**
   * instance | kind
   */
  var mode: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name of the location as used by humans
   */
  var name: String? = null,
  /**
   * The operational status of the location (typically only for a bed/room)
   */
  var operationalStatus: Coding? = null,
  /**
   * Another Location this one is physically a part of
   */
  var partOf: Reference? = null,
  /**
   * Physical form of the location
   */
  var physicalType: CodeableConcept? = null,
  /**
   * The absolute geographic location
   */
  var position: LocationPosition? = null,
  /**
   * active | suspended | inactive
   */
  var status: String? = null,
  var telecom: List<ContactPoint> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var type: List<CodeableConcept> = listOf()
) : DomainResource
