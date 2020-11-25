//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductmanufactured

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.prodcharacteristic.ProdCharacteristic
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The manufactured item as contained in the packaged medicinal product
 *
 * The manufactured item as contained in the packaged medicinal product.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicinalProductManufactured(
  override var contained: List<Resource> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var ingredient: List<Reference> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Dose form as manufactured and before any transformation into the pharmaceutical product
   */
  var manufacturedDoseForm: CodeableConcept,
  var manufacturer: List<Reference> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var otherCharacteristics: List<CodeableConcept> = listOf(),
  /**
   * Dimensions, color etc.
   */
  var physicalCharacteristics: ProdCharacteristic? = null,
  /**
   * The quantity or "count number" of the manufactured item
   */
  var quantity: Quantity,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * The “real world” units in which the quantity of the manufactured item is described
   */
  var unitOfPresentation: CodeableConcept? = null
) : DomainResource
