//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.reference

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier

/**
 * A reference from one resource to another
 *
 * A reference from one resource to another.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Reference(
  /**
   * Text alternative for the resource
   */
  var display: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Logical reference, when literal reference is not known
   */
  var identifier: Identifier? = null,
  /**
   * Literal reference, Relative, internal or absolute URL
   */
  var reference: String? = null,
  /**
   * Type the reference refers to (e.g. "Patient")
   */
  var type: String? = null
) : Element
