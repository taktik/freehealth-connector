//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.activitydefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.expression.Expression
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Dynamic aspects of the definition
 *
 * Dynamic values that will be evaluated to produce values for elements of the resulting resource.
 * For example, if the dosage of a medication must be computed based on the patient's weight, a dynamic
 * value would be used to specify an expression that calculated the weight, and the path on the request
 * resource that would contain the result.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ActivityDefinitionDynamicValue(
  /**
   * An expression that provides the dynamic value for the customization
   */
  var expression: Expression,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The path to the element to be set dynamically
   */
  var path: String? = null
) : BackboneElement
