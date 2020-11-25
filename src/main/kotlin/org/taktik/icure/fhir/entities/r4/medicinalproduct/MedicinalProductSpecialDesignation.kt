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
 * Indicates if the medicinal product has an orphan designation for the treatment of a rare disease
 *
 * Indicates if the medicinal product has an orphan designation for the treatment of a rare disease.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductSpecialDesignation(
  /**
   * Date when the designation was granted
   */
  var date: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * Condition for which the medicinal use applies
   */
  var indicationCodeableConcept: CodeableConcept? = null,
  /**
   * Condition for which the medicinal use applies
   */
  var indicationReference: Reference? = null,
  /**
   * The intended use of the product, e.g. prevention, treatment
   */
  var intendedUse: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Animal species for which this applies
   */
  var species: CodeableConcept? = null,
  /**
   * For example granted, pending, expired or withdrawn
   */
  var status: CodeableConcept? = null,
  /**
   * The type of special designation, e.g. orphan drug, minor use
   */
  var type: CodeableConcept? = null
) : BackboneElement
