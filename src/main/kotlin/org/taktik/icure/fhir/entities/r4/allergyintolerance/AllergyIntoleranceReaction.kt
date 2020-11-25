//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.allergyintolerance

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Adverse Reaction Events linked to exposure to substance
 *
 * Details about each adverse reaction event linked to exposure to the identified substance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class AllergyIntoleranceReaction(
  /**
   * Description of the event as a whole
   */
  var description: String? = null,
  /**
   * How the subject was exposed to the substance
   */
  var exposureRoute: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var manifestation: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Date(/time) when manifestations showed
   */
  var onset: String? = null,
  /**
   * mild | moderate | severe (of event as a whole)
   */
  var severity: String? = null,
  /**
   * Specific substance or pharmaceutical product considered to be responsible for event
   */
  var substance: CodeableConcept? = null
) : BackboneElement
