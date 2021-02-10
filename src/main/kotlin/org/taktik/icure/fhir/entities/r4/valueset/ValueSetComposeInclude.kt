//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.valueset

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Include one or more codes from a code system or other value set(s)
 *
 * Include one or more codes from a code system or other value set(s).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ValueSetComposeInclude(
  var concept: List<ValueSetComposeIncludeConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  var filter: List<ValueSetComposeIncludeFilter> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The system the codes come from
   */
  var system: String? = null,
  var valueSet: List<String> = listOf(),
  /**
   * Specific version of the code system referred to
   */
  var version: String? = null
) : BackboneElement
