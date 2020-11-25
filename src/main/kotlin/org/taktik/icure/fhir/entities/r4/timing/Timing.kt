//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.timing

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * A timing schedule that specifies an event that may occur multiple times
 *
 * Specifies an event that may occur multiple times. Timing schedules are used to record when things
 * are planned, expected or requested to occur. The most common usage is in dosage instructions for
 * medications. They are also used when planning care of various kinds, and may be used for reporting
 * the schedule to which past regular activities were carried out.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Timing(
  /**
   * BID | TID | QID | AM | PM | QD | QOD | +
   */
  var code: CodeableConcept? = null,
  var event: List<String> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * When the event is to occur
   */
  var repeat: TimingRepeat? = null
) : BackboneElement
