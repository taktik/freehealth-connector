//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Metadata about a resource
 *
 * The metadata about a resource. This is content in the resource that is maintained by the
 * infrastructure. Changes to the content might not always be associated with version changes to the
 * resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Meta(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * When the resource version last changed
   */
  var lastUpdated: String? = null,
  var profile: List<String> = listOf(),
  var security: List<Coding> = listOf(),
  /**
   * Identifies where the resource comes from
   */
  var source: String? = null,
  var tag: List<Coding> = listOf(),
  /**
   * Version specific identifier
   */
  var versionId: String? = null
) : Element
