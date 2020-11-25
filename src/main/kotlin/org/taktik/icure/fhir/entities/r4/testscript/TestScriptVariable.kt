//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testscript

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Placeholder for evaluated elements
 *
 * Variable is set based either on element value in response body or on header field value in the
 * response headers.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TestScriptVariable(
  /**
   * Default, hard-coded, or user-defined value for this variable
   */
  var defaultValue: String? = null,
  /**
   * Natural language description of the variable
   */
  var description: String? = null,
  /**
   * The FHIRPath expression against the fixture body
   */
  var expression: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * HTTP header field name for source
   */
  var headerField: String? = null,
  /**
   * Hint help text for default value to enter
   */
  var hint: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Descriptive name for this variable
   */
  var name: String? = null,
  /**
   * XPath or JSONPath against the fixture body
   */
  var path: String? = null,
  /**
   * Fixture Id of source expression or headerField within this variable
   */
  var sourceId: String? = null
) : BackboneElement
