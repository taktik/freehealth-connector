//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.devicedefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The actual configuration settings of a device as it actually operates, e.g., regulation status,
 * time properties
 *
 * The actual configuration settings of a device as it actually operates, e.g., regulation status,
 * time properties.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DeviceDefinitionProperty(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Code that specifies the property DeviceDefinitionPropetyCode (Extensible)
   */
  var type: CodeableConcept,
  var valueCode: List<CodeableConcept> = listOf(),
  var valueQuantity: List<Quantity> = listOf()
) : BackboneElement
