//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.specimendefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The specimen's container
 *
 * The specimen's container.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SpecimenDefinitionTypeTestedContainer(
  var additive: List<SpecimenDefinitionTypeTestedContainerAdditive> = listOf(),
  /**
   * Color of container cap
   */
  var cap: CodeableConcept? = null,
  /**
   * Container capacity
   */
  var capacity: Quantity? = null,
  /**
   * Container description
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Container material
   */
  var material: CodeableConcept? = null,
  /**
   * Minimum volume
   */
  var minimumVolumeQuantity: Quantity? = null,
  /**
   * Minimum volume
   */
  var minimumVolumeString: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Specimen container preparation
   */
  var preparation: String? = null,
  /**
   * Kind of container associated with the kind of specimen
   */
  var type: CodeableConcept? = null
) : BackboneElement
