//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.implementationguide

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Information needed to build the IG
 *
 * The information needed by an IG publisher tool to publish the whole implementation guide.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImplementationGuideDefinition(
  override var extension: List<Extension> = listOf(),
  var grouping: List<ImplementationGuideDefinitionGrouping> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Page/Section in the Guide
   */
  var page: ImplementationGuideDefinitionPage? = null,
  var parameter: List<ImplementationGuideDefinitionParameter> = listOf(),
  var resource: List<ImplementationGuideDefinitionResource> = listOf(),
  var template: List<ImplementationGuideDefinitionTemplate> = listOf()
) : BackboneElement
