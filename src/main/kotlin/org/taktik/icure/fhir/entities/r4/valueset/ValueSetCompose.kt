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
 * Content logical definition of the value set (CLD)
 *
 * A set of criteria that define the contents of the value set by including or excluding codes
 * selected from the specified code system(s) that the value set draws from. This is also known as the
 * Content Logical Definition (CLD).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ValueSetCompose(
  var exclude: List<ValueSetComposeInclude> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Whether inactive codes are in the value set
   */
  var inactive: Boolean? = null,
  var include: List<ValueSetComposeInclude> = listOf(),
  /**
   * Fixed date for references with no specified version (transitive)
   */
  var lockedDate: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
