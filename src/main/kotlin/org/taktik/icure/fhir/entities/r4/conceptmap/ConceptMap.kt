//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.conceptmap

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
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * A map from one set of concepts to one or more other concepts
 *
 * A statement of relationships from one set of concepts to one or more other concepts - either
 * concepts in code systems, or data element/data element concepts, or classes in @JsonDeserialize(using= JsonDeserializer.None::class)
class models.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ConceptMap(
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Use and/or publishing restrictions
   */
  var copyright: String? = null,
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * Natural language description of the concept map
   */
  var description: String? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var group: List<ConceptMapGroup> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Additional identifier for the concept map
   */
  var identifier: Identifier? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
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
   * Name for this concept map (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this concept map is defined
   */
  var purpose: String? = null,
  /**
   * The source value set that contains the concepts that are being mapped
   */
  var sourceCanonical: String? = null,
  /**
   * The source value set that contains the concepts that are being mapped
   */
  var sourceUri: String? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * The target value set which provides context for the mappings
   */
  var targetCanonical: String? = null,
  /**
   * The target value set which provides context for the mappings
   */
  var targetUri: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this concept map (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this concept map, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the concept map
   */
  var version: String? = null
) : DomainResource
