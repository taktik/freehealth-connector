//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproduct

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * An operation applied to the product, for manufacturing or adminsitrative purpose
 *
 * An operation applied to the product, for manufacturing or adminsitrative purpose.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductManufacturingBusinessOperation(
  /**
   * Regulatory authorization reference number
   */
  var authorisationReferenceNumber: Identifier? = null,
  /**
   * To indicate if this proces is commercially confidential
   */
  var confidentialityIndicator: CodeableConcept? = null,
  /**
   * Regulatory authorization date
   */
  var effectiveDate: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var manufacturer: List<Reference> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The type of manufacturing operation
   */
  var operationType: CodeableConcept? = null,
  /**
   * A regulator which oversees the operation
   */
  var regulator: Reference? = null
) : BackboneElement
