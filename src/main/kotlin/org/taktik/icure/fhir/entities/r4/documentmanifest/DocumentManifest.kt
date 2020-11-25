//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.documentmanifest

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
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A list that defines a set of documents
 *
 * A collection of documents compiled for a purpose together with metadata that applies to the
 * collection.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class DocumentManifest(
  var author: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  var content: List<Reference> = listOf(),
  /**
   * When this document manifest created
   */
  var created: String? = null,
  /**
   * Human-readable description (title)
   */
  var description: String? = null,
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
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Unique Identifier for the set of documents
   */
  var masterIdentifier: Identifier? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var recipient: List<Reference> = listOf(),
  var related: List<DocumentManifestRelated> = listOf(),
  /**
   * The source system/application/software
   */
  var source: String? = null,
  /**
   * current | superseded | entered-in-error
   */
  var status: String? = null,
  /**
   * The subject of the set of documents
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Kind of document set
   */
  var type: CodeableConcept? = null
) : DomainResource
