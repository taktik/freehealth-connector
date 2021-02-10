//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.valueset

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
 * A set of codes drawn from one or more code systems
 *
 * A ValueSet resource instance specifies a set of codes drawn from one or more code systems,
 * intended for use in a particular context. Value sets link between [CodeSystem](codesystem.html)
 * definitions and their use in [coded elements](terminologies.html).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ValueSet(
  /**
   * Content logical definition of the value set (CLD)
   */
  var compose: ValueSetCompose? = null,
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
   * Natural language description of the value set
   */
  var description: String? = null,
  /**
   * Used when the value set is "expanded"
   */
  var expansion: ValueSetExpansion? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * Indicates whether or not any change to the content logical definition may occur
   */
  var immutable: Boolean? = null,
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
   * Name for this value set (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this value set is defined
   */
  var purpose: String? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this value set (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this value set, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the value set
   */
  var version: String? = null
) : DomainResource
