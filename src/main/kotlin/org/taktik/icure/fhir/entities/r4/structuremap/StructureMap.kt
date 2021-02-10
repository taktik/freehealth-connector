//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.structuremap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.annotation.JsonProperty
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
 * A Map of relationships between 2 structures that can be used to transform data
 *
 * A Map of relationships between 2 structures that can be used to transform data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class StructureMap(
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
   * Natural language description of the structure map
   */
  var description: String? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var group: List<StructureMapGroup> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  @JsonProperty("import")
  var import_fhir: List<String> = listOf(),
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
   * Name for this structure map (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this structure map is defined
   */
  var purpose: String? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  var structure: List<StructureMapStructure> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this structure map (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this structure map, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the structure map
   */
  var version: String? = null
) : DomainResource
