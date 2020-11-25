//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.elementdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Base definition information for tools
 *
 * Information about the base definition of the element, provided to make it unnecessary for tools
 * to trace the deviation of the element through the derived and related profiles. When the element
 * definition is not the original definition of an element - i.g. either in a constraint on another
 * type, or for elements from a super type in a snap shot - then the information in provided in the
 * element definition may be different to the base definition. On the original definition of the
 * element, it will be same.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinitionBase(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Max cardinality of the base element
   */
  var max: String? = null,
  /**
   * Min cardinality of the base element
   */
  var min: Int? = null,
  /**
   * Path that identifies the base element
   */
  var path: String? = null
) : Element
