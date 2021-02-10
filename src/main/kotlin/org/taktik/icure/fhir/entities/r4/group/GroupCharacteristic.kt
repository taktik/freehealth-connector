//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.group

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Include / Exclude group members by Trait
 *
 * Identifies traits whose presence r absence is shared by members of the group.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class GroupCharacteristic(
  /**
   * Kind of characteristic
   */
  var code: CodeableConcept,
  /**
   * Group includes or excludes
   */
  var exclude: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Period over which characteristic is tested
   */
  var period: Period? = null,
  /**
   * Value held by characteristic
   */
  var valueBoolean: Boolean? = null,
  /**
   * Value held by characteristic
   */
  var valueCodeableConcept: CodeableConcept,
  /**
   * Value held by characteristic
   */
  var valueQuantity: Quantity,
  /**
   * Value held by characteristic
   */
  var valueRange: Range,
  /**
   * Value held by characteristic
   */
  var valueReference: Reference
) : BackboneElement
