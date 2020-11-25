//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.composition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Composition is broken into sections
 *
 * The root of the sections that make up the composition.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CompositionSection(
  var author: List<Reference> = listOf(),
  /**
   * Classification of section (recommended)
   */
  var code: CodeableConcept? = null,
  /**
   * Why the section is empty
   */
  var emptyReason: CodeableConcept? = null,
  var entry: List<Reference> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Who/what the section is about, when it is not about the subject of composition
   */
  var focus: Reference? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * working | snapshot | changes
   */
  var mode: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Order of section entries
   */
  var orderedBy: CodeableConcept? = null,
  var section: List<CompositionSection> = listOf(),
  /**
   * Text summary of the section, for human interpretation
   */
  var text: Narrative? = null,
  /**
   * Label for section (e.g. for ToC)
   */
  var title: String? = null
) : BackboneElement
