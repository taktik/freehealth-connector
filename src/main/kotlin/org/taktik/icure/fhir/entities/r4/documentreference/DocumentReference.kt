//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.documentreference

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
 * A reference to a document
 *
 * A reference to a document of any kind for any purpose. Provides metadata about the document so
 * that the document can be discovered and managed. The scope of a document is any seralized object
 * with a mime-type, so includes formal patient centric documents (CDA), cliical notes, scanned paper,
 * and non-patient specific documents like policy text.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class DocumentReference(
  /**
   * Who/what authenticated the document
   */
  var authenticator: Reference? = null,
  var author: List<Reference> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  var content: List<DocumentReferenceContent> = listOf(),
  /**
   * Clinical context of document
   */
  var context: DocumentReferenceContext? = null,
  /**
   * Organization which maintains the document
   */
  var custodian: Reference? = null,
  /**
   * When this document reference was created
   */
  var date: String? = null,
  /**
   * Human-readable description
   */
  var description: String? = null,
  /**
   * preliminary | final | amended | entered-in-error
   */
  var docStatus: String? = null,
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
   * Master Version Specific Identifier
   */
  var masterIdentifier: Identifier? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var relatesTo: List<DocumentReferenceRelatesTo> = listOf(),
  var securityLabel: List<CodeableConcept> = listOf(),
  /**
   * current | superseded | entered-in-error
   */
  var status: String? = null,
  /**
   * Who/what is the subject of the document
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Kind of document (LOINC if possible)
   */
  var type: CodeableConcept? = null
) : DomainResource
