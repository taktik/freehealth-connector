//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.examplescenario

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Each interaction or action
 *
 * Each interaction or action.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ExampleScenarioProcessStepOperation(
  /**
   * A comment to be inserted in the diagram
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Who starts the transaction
   */
  var initiator: String? = null,
  /**
   * Whether the initiator is deactivated right after the transaction
   */
  var initiatorActive: Boolean? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The human-friendly name of the interaction
   */
  var name: String? = null,
  /**
   * The sequential number of the interaction
   */
  var number: String? = null,
  /**
   * Who receives the transaction
   */
  var receiver: String? = null,
  /**
   * Whether the receiver is deactivated right after the transaction
   */
  var receiverActive: Boolean? = null,
  /**
   * Each resource instance used by the initiator
   */
  var request: ExampleScenarioInstanceContainedInstance? = null,
  /**
   * Each resource instance used by the responder
   */
  var response: ExampleScenarioInstanceContainedInstance? = null,
  /**
   * The type of operation - CRUD
   */
  var type: String? = null
) : BackboneElement
