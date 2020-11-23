//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.molecularsequence

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Information about a biological sequence
 *
 * Raw data describing a biological sequence.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MolecularSequence(
  override var contained: List<Resource> = listOf(),
  /**
   * Base number of coordinate system (0 for 0-based numbering or coordinates, inclusive start,
   * exclusive end, 1 for 1-based numbering, inclusive start, inclusive end)
   */
  var coordinateSystem: Int? = null,
  /**
   * The method for sequencing
   */
  var device: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Sequence that was observed
   */
  var observedSeq: String? = null,
  /**
   * Who and/or what this is about
   */
  var patient: Reference? = null,
  /**
   * Who should be responsible for test result
   */
  var performer: Reference? = null,
  var pointer: List<Reference> = listOf(),
  var quality: List<MolecularSequenceQuality> = listOf(),
  /**
   * The number of copies of the sequence of interest.  (RNASeq)
   */
  var quantity: Quantity? = null,
  /**
   * Average number of reads representing a given nucleotide in the reconstructed sequence
   */
  var readCoverage: Int? = null,
  /**
   * A sequence used as reference
   */
  var referenceSeq: MolecularSequenceReferenceSeq? = null,
  var repository: List<MolecularSequenceRepository> = listOf(),
  /**
   * Specimen used for sequencing
   */
  var specimen: Reference? = null,
  var structureVariant: List<MolecularSequenceStructureVariant> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * aa | dna | rna
   */
  var type: String? = null,
  var variant: List<MolecularSequenceVariant> = listOf()
) : DomainResource
