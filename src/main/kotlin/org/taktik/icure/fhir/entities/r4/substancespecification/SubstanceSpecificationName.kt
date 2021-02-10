//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancespecification

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Names applicable to this substance
 *
 * Names applicable to this substance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSpecificationName(
  var domain: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  var language: List<CodeableConcept> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The actual name
   */
  var name: String? = null,
  var official: List<SubstanceSpecificationNameOfficial> = listOf(),
  /**
   * If this is the preferred name for this substance
   */
  var preferred: Boolean? = null,
  var source: List<Reference> = listOf(),
  /**
   * The status of the name
   */
  var status: CodeableConcept? = null,
  var synonym: List<SubstanceSpecificationName> = listOf(),
  var translation: List<SubstanceSpecificationName> = listOf(),
  /**
   * Name type
   */
  var type: CodeableConcept? = null
) : BackboneElement
