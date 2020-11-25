//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.visionprescription

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Vision lens authorization
 *
 * Contain the details of  the individual lens specifications and serves as the authorization for
 * the fullfillment by certified professionals.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class VisionPrescriptionLensSpecification(
  /**
   * Added power for multifocal levels
   */
  var add: Float? = null,
  /**
   * Lens meridian which contain no power for astigmatism
   */
  var axis: Int? = null,
  /**
   * Contact lens back curvature
   */
  var backCurve: Float? = null,
  /**
   * Brand required
   */
  var brand: String? = null,
  /**
   * Color required
   */
  var color: String? = null,
  /**
   * Lens power for astigmatism
   */
  var cylinder: Float? = null,
  /**
   * Contact lens diameter
   */
  var diameter: Float? = null,
  /**
   * Lens wear duration
   */
  var duration: Quantity? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * right | left
   */
  var eye: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Contact lens power
   */
  var power: Float? = null,
  var prism: List<VisionPrescriptionLensSpecificationPrism> = listOf(),
  /**
   * Product to be supplied
   */
  var product: CodeableConcept,
  /**
   * Power of the lens
   */
  var sphere: Float? = null
) : BackboneElement
