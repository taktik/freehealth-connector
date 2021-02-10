//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.biologicallyderivedproduct

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Product storage
 *
 * Product storage.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class BiologicallyDerivedProductStorage(
  /**
   * Description of storage
   */
  var description: String? = null,
  /**
   * Storage timeperiod
   */
  var duration: Period? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * farenheit | celsius | kelvin
   */
  var scale: String? = null,
  /**
   * Storage temperature
   */
  var temperature: Float? = null
) : BackboneElement
