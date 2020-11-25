//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancespecification

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Codes associated with the substance
 *
 * Codes associated with the substance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationString(
  /**
   * The specific code
   */
  var code: CodeableConcept? = null,
  /**
   * Any comment can be provided in this field, if necessary
   */
  var comment: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var source: List<Reference> = listOf(),
  /**
   * Status of the code assignment
   */
  var status: CodeableConcept? = null,
  /**
   * The date at which the code status is changed as part of the terminology maintenance
   */
  var statusDate: String? = null
) : BackboneElement
