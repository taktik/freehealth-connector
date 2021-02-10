//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductundesirableeffect

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.population.Population
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * MedicinalProductUndesirableEffect
 *
 * Describe the undesirable effects of the medicinal product.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicinalProductUndesirableEffect(
  /**
   * Classification of the effect
   */
  var classification: CodeableConcept? = null,
  override var contained: List<Resource> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * The frequency of occurrence of the effect
   */
  var frequencyOfOccurrence: CodeableConcept? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
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
  var population: List<Population> = listOf(),
  var subject: List<Reference> = listOf(),
  /**
   * The symptom, condition or undesirable effect
   */
  var symptomConditionEffect: CodeableConcept? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
