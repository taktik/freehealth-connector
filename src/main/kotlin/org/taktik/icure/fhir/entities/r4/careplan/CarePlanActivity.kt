//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.careplan

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Action to occur as part of plan
 *
 * Identifies a planned action to occur as part of the plan.  For example, a medication to be used,
 * lab tests to perform, self-monitoring, education, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CarePlanActivity(
  /**
   * In-line definition of activity
   */
  var detail: CarePlanActivityDetail? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var outcomeCodeableConcept: List<CodeableConcept> = listOf(),
  var outcomeReference: List<Reference> = listOf(),
  var progress: List<Annotation> = listOf(),
  /**
   * Activity details defined in specific resource
   */
  var reference: Reference? = null
) : BackboneElement
