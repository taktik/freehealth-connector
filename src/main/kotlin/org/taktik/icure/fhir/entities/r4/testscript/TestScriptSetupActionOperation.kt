//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testscript

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The setup operation to perform
 *
 * The operation to perform.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TestScriptSetupActionOperation(
  /**
   * Mime type to accept in the payload of the response, with charset etc.
   */
  var accept: String? = null,
  /**
   * Mime type of the request payload contents, with charset etc.
   */
  var contentType: String? = null,
  /**
   * Tracking/reporting operation description
   */
  var description: String? = null,
  /**
   * Server responding to the request
   */
  var destination: Int? = null,
  /**
   * Whether or not to send the request url in encoded format
   */
  var encodeRequestUrl: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Tracking/logging operation label
   */
  var label: String? = null,
  /**
   * delete | get | options | patch | post | put | head
   */
  var method: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Server initiating the request
   */
  var origin: Int? = null,
  /**
   * Explicitly defined path parameters
   */
  var params: String? = null,
  var requestHeader: List<TestScriptSetupActionOperationRequestHeader> = listOf(),
  /**
   * Fixture Id of mapped request
   */
  var requestId: String? = null,
  /**
   * Resource type
   */
  var resource: String? = null,
  /**
   * Fixture Id of mapped response
   */
  var responseId: String? = null,
  /**
   * Fixture Id of body for PUT and POST requests
   */
  var sourceId: String? = null,
  /**
   * Id of fixture used for extracting the [id],  [type], and [vid] for GET requests
   */
  var targetId: String? = null,
  /**
   * The operation code type that will be executed
   */
  var type: Coding? = null,
  /**
   * Request URL
   */
  var url: String? = null
) : BackboneElement
