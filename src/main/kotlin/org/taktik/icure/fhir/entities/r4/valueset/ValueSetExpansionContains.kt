//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.valueset

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Codes in the value set
 *
 * The codes that are contained in the value set expansion.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ValueSetExpansionContains(
  /**
   * If user cannot select this entry
   */
  var abstract: Boolean? = null,
  /**
   * Code - if blank, this is not a selectable code
   */
  var code: String? = null,
  var contains: List<ValueSetExpansionContains> = listOf(),
  var designation: List<ValueSetComposeIncludeConceptDesignation> = listOf(),
  /**
   * User display for the concept
   */
  var display: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * If concept is inactive in the code system
   */
  var inactive: Boolean? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * System value for the code
   */
  var system: String? = null,
  /**
   * Version in which this code/display is defined
   */
  var version: String? = null
) : BackboneElement
