//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.careteam

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Members of the team
 *
 * Identifies all people and organizations who are expected to be involved in the care team.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CareTeamParticipant(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Who is involved
   */
  var member: Reference? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Organization of the practitioner
   */
  var onBehalfOf: Reference? = null,
  /**
   * Time period of participant
   */
  var period: Period? = null,
  var role: List<CodeableConcept> = listOf()
) : BackboneElement
