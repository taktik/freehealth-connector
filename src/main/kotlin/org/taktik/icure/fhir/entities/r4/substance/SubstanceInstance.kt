//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substance

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier

/**
 * If this describes a specific package/container of the substance
 *
 * Substance may be used to describe a kind of substance, or a specific package/container of the
 * substance: an instance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceInstance(
  /**
   * When no longer valid to use
   */
  var expiry: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Identifier of the package/container
   */
  var identifier: Identifier? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Amount of substance in the package
   */
  var quantity: Quantity? = null
) : BackboneElement
