//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.codesystem

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Filter that can be used in a value set
 *
 * A filter that can be used in a value set compose statement when selecting concepts using a
 * filter.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CodeSystemFilter(
  /**
   * Code that identifies the filter
   */
  var code: String? = null,
  /**
   * How or why the filter is used
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var operator: List<String> = listOf(),
  /**
   * What to use for the value
   */
  var value: String? = null
) : BackboneElement
