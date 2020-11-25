//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.codesystem

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.Int
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
 * Declares the existence of and describes a code system or code system supplement
 *
 * The CodeSystem resource is used to declare the existence of and describe a code system or code
 * system supplement and its key properties, and optionally define a part or all of its content.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class CodeSystem(
  /**
   * If code comparison is case sensitive
   */
  var caseSensitive: Boolean? = null,
  /**
   * If code system defines a compositional grammar
   */
  var compositional: Boolean? = null,
  var concept: List<CodeSystemConcept> = listOf(),
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * not-present | example | fragment | complete | supplement
   */
  var content: String? = null,
  /**
   * Use and/or publishing restrictions
   */
  var copyright: String? = null,
  /**
   * Total concepts in the code system
   */
  var count: Int? = null,
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * Natural language description of the code system
   */
  var description: String? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var filter: List<CodeSystemFilter> = listOf(),
  /**
   * grouped-by | is-a | part-of | classified-with
   */
  var hierarchyMeaning: String? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
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
   * Name for this code system (computer friendly)
   */
  var name: String? = null,
  var property: List<CodeSystemProperty> = listOf(),
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this code system is defined
   */
  var purpose: String? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Canonical URL of Code System this adds designations and properties to
   */
  var supplements: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this code system (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this code system, represented as a URI (globally unique)
   * (Coding.system)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Canonical reference to the value set with entire code system
   */
  var valueSet: String? = null,
  /**
   * Business version of the code system (Coding.version)
   */
  var version: String? = null,
  /**
   * If definitions are not stable
   */
  var versionNeeded: Boolean? = null
) : DomainResource
