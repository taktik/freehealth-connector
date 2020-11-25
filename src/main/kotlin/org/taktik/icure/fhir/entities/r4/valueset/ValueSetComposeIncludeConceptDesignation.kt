//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.valueset

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Additional representations for this concept
 *
 * Additional representations for this concept when used in this value set - other languages,
 * aliases, specialized purposes, used for particular purposes, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ValueSetComposeIncludeConceptDesignation(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Human language of the designation
   */
  var language: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Types of uses of designations
   */
  var use: Coding? = null,
  /**
   * The text value for this designation
   */
  var value: String? = null
) : BackboneElement
