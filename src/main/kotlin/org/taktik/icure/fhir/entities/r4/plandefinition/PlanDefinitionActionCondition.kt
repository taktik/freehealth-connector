//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.plandefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.expression.Expression
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Whether or not the action is applicable
 *
 * An expression that describes applicability criteria or start/stop conditions for the action.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PlanDefinitionActionCondition(
  /**
   * Boolean-valued expression
   */
  var expression: Expression? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * applicability | start | stop
   */
  var kind: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
