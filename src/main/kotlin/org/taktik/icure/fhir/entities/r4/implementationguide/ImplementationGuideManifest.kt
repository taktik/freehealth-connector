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
 * Information about an assembled IG
 *
 * Information about an assembled implementation guide, created by the publication tooling.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImplementationGuideManifest(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var image: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var other: List<String> = listOf(),
  var page: List<ImplementationGuideManifestPage> = listOf(),
  /**
   * Location of rendered implementation guide
   */
  var rendering: String? = null,
  var resource: List<ImplementationGuideManifestResource> = listOf()
) : BackboneElement
