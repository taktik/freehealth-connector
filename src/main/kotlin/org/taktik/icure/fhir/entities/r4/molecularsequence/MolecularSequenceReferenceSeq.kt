//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.molecularsequence

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A sequence used as reference
 *
 * A sequence that is used as a reference to describe variants that are present in a sequence
 * analyzed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MolecularSequenceReferenceSeq(
  /**
   * Chromosome containing genetic finding
   */
  var chromosome: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * The Genome Build used for reference, following GRCh build versions e.g. 'GRCh 37'
   */
  var genomeBuild: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * sense | antisense
   */
  var orientation: String? = null,
  /**
   * Reference identifier
   */
  var referenceSeqId: CodeableConcept? = null,
  /**
   * A pointer to another MolecularSequence entity as reference sequence
   */
  var referenceSeqPointer: Reference? = null,
  /**
   * A string to represent reference sequence
   */
  var referenceSeqString: String? = null,
  /**
   * watson | crick
   */
  var strand: String? = null,
  /**
   * End position of the window on the reference sequence
   */
  var windowEnd: Int? = null,
  /**
   * Start position of the window on the  reference sequence
   */
  var windowStart: Int? = null
) : BackboneElement
