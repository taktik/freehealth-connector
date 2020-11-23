//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.capabilitystatement

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
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * A statement of system capabilities
 *
 * A Capability Statement documents a set of capabilities (behaviors) of a FHIR Server for a
 * particular version of FHIR that may be used as a statement of actual server functionality or a
 * statement of required or desired server implementation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class CapabilityStatement(
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
   * Natural language description of the capability statement
   */
  var description: String? = null,
  var document: List<CapabilityStatementDocument> = listOf(),
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * FHIR Version the system supports
   */
  var fhirVersion: String? = null,
  var format: List<String> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * If this describes a specific instance
   */
  var implementation: CapabilityStatementImplementation? = null,
  var implementationGuide: List<String> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var imports: List<String> = listOf(),
  var instantiates: List<String> = listOf(),
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * instance | capability | requirements
   */
  var kind: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var messaging: List<CapabilityStatementMessaging> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this capability statement (computer friendly)
   */
  var name: String? = null,
  var patchFormat: List<String> = listOf(),
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this capability statement is defined
   */
  var purpose: String? = null,
  var rest: List<CapabilityStatementRest> = listOf(),
  /**
   * Software that is covered by this capability statement
   */
  var software: CapabilityStatementSoftware? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this capability statement (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this capability statement, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the capability statement
   */
  var version: String? = null
) : DomainResource
