//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.implementationguide

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Resource in the implementation guide
 *
 * A resource that is part of the implementation guide. Conformance resources (value set, structure
 * definition, capability statements etc.) are obvious candidates for inclusion, but any kind of
 * resource can be included as an example resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImplementationGuideManifestResource(
  /**
   * Is an example/What is this an example of?
   */
  var exampleBoolean: Boolean? = null,
  /**
   * Is an example/What is this an example of?
   */
  var exampleCanonical: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Location of the resource
   */
  var reference: Reference,
  /**
   * Relative path for page in IG
   */
  var relativePath: String? = null
) : BackboneElement
