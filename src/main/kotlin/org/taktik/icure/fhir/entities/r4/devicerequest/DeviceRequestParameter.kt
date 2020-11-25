//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.devicerequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * Device details
 *
 * Specific parameters for the ordered item.  For example, the prism value for lenses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DeviceRequestParameter(
  /**
   * Device detail
   */
  var code: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Value of detail
   */
  var valueBoolean: Boolean? = null,
  /**
   * Value of detail
   */
  var valueCodeableConcept: CodeableConcept? = null,
  /**
   * Value of detail
   */
  var valueQuantity: Quantity? = null,
  /**
   * Value of detail
   */
  var valueRange: Range? = null
) : BackboneElement
