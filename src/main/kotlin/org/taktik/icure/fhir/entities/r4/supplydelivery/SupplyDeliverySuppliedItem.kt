//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.supplydelivery

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The item that is delivered or supplied
 *
 * The item that is being delivered or has been supplied.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SupplyDeliverySuppliedItem(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Medication, Substance, or Device supplied
   */
  var itemCodeableConcept: CodeableConcept? = null,
  /**
   * Medication, Substance, or Device supplied
   */
  var itemReference: Reference? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Amount dispensed
   */
  var quantity: Quantity? = null
) : BackboneElement
