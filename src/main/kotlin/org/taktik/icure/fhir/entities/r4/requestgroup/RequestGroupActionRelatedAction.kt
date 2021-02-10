//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.requestgroup

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * Relationship to another action
 *
 * A relationship to another action such as "before" or "30-60 minutes after start of".
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RequestGroupActionRelatedAction(
  /**
   * What action this is related to
   */
  var actionId: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Time offset for the relationship
   */
  var offsetDuration: Duration? = null,
  /**
   * Time offset for the relationship
   */
  var offsetRange: Range? = null,
  /**
   * before-start | before | before-end | concurrent-with-start | concurrent | concurrent-with-end |
   * after-start | after | after-end
   */
  var relationship: String? = null
) : BackboneElement
