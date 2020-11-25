//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.clinicalimpression

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Possible or likely findings and diagnoses
 *
 * Specific findings or diagnoses that were considered likely or relevant to ongoing treatment.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClinicalImpressionFinding(
  /**
   * Which investigations support finding
   */
  var basis: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * What was found
   */
  var itemCodeableConcept: CodeableConcept? = null,
  /**
   * What was found
   */
  var itemReference: Reference? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
