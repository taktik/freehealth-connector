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
 * Map element to another set of definitions
 *
 * Identifies a concept from an external specification that roughly corresponds to this element.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinitionMapping(
  /**
   * Comments about the mapping or its use
   */
  var comment: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Reference to mapping declaration
   */
  var identity: String? = null,
  /**
   * Computable language of mapping
   */
  var language: String? = null,
  /**
   * Details of the mapping
   */
  var map: String? = null
) : Element
