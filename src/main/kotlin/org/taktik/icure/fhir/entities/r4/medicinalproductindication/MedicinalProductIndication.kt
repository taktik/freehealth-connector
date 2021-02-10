//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductindication

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.population.Population
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * MedicinalProductIndication
 *
 * Indication for the Medicinal Product.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicinalProductIndication(
  var comorbidity: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * The status of the disease or symptom for which the indication applies
   */
  var diseaseStatus: CodeableConcept? = null,
  /**
   * The disease, symptom or procedure that is the indication for treatment
   */
  var diseaseSymptomProcedure: CodeableConcept? = null,
  /**
   * Timing or duration information as part of the indication
   */
  var duration: Quantity? = null,
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
   * The intended effect, aim or strategy to be achieved by the indication
   */
  var intendedEffect: CodeableConcept? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var otherTherapy: List<MedicinalProductIndicationOtherTherapy> = listOf(),
  var population: List<Population> = listOf(),
  var subject: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var undesirableEffect: List<Reference> = listOf()
) : DomainResource
