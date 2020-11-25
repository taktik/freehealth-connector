//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.examplescenario

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Each resource and each version that is present in the workflow
 *
 * Each resource and each version that is present in the workflow.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ExampleScenarioInstance(
  var containedInstance: List<ExampleScenarioInstanceContainedInstance> = listOf(),
  /**
   * Human-friendly description of the resource instance
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * A short name for the resource instance
   */
  var name: String? = null,
  /**
   * The id of the resource for referencing
   */
  var resourceId: String? = null,
  /**
   * The type of the resource
   */
  var resourceType: String? = null,
  var version: List<ExampleScenarioInstanceVersion> = listOf()
) : BackboneElement
