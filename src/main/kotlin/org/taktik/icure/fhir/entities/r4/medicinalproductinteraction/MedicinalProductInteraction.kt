//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductinteraction

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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * MedicinalProductInteraction
 *
 * The interactions of the medicinal product with other medicinal products, or other forms of
 * interactions.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicinalProductInteraction(
  override var contained: List<Resource> = listOf(),
  /**
   * The interaction described
   */
  var description: String? = null,
  /**
   * The effect of the interaction, for example "reduced gastric absorption of primary medication"
   */
  var effect: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * The incidence of the interaction, e.g. theoretical, observed
   */
  var incidence: CodeableConcept? = null,
  var interactant: List<MedicinalProductInteractionInteractant> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Actions for managing the interaction
   */
  var management: CodeableConcept? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var subject: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * The type of the interaction e.g. drug-drug interaction, drug-food interaction, drug-lab test
   * interaction
   */
  var type: CodeableConcept? = null
) : DomainResource
