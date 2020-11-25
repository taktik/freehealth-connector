//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.structuredefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * External specification that the content is mapped to
 *
 * An external specification that the content is mapped to.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureDefinitionMapping(
  /**
   * Versions, Issues, Scope limitations etc.
   */
  var comment: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Internal id when this mapping is used
   */
  var identity: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Names what this mapping refers to
   */
  var name: String? = null,
  /**
   * Identifies what this mapping refers to
   */
  var uri: String? = null
) : BackboneElement
