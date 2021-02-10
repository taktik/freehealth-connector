//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproduct

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Country where the name applies
 *
 * Country where the name applies.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductNameCountryLanguage(
  /**
   * Country code for where this name applies
   */
  var country: CodeableConcept,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Jurisdiction code for where this name applies
   */
  var jurisdiction: CodeableConcept? = null,
  /**
   * Language code for this name
   */
  var language: CodeableConcept,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
