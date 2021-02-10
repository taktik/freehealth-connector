//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.capabilitystatement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * If this describes a specific instance
 *
 * Identifies a specific implementation instance that is described by the capability statement -
 * i.e. a particular installation, rather than the capabilities of a software program.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CapabilityStatementImplementation(
  /**
   * Organization that manages the data
   */
  var custodian: Reference? = null,
  /**
   * Describes this specific instance
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Base URL for the installation
   */
  var url: String? = null
) : BackboneElement
