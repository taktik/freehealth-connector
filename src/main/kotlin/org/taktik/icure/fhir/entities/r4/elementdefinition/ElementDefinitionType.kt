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
 * Data type and Profile for this element
 *
 * The data type or resource that the value of this element is permitted to be.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinitionType(
  var aggregation: List<String> = listOf(),
  /**
   * Data type or Resource (reference to definition)
   */
  var code: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var profile: List<String> = listOf(),
  var targetProfile: List<String> = listOf(),
  /**
   * either | independent | specific
   */
  var versioning: String? = null
) : Element
