//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.bundle

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Additional execution information (transaction/batch/history)
 *
 * Additional information about how this entry should be processed as part of a transaction or
 * batch.  For history, it shows how the entry was processed to create the version contained in the
 * entry.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class BundleEntryRequest(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * For managing update contention
   */
  var ifMatch: String? = null,
  /**
   * For managing cache currency
   */
  var ifModifiedSince: String? = null,
  /**
   * For conditional creates
   */
  var ifNoneExist: String? = null,
  /**
   * For managing cache currency
   */
  var ifNoneMatch: String? = null,
  /**
   * GET | HEAD | POST | PUT | DELETE | PATCH
   */
  var method: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * URL for HTTP equivalent of this entry
   */
  var url: String? = null
) : BackboneElement
