//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.immunizationrecommendation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Guidance or advice relating to an immunization
 *
 * A patient's point-in-time set of recommendations (i.e. forecasting) according to a published
 * schedule with optional supporting justification.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ImmunizationRecommendation(
  /**
   * Who is responsible for protocol
   */
  var authority: Reference? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Date recommendation(s) created
   */
  var date: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Who this profile is for
   */
  var patient: Reference,
  var recommendation: List<ImmunizationRecommendationRecommendation> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
