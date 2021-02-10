//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.specimen

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Collection details
 *
 * Details concerning the specimen collection.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SpecimenCollection(
  /**
   * Anatomical collection site
   */
  var bodySite: CodeableConcept? = null,
  /**
   * Collection time
   */
  var collectedDateTime: String? = null,
  /**
   * Collection time
   */
  var collectedPeriod: Period? = null,
  /**
   * Who collected the specimen
   */
  var collector: Reference? = null,
  /**
   * How long it took to collect specimen
   */
  var duration: Duration? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Whether or how long patient abstained from food and/or drink
   */
  var fastingStatusCodeableConcept: CodeableConcept? = null,
  /**
   * Whether or how long patient abstained from food and/or drink
   */
  var fastingStatusDuration: Duration? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Technique used to perform collection
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The quantity of specimen collected
   */
  var quantity: Quantity? = null
) : BackboneElement
