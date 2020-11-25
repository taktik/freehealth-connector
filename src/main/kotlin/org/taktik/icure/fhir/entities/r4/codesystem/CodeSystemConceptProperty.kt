//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.codesystem

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Property value for the concept
 *
 * A property value for this concept.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CodeSystemConceptProperty(
  /**
   * Reference to CodeSystem.property.code
   */
  var code: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Value of the property for this concept
   */
  var valueBoolean: Boolean? = null,
  /**
   * Value of the property for this concept
   */
  var valueCode: String? = null,
  /**
   * Value of the property for this concept
   */
  var valueCoding: Coding,
  /**
   * Value of the property for this concept
   */
  var valueDateTime: String? = null,
  /**
   * Value of the property for this concept
   */
  var valueDecimal: Float? = null,
  /**
   * Value of the property for this concept
   */
  var valueInteger: Int? = null,
  /**
   * Value of the property for this concept
   */
  var valueString: String? = null
) : BackboneElement
