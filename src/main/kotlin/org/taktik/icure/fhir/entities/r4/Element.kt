//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4

import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Base for all elements
 *
 * Base definition for all elements in a resource.
 */
interface Element {
  var extension: List<Extension>

  /**
   * Unique id for inter-element referencing
   */
  var id: String?
}
