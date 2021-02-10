//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.device

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The capabilities supported on a  device, the standards to which the device conforms for a
 * particular purpose, and used for the communication
 *
 * The capabilities supported on a  device, the standards to which the device conforms for a
 * particular purpose, and used for the communication.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DeviceSpecialization(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The standard that is used to operate and communicate
   */
  var systemType: CodeableConcept,
  /**
   * The version of the standard that is used to operate and communicate
   */
  var version: String? = null
) : BackboneElement
