//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testscript

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The assertion to perform
 *
 * Evaluates the results of previous operations to determine if the server under test behaves
 * appropriately.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TestScriptSetupActionAssert(
  /**
   * The FHIRPath expression to evaluate against the source fixture
   */
  var compareToSourceExpression: String? = null,
  /**
   * Id of the source fixture to be evaluated
   */
  var compareToSourceId: String? = null,
  /**
   * XPath or JSONPath expression to evaluate against the source fixture
   */
  var compareToSourcePath: String? = null,
  /**
   * Mime type to compare against the 'Content-Type' header
   */
  var contentType: String? = null,
  /**
   * Tracking/reporting assertion description
   */
  var description: String? = null,
  /**
   * response | request
   */
  var direction: String? = null,
  /**
   * The FHIRPath expression to be evaluated
   */
  var expression: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * HTTP header field name
   */
  var headerField: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Tracking/logging assertion label
   */
  var label: String? = null,
  /**
   * Fixture Id of minimum content resource
   */
  var minimumId: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Perform validation on navigation links?
   */
  var navigationLinks: Boolean? = null,
  /**
   * equals | notEquals | in | notIn | greaterThan | lessThan | empty | notEmpty | contains |
   * notContains | eval
   */
  var operator: String? = null,
  /**
   * XPath or JSONPath expression
   */
  var path: String? = null,
  /**
   * delete | get | options | patch | post | put | head
   */
  var requestMethod: String? = null,
  /**
   * Request URL comparison value
   */
  var requestURL: String? = null,
  /**
   * Resource type
   */
  var resource: String? = null,
  /**
   * okay | created | noContent | notModified | bad | forbidden | notFound | methodNotAllowed |
   * conflict | gone | preconditionFailed | unprocessable
   */
  var response: String? = null,
  /**
   * HTTP response code to test
   */
  var responseCode: String? = null,
  /**
   * Fixture Id of source expression or headerField
   */
  var sourceId: String? = null,
  /**
   * Profile Id of validation profile reference
   */
  var validateProfileId: String? = null,
  /**
   * The value to compare to
   */
  var value: String? = null,
  /**
   * Will this assert produce a warning only on error?
   */
  var warningOnly: Boolean? = null
) : BackboneElement
