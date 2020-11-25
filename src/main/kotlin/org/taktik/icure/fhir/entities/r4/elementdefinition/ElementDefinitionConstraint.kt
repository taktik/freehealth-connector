//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.elementdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Condition that must evaluate to true
 *
 * Formal constraints such as co-occurrence and other constraints that can be computationally
 * evaluated within the context of the instance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinitionConstraint(
  /**
   * FHIRPath expression of constraint
   */
  var expression: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Human description of constraint
   */
  var human: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Target of 'condition' reference above
   */
  var key: String? = null,
  /**
   * Why this constraint is necessary or appropriate
   */
  var requirements: String? = null,
  /**
   * error | warning
   */
  var severity: String? = null,
  /**
   * Reference to original source of constraint
   */
  var source: String? = null,
  /**
   * XPath expression of constraint
   */
  var xpath: String? = null
) : Element
