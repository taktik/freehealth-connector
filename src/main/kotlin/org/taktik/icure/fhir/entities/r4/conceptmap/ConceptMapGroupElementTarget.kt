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
 * Concept in target system for element
 *
 * A concept from the target value set that this concept maps to.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ConceptMapGroupElementTarget(
  /**
   * Code that identifies the target element
   */
  var code: String? = null,
  /**
   * Description of status/issues in mapping
   */
  var comment: String? = null,
  var dependsOn: List<ConceptMapGroupElementTargetDependsOn> = listOf(),
  /**
   * Display for the code
   */
  var display: String? = null,
  /**
   * relatedto | equivalent | equal | wider | subsumes | narrower | specializes | inexact |
   * unmatched | disjoint
   */
  var equivalence: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var product: List<ConceptMapGroupElementTargetDependsOn> = listOf()
) : BackboneElement
