//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.adverseevent

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Information on the possible cause of the event
 *
 * Information on the possible cause of the event.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class AdverseEventSuspectEntityCausality(
  /**
   * Assessment of if the entity caused the event
   */
  var assessment: CodeableConcept? = null,
  /**
   * AdverseEvent.suspectEntity.causalityAuthor
   */
  var author: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * ProbabilityScale | Bayesian | Checklist
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * AdverseEvent.suspectEntity.causalityProductRelatedness
   */
  var productRelatedness: String? = null
) : BackboneElement
