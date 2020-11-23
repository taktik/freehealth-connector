//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.biologicallyderivedproduct

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Any manipulation of product post-collection
 *
 * Any manipulation of product post-collection that is intended to alter the product.  For example a
 * buffy-coat enrichment or CD8 reduction of Peripheral Blood Stem Cells to make it more suitable for
 * infusion.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class BiologicallyDerivedProductManipulation(
  /**
   * Description of manipulation
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Time of manipulation
   */
  var timeDateTime: String? = null,
  /**
   * Time of manipulation
   */
  var timePeriod: Period? = null
) : BackboneElement
