//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.terminologycapabilities

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
 * A TerminologyCapabilities resource documents a set of capabilities (behaviors) of a FHIR
 * Terminology Server that may be used as a statement of actual server functionality or a statement of
 * required or desired server implementation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class TerminologyCapabilities(
  /**
   * Information about the [ConceptMap/$closure](conceptmap-operation-closure.html) operation
   */
  var closure: TerminologyCapabilitiesClosure? = null,
  /**
   * explicit | all
   */
  var codeSearch: String? = null,
  var codeSystem: List<TerminologyCapabilitiesCodeSystem> = listOf(),
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
   * Natural language description of the terminology capabilities
   */
  var description: String? = null,
  /**
   * Information about the [ValueSet/$expand](valueset-operation-expand.html) operation
   */
  var expansion: TerminologyCapabilitiesExpansion? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * If this describes a specific instance
   */
  var implementation: TerminologyCapabilitiesImplementation? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * instance | capability | requirements
   */
  var kind: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Whether lockedDate is supported
   */
  var lockedDate: Boolean? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this terminology capabilities (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this terminology capabilities is defined
   */
  var purpose: String? = null,
  /**
   * Software that is covered by this terminology capability statement
   */
  var software: TerminologyCapabilitiesSoftware? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this terminology capabilities (human friendly)
   */
  var title: String? = null,
  /**
   * Information about the [ConceptMap/$translate](conceptmap-operation-translate.html) operation
   */
  var translation: TerminologyCapabilitiesTranslation? = null,
  /**
   * Canonical identifier for this terminology capabilities, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Information about the [ValueSet/$validate-code](valueset-operation-validate-code.html)
   * operation
   */
  var validateCode: TerminologyCapabilitiesValidateCode? = null,
  /**
   * Business version of the terminology capabilities
   */
  var version: String? = null
) : DomainResource
