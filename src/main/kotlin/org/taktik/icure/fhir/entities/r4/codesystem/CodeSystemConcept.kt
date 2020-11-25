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
 * Concepts in the code system
 *
 * Concepts that are in the code system. The concept definitions are inherently hierarchical, but
 * the definitions must be consulted to determine what the meanings of the hierarchical relationships
 * are.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CodeSystemConcept(
  /**
   * Code that identifies concept
   */
  var code: String? = null,
  var concept: List<CodeSystemConcept> = listOf(),
  /**
   * Formal definition
   */
  var definition: String? = null,
  var designation: List<CodeSystemConceptDesignation> = listOf(),
  /**
   * Text to display to the user
   */
  var display: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var property: List<CodeSystemConceptProperty> = listOf()
) : BackboneElement
