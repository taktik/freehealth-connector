//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.structuredefinition

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
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * Structural Definition
 *
 * A definition of a FHIR structure. This resource is used to describe the underlying resources,
 * data types defined in FHIR, and also for describing extensions and constraints on resources and data
 * types.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class StructureDefinition(
  /**
   * Whether the structure is abstract
   */
  var abstract: Boolean? = null,
  /**
   * Definition that this type is constrained/specialized from
   */
  var baseDefinition: String? = null,
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  var context: List<StructureDefinitionContext> = listOf(),
  var contextInvariant: List<String> = listOf(),
  /**
   * Use and/or publishing restrictions
   */
  var copyright: String? = null,
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * specialization | constraint - How relates to base definition
   */
  var derivation: String? = null,
  /**
   * Natural language description of the structure definition
   */
  var description: String? = null,
  /**
   * Differential view of the structure
   */
  var differential: StructureDefinitionDifferential? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * FHIR Version this StructureDefinition targets
   */
  var fhirVersion: String? = null,
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
  var keyword: List<Coding> = listOf(),
  /**
   * primitive-type | complex-type | resource | logical
   */
  var kind: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var mapping: List<StructureDefinitionMapping> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this structure definition (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this structure definition is defined
   */
  var purpose: String? = null,
  /**
   * Snapshot view of the structure
   */
  var snapshot: StructureDefinitionSnapshot? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this structure definition (human friendly)
   */
  var title: String? = null,
  /**
   * Type defined or constrained by this structure
   */
  var type: String? = null,
  /**
   * Canonical identifier for this structure definition, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the structure definition
   */
  var version: String? = null
) : DomainResource
