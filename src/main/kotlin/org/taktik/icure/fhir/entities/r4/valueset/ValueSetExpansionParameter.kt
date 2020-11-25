//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.valueset

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Parameter that controlled the expansion process
 *
 * A parameter that controlled the expansion process. These parameters may be used by users of
 * expanded value sets to check whether the expansion is suitable for a particular purpose, or to pick
 * the correct expansion.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ValueSetExpansionParameter(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name as assigned by the client or server
   */
  var name: String? = null,
  /**
   * Value of the named parameter
   */
  var valueBoolean: Boolean? = null,
  /**
   * Value of the named parameter
   */
  var valueCode: String? = null,
  /**
   * Value of the named parameter
   */
  var valueDateTime: String? = null,
  /**
   * Value of the named parameter
   */
  var valueDecimal: Float? = null,
  /**
   * Value of the named parameter
   */
  var valueInteger: Int? = null,
  /**
   * Value of the named parameter
   */
  var valueString: String? = null,
  /**
   * Value of the named parameter
   */
  var valueUri: String? = null
) : BackboneElement
