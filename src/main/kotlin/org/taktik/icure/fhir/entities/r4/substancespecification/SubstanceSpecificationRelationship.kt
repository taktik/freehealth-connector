//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancespecification

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A link between this substance and another, with details of the relationship
 *
 * A link between this substance and another, with details of the relationship.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationRelationship(
  /**
   * A numeric factor for the relationship, for instance to express that the salt of a substance has
   * some percentage of the active substance in relation to some other
   */
  var amountQuantity: Quantity? = null,
  /**
   * A numeric factor for the relationship, for instance to express that the salt of a substance has
   * some percentage of the active substance in relation to some other
   */
  var amountRange: Range? = null,
  /**
   * A numeric factor for the relationship, for instance to express that the salt of a substance has
   * some percentage of the active substance in relation to some other
   */
  var amountRatio: Ratio? = null,
  /**
   * For use when the numeric
   */
  var amountRatioLowLimit: Ratio? = null,
  /**
   * A numeric factor for the relationship, for instance to express that the salt of a substance has
   * some percentage of the active substance in relation to some other
   */
  var amountString: String? = null,
  /**
   * An operator for the amount, for example "average", "approximately", "less than"
   */
  var amountType: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * For example where an enzyme strongly bonds with a particular substance, this is a defining
   * relationship for that enzyme, out of several possible substance relationships
   */
  var isDefining: Boolean? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * For example "salt to parent", "active moiety", "starting material"
   */
  var relationship: CodeableConcept? = null,
  var source: List<Reference> = listOf(),
  /**
   * A pointer to another substance, as a resource or just a representational code
   */
  var substanceCodeableConcept: CodeableConcept? = null,
  /**
   * A pointer to another substance, as a resource or just a representational code
   */
  var substanceReference: Reference? = null
) : BackboneElement
