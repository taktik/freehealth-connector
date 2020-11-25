//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.operationdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Parameters for the operation/query
 *
 * The parameters for the operation/query.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class OperationDefinitionParameter(
  /**
   * ValueSet details if this is coded
   */
  var binding: OperationDefinitionParameterBinding? = null,
  /**
   * Description of meaning/use
   */
  var documentation: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Maximum Cardinality (a number or *)
   */
  var max: String? = null,
  /**
   * Minimum Cardinality
   */
  var min: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name in Parameters.parameter.name or in URL
   */
  var name: String? = null,
  var part: List<OperationDefinitionParameter> = listOf(),
  var referencedFrom: List<OperationDefinitionParameterReferencedFrom> = listOf(),
  /**
   * number | date | string | token | reference | composite | quantity | uri | special
   */
  var searchType: String? = null,
  var targetProfile: List<String> = listOf(),
  /**
   * What type this parameter has
   */
  var type: String? = null,
  /**
   * in | out
   */
  var use: String? = null
) : BackboneElement
