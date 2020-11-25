//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.episodeofcare

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The list of diagnosis relevant to this episode of care
 *
 * The list of diagnosis relevant to this episode of care.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class EpisodeOfCareDiagnosis(
  /**
   * Conditions/problems/diagnoses this episode of care is for
   */
  var condition: Reference,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Ranking of the diagnosis (for each role type)
   */
  var rank: Int? = null,
  /**
   * Role that this diagnosis has within the episode of care (e.g. admission, billing, discharge …)
   */
  var role: CodeableConcept? = null
) : BackboneElement
