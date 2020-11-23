//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancereferenceinformation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Todo
 *
 * Todo.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceReferenceInformationTarget(
  /**
   * Todo
   */
  var amountQuantity: Quantity? = null,
  /**
   * Todo
   */
  var amountRange: Range? = null,
  /**
   * Todo
   */
  var amountString: String? = null,
  /**
   * Todo
   */
  var amountType: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Todo
   */
  var interaction: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Todo
   */
  var organism: CodeableConcept? = null,
  /**
   * Todo
   */
  var organismType: CodeableConcept? = null,
  var source: List<Reference> = listOf(),
  /**
   * Todo
   */
  var target: Identifier? = null,
  /**
   * Todo
   */
  var type: CodeableConcept? = null
) : BackboneElement
