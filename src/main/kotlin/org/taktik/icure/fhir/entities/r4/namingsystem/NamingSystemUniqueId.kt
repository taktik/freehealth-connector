//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.namingsystem

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Unique identifiers used for system
 *
 * Indicates how the system may be identified when referenced in electronic exchange.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class NamingSystemUniqueId(
  /**
   * Notes about identifier usage
   */
  var comment: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * When is identifier valid?
   */
  var period: Period? = null,
  /**
   * Is this the id that should be used for this type
   */
  var preferred: Boolean? = null,
  /**
   * oid | uuid | uri | other
   */
  var type: String? = null,
  /**
   * The unique identifier
   */
  var value: String? = null
) : BackboneElement
