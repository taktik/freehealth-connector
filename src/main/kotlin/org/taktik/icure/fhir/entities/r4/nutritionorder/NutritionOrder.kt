//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.nutritionorder

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Diet, formula or nutritional supplement request
 *
 * A request to supply a diet, formula feeding (enteral) or oral nutritional supplement to a
 * patient/resident.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class NutritionOrder(
  var allergyIntolerance: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Date and time the nutrition order was requested
   */
  var dateTime: String? = null,
  /**
   * The encounter associated with this nutrition order
   */
  var encounter: Reference? = null,
  /**
   * Enteral formula components
   */
  var enteralFormula: NutritionOrderEnteralFormula? = null,
  var excludeFoodModifier: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  var foodPreferenceModifier: List<CodeableConcept> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var instantiates: List<String> = listOf(),
  var instantiatesCanonical: List<String> = listOf(),
  var instantiatesUri: List<String> = listOf(),
  /**
   * proposal | plan | directive | order | original-order | reflex-order | filler-order |
   * instance-order | option
   */
  var intent: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Oral diet components
   */
  var oralDiet: NutritionOrderOralDiet? = null,
  /**
   * Who ordered the diet, formula or nutritional supplement
   */
  var orderer: Reference? = null,
  /**
   * The person who requires the diet, formula or nutritional supplement
   */
  var patient: Reference,
  /**
   * draft | active | on-hold | revoked | completed | entered-in-error | unknown
   */
  var status: String? = null,
  var supplement: List<NutritionOrderSupplement> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
