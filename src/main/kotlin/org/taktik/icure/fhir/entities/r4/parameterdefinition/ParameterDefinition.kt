//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.parameterdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Definition of a parameter to a module
 *
 * The parameters to the module. This collection specifies both the input and output parameters.
 * Input parameters are provided by the caller as part of the $evaluate operation. Output parameters
 * are included in the GuidanceResponse.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ParameterDefinition(
  /**
   * A brief description of the parameter
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Maximum cardinality (a number of *)
   */
  var max: String? = null,
  /**
   * Minimum cardinality
   */
  var min: Int? = null,
  /**
   * Name used to access the parameter value
   */
  var name: String? = null,
  /**
   * What profile the value is expected to be
   */
  var profile: String? = null,
  /**
   * What type of value
   */
  var type: String? = null,
  /**
   * in | out
   */
  var use: String? = null
) : Element
