//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testscript

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Required capability that is assumed to function correctly on the FHIR server being tested
 *
 * The required capability must exist and are assumed to function correctly on the FHIR server being
 * tested.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TestScriptMetadata(
  var capability: List<TestScriptMetadataCapability> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var link: List<TestScriptMetadataLink> = listOf(),
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
