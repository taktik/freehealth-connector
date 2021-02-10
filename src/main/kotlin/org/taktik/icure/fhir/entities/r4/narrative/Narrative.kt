//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.narrative

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Human-readable summary of the resource (essential clinical and business information)
 *
 * A human-readable summary of the resource conveying the essential clinical and business
 * information for the resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Narrative(
  /**
   * Limited xhtml content
   */
  var div: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * generated | extensions | additional | empty
   */
  var status: String? = null
) : Element
