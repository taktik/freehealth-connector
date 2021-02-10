//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.examplescenario

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
 * Example of workflow instance
 *
 * Example of workflow instance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ExampleScenario(
  var actor: List<ExampleScenarioActor> = listOf(),
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
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var instance: List<ExampleScenarioInstance> = listOf(),
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
   * Name for this example scenario (computer friendly)
   */
  var name: String? = null,
  var process: List<ExampleScenarioProcess> = listOf(),
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * The purpose of the example, e.g. to illustrate a scenario
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
   * Canonical identifier for this example scenario, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the example scenario
   */
  var version: String? = null,
  var workflow: List<String> = listOf()
) : DomainResource
