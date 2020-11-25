//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.messageheader

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
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A resource that describes a message that is exchanged between systems
 *
 * The header for a message exchange that is either requesting or responding to an action.  The
 * reference(s) that are the subject of the action as well as other information related to the action
 * are typically transmitted in a bundle in which the MessageHeader resource instance is the first
 * resource in the bundle.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MessageHeader(
  /**
   * The source of the decision
   */
  var author: Reference? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Link to the definition for this message
   */
  var definition: String? = null,
  var destination: List<MessageHeaderDestination> = listOf(),
  /**
   * The source of the data entry
   */
  var enterer: Reference? = null,
  /**
   * Code for the event this message represents or link to event definition
   */
  var eventCoding: Coding,
  /**
   * Code for the event this message represents or link to event definition
   */
  var eventUri: String? = null,
  override var extension: List<Extension> = listOf(),
  var focus: List<Reference> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Cause of event
   */
  var reason: CodeableConcept? = null,
  /**
   * If this is a reply to prior message
   */
  var response: MessageHeaderResponse? = null,
  /**
   * Final responsibility for event
   */
  var responsible: Reference? = null,
  /**
   * Real world sender of the message
   */
  var sender: Reference? = null,
  /**
   * Message source application
   */
  var source: MessageHeaderSource,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
