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
 * What to do when there is no mapping for the source concept
 *
 * What to do when there is no mapping for the source concept. "Unmapped" does not include codes
 * that are unmatched, and the unmapped element is ignored in a code is specified to have equivalence =
 * unmatched.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ConceptMapGroupUnmapped(
  /**
   * Fixed code when mode = fixed
   */
  var code: String? = null,
  /**
   * Display for the code
   */
  var display: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * provided | fixed | other-map
   */
  var mode: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * canonical reference to an additional ConceptMap to use for mapping if the source concept is
   * unmapped
   */
  var url: String? = null
) : BackboneElement
