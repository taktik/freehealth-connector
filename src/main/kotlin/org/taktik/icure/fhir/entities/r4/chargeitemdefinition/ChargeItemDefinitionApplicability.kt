//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.chargeitemdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Whether or not the billing code is applicable
 *
 * Expressions that describe applicability criteria for the billing code.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ChargeItemDefinitionApplicability(
  /**
   * Natural language description of the condition
   */
  var description: String? = null,
  /**
   * Boolean-valued expression
   */
  var expression: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Language of the expression
   */
  var language: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
