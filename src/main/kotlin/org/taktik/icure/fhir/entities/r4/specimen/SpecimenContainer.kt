//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.specimen

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Direct container of specimen (tube/slide, etc.)
 *
 * The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in
 * tray in rack is not addressed here.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SpecimenContainer(
  /**
   * Additive associated with container
   */
  var additiveCodeableConcept: CodeableConcept? = null,
  /**
   * Additive associated with container
   */
  var additiveReference: Reference? = null,
  /**
   * Container volume or size
   */
  var capacity: Quantity? = null,
  /**
   * Textual description of the container
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Quantity of specimen within container
   */
  var specimenQuantity: Quantity? = null,
  /**
   * Kind of container directly associated with specimen
   */
  var type: CodeableConcept? = null
) : BackboneElement
