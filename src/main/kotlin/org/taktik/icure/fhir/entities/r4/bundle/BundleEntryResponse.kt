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
 * Results of execution (transaction/batch/history)
 *
 * Indicates the results of processing the corresponding 'request' entry in the batch or transaction
 * being responded to or what the results of an operation where when returning history.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class BundleEntryResponse(
  /**
   * The Etag for the resource (if relevant)
   */
  var etag: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Server's date time modified
   */
  var lastModified: String? = null,
  /**
   * The location (if the operation returns a location)
   */
  var location: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * OperationOutcome with hints and warnings (for batch/transaction)
   */
  var outcome: Resource? = null,
  /**
   * Status response code (text optional)
   */
  var status: String? = null
) : BackboneElement
