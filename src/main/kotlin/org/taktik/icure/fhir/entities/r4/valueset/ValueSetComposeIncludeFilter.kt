//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.valueset

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Select codes/concepts by their properties (including relationships)
 *
 * Select concepts by specify a matching criterion based on the properties (including relationships)
 * defined by the system, or on filters defined by the system. If multiple filters are specified, they
 * SHALL all be true.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ValueSetComposeIncludeFilter(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * = | is-a | descendent-of | is-not-a | regex | in | not-in | generalizes | exists
   */
  var op: String? = null,
  /**
   * A property/filter defined by the code system
   */
  var property: String? = null,
  /**
   * Code from the system, or regex criteria, or boolean value for exists
   */
  var value: String? = null
) : BackboneElement
