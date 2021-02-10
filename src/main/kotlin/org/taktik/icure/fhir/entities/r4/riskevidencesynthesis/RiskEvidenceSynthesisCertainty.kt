//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.riskevidencesynthesis

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * How certain is the risk
 *
 * A description of the certainty of the risk estimate.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RiskEvidenceSynthesisCertainty(
  var certaintySubcomponent: List<RiskEvidenceSynthesisCertaintyCertaintySubcomponent> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  var rating: List<CodeableConcept> = listOf()
) : BackboneElement
