//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.operationdefinition

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
 * Definition of an operation or a named query
 *
 * A formal computable definition of an operation (on the RESTful interface) or a named query (using
 * the search interaction).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class OperationDefinition(
  /**
   * Whether content is changed by the operation
   */
  var affectsState: Boolean? = null,
  /**
   * Marks this as a profile of the base
   */
  var base: String? = null,
  /**
   * Name used to invoke the operation
   */
  var code: String? = null,
  /**
   * Additional information about use
   */
  var comment: String? = null,
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * Natural language description of the operation definition
   */
  var description: String? = null,
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
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Validation information for in parameters
   */
  var inputProfile: String? = null,
  /**
   * Invoke on an instance?
   */
  var instance: Boolean? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * operation | query
   */
  var kind: String? = null,
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
   * Name for this operation definition (computer friendly)
   */
  var name: String? = null,
  /**
   * Validation information for out parameters
   */
  var outputProfile: String? = null,
  var overload: List<OperationDefinitionOverload> = listOf(),
  var parameter: List<OperationDefinitionParameter> = listOf(),
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this operation definition is defined
   */
  var purpose: String? = null,
  var resource: List<String> = listOf(),
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Invoke at the system level?
   */
  var system: Boolean? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this operation definition (human friendly)
   */
  var title: String? = null,
  /**
   * Invoke at the type level?
   */
  var type: Boolean? = null,
  /**
   * Canonical identifier for this operation definition, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the operation definition
   */
  var version: String? = null
) : DomainResource
