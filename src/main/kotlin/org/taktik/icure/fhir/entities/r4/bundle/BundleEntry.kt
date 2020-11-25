//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.bundle

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Entry in the bundle - will have a resource or information
 *
 * An entry in a bundle resource - will either contain a resource or information about a resource
 * (transactions and history only).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class BundleEntry(
  override var extension: List<Extension> = listOf(),
  /**
   * URI for resource (Absolute URL server address or URI for UUID/OID)
   */
  var fullUrl: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var link: List<BundleLink> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Additional execution information (transaction/batch/history)
   */
  var request: BundleEntryRequest? = null,
  /**
   * A resource in the bundle
   */
  var resource: Resource? = null,
  /**
   * Results of execution (transaction/batch/history)
   */
  var response: BundleEntryResponse? = null,
  /**
   * Search related information
   */
  var search: BundleEntrySearch? = null
) : BackboneElement
