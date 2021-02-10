//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancespecification

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
 * General specifications for this substance, including how it is related to other substances
 *
 * General specifications for this substance, including how it is related to other substances.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationProperty(
  /**
   * Quantitative value for this property
   */
  var amountQuantity: Quantity? = null,
  /**
   * Quantitative value for this property
   */
  var amountString: String? = null,
  /**
   * A category for this property, e.g. Physical, Chemical, Enzymatic
   */
  var category: CodeableConcept? = null,
  /**
   * Property type e.g. viscosity, pH, isoelectric point
   */
  var code: CodeableConcept? = null,
  /**
   * A substance upon which a defining property depends (e.g. for solubility: in water, in alcohol)
   */
  var definingSubstanceCodeableConcept: CodeableConcept? = null,
  /**
   * A substance upon which a defining property depends (e.g. for solubility: in water, in alcohol)
   */
  var definingSubstanceReference: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Parameters that were used in the measurement of a property (e.g. for viscosity: measured at 20C
   * with a pH of 7.1)
   */
  var parameters: String? = null
) : BackboneElement
