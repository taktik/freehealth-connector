//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.graphdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Compartment Consistency Rules
 *
 * Compartment Consistency Rules.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class GraphDefinitionLinkTargetCompartment(
  /**
   * Patient | Encounter | RelatedPerson | Practitioner | Device
   */
  var code: String? = null,
  /**
   * Documentation for FHIRPath expression
   */
  var description: String? = null,
  /**
   * Custom rule, as a FHIRPath expression
   */
  var expression: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * identical | matching | different | custom
   */
  var rule: String? = null,
  /**
   * condition | requirement
   */
  var use: String? = null
) : BackboneElement
