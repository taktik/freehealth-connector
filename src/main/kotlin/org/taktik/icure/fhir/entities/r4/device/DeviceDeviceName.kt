//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.device

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The name of the device as given by the manufacturer
 *
 * This represents the manufacturer's name of the device as provided by the device, from a UDI
 * label, or by a person describing the Device.  This typically would be used when a person provides
 * the name(s) or when the device represents one of the names available from DeviceDefinition.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DeviceDeviceName(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The name of the device
   */
  var name: String? = null,
  /**
   * udi-label-name | user-friendly-name | patient-reported-name | manufacturer-name | model-name |
   * other
   */
  var type: String? = null
) : BackboneElement
