//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.communication

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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A record of information transmitted from a sender to a receiver
 *
 * An occurrence of information being transmitted; e.g. an alert that was sent to a responsible
 * provider, a public health agency that was notified about a reportable condition.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Communication(
  var about: List<Reference> = listOf(),
  var basedOn: List<Reference> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter created as part of
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
  var inResponseTo: List<Reference> = listOf(),
  var instantiatesCanonical: List<String> = listOf(),
  var instantiatesUri: List<String> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var medium: List<CodeableConcept> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var partOf: List<Reference> = listOf(),
  var payload: List<CommunicationPayload> = listOf(),
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  /**
   * When received
   */
  var received: String? = null,
  var recipient: List<Reference> = listOf(),
  /**
   * Message sender
   */
  var sender: Reference? = null,
  /**
   * When sent
   */
  var sent: String? = null,
  /**
   * preparation | in-progress | not-done | on-hold | stopped | completed | entered-in-error |
   * unknown
   */
  var status: String? = null,
  /**
   * Reason for current status
   */
  var statusReason: CodeableConcept? = null,
  /**
   * Focus of message
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Description of the purpose/content
   */
  var topic: CodeableConcept? = null
) : DomainResource
