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
 * Element values that are used to distinguish the slices
 *
 * Designates which child elements are used to discriminate between the slices when processing an
 * instance. If one or more discriminators are provided, the value of the child elements in the
 * instance data SHALL completely distinguish which slice the element in the resource matches based on
 * the allowed values for those elements in each of the slices.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinitionSlicingDiscriminator(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Path to element value
   */
  var path: String? = null,
  /**
   * value | exists | pattern | type | profile
   */
  var type: String? = null
) : Element
