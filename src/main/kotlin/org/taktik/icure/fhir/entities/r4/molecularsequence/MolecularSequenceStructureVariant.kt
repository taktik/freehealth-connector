//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.molecularsequence

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Structural variant
 *
 * Information about chromosome structure variation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MolecularSequenceStructureVariant(
  /**
   * Does the structural variant have base pair resolution breakpoints?
   */
  var exact: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Structural variant inner
   */
  var inner: MolecularSequenceStructureVariantInner? = null,
  /**
   * Structural variant length
   */
  var length: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Structural variant outer
   */
  var outer: MolecularSequenceStructureVariantOuter? = null,
  /**
   * Structural variant change type
   */
  var variantType: CodeableConcept? = null
) : BackboneElement
