//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.valueset

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Used when the value set is "expanded"
 *
 * A value set can also be "expanded", where the value set is turned into a simple collection of
 * enumerated codes. This element holds the expansion, if it has been performed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ValueSetExpansion(
  var contains: List<ValueSetExpansionContains> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Identifies the value set expansion (business identifier)
   */
  var identifier: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Offset at which this resource starts
   */
  var offset: Int? = null,
  var parameter: List<ValueSetExpansionParameter> = listOf(),
  /**
   * Time ValueSet expansion happened
   */
  var timestamp: String? = null,
  /**
   * Total number of codes in the expansion
   */
  var total: Int? = null
) : BackboneElement
