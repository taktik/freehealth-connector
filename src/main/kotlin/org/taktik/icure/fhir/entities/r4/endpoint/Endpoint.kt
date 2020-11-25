//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.endpoint

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
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The technical details of an endpoint that can be used for electronic services
 *
 * The technical details of an endpoint that can be used for electronic services, such as for web
 * services providing XDS.b or a REST endpoint for another FHIR server. This may include any security
 * context information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Endpoint(
  /**
   * The technical base address for connecting to this endpoint
   */
  var address: String? = null,
  /**
   * Protocol/Profile/Standard to be used with this endpoint connection
   */
  var connectionType: Coding,
  var contact: List<ContactPoint> = listOf(),
  override var contained: List<Resource> = listOf(),
  override var extension: List<Extension> = listOf(),
  var header: List<String> = listOf(),
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
   * Organization that manages this endpoint (might not be the organization that exposes the
   * endpoint)
   */
  var managingOrganization: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * A name that this endpoint can be identified by
   */
  var name: String? = null,
  var payloadMimeType: List<String> = listOf(),
  var payloadType: List<CodeableConcept> = listOf(),
  /**
   * Intervar the endpoint is expected to be operational
   */
  var period: Period? = null,
  /**
   * active | suspended | error | off | entered-in-error | test
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
