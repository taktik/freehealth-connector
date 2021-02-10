//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.triggerdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.datarequirement.DataRequirement
import org.taktik.icure.fhir.entities.r4.expression.Expression
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Defines an expected trigger for a module
 *
 * A description of a triggering event. Triggering events can be named events, data events, or
 * periodic, as determined by the type element.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TriggerDefinition(
  /**
   * Whether the event triggers (boolean expression)
   */
  var condition: Expression? = null,
  var data: List<DataRequirement> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Name or URI that identifies the event
   */
  var name: String? = null,
  /**
   * Timing of the event
   */
  var timingDate: String? = null,
  /**
   * Timing of the event
   */
  var timingDateTime: String? = null,
  /**
   * Timing of the event
   */
  var timingReference: Reference? = null,
  /**
   * Timing of the event
   */
  var timingTiming: Timing? = null,
  /**
   * named-event | periodic | data-changed | data-added | data-modified | data-removed |
   * data-accessed | data-access-ended
   */
  var type: String? = null
) : Element
