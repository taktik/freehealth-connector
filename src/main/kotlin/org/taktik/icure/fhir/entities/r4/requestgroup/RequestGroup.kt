//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.requestgroup

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
 * A group of related requests
 *
 * A group of related requests that can be used to capture intended activities that have
 * inter-dependencies such as "give this medication after that one".
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class RequestGroup(
  var action: List<RequestGroupAction> = listOf(),
  /**
   * Device or practitioner that authored the request group
   */
  var author: Reference? = null,
  /**
   * When the request group was authored
   */
  var authoredOn: String? = null,
  var basedOn: List<Reference> = listOf(),
  /**
   * What's being requested/ordered
   */
  var code: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Created as part of
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Composite request this is part of
   */
  var groupIdentifier: Identifier? = null,
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
   * proposal | plan | directive | order | original-order | reflex-order | filler-order |
   * instance-order | option
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
  /**
   * routine | urgent | asap | stat
   */
  var priority: String? = null,
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  var replaces: List<Reference> = listOf(),
  /**
   * draft | active | on-hold | revoked | completed | entered-in-error | unknown
   */
  var status: String? = null,
  /**
   * Who the request group is about
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
