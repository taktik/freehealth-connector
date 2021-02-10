//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductpackaged

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.prodcharacteristic.ProdCharacteristic
import org.taktik.icure.fhir.entities.r4.productshelflife.ProductShelfLife
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A packaging item, as a contained for medicine, possibly with other packaging items within
 *
 * A packaging item, as a contained for medicine, possibly with other packaging items within.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductPackagedPackageItem(
  var alternateMaterial: List<CodeableConcept> = listOf(),
  var device: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  var manufacturedItem: List<Reference> = listOf(),
  var manufacturer: List<Reference> = listOf(),
  var material: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var otherCharacteristics: List<CodeableConcept> = listOf(),
  var packageItem: List<MedicinalProductPackagedPackageItem> = listOf(),
  /**
   * Dimensions, color etc.
   */
  var physicalCharacteristics: ProdCharacteristic? = null,
  /**
   * The quantity of this package in the medicinal product, at the current level of packaging. The
   * outermost is always 1
   */
  var quantity: Quantity,
  var shelfLifeStorage: List<ProductShelfLife> = listOf(),
  /**
   * The physical type of the container of the medicine
   */
  var type: CodeableConcept
) : BackboneElement
