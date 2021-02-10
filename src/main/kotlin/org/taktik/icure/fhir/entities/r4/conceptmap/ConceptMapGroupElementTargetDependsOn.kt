//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.conceptmap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Other elements required for this mapping (from context)
 *
 * A set of additional dependencies for this mapping to hold. This mapping is only applicable if the
 * specified element can be resolved, and it has the specified value.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ConceptMapGroupElementTargetDependsOn(
  /**
   * Display for the code (if value is a code)
   */
  var display: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Reference to property mapping depends on
   */
  var property: String? = null,
  /**
   * Code System (if necessary)
   */
  var system: String? = null,
  /**
   * Value of the referenced element
   */
  var value: String? = null
) : BackboneElement
