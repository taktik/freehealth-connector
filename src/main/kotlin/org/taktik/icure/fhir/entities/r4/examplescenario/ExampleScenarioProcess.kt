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
 * Each major process - a group of operations
 *
 * Each major process - a group of operations.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ExampleScenarioProcess(
  /**
   * A longer description of the group of operations
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Description of final status after the process ends
   */
  var postConditions: String? = null,
  /**
   * Description of initial status before the process starts
   */
  var preConditions: String? = null,
  var step: List<ExampleScenarioProcessStep> = listOf(),
  /**
   * The diagram title of the group of operations
   */
  var title: String? = null
) : BackboneElement
