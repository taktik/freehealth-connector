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
 * ValueSet details if this is coded
 *
 * Binds to a value set if this element is coded (code, Coding, CodeableConcept, Quantity), or the
 * data types (string, uri).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinitionBinding(
  /**
   * Human explanation of the value set
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * required | extensible | preferred | example
   */
  var strength: String? = null,
  /**
   * Source of value set
   */
  var valueSet: String? = null
) : Element
