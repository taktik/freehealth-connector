//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancenucleicacid

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative

/**
 * Nucleic acids are defined by three distinct elements: the base, sugar and linkage. Individual
 * substance/moiety IDs will be created for each of these elements. The nucleotide sequence will be
 * always entered in the 5’-3’ direction
 *
 * Nucleic acids are defined by three distinct elements: the base, sugar and linkage. Individual
 * substance/moiety IDs will be created for each of these elements. The nucleotide sequence will be
 * always entered in the 5’-3’ direction.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class SubstanceNucleicAcid(
  /**
   * The area of hybridisation shall be described if applicable for double stranded RNA or DNA. The
   * number associated with the subunit followed by the number associated to the residue shall be
   * specified in increasing order. The underscore “” shall be used as separator as follows:
   * “Subunitnumber Residue”
   */
  var areaOfHybridisation: String? = null,
  override var contained: List<Resource> = listOf(),
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
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The number of linear sequences of nucleotides linked through phosphodiester bonds shall be
   * described. Subunits would be strands of nucleic acids that are tightly associated typically
   * through Watson-Crick base pairing. NOTE: If not specified in the reference source, the assumption
   * is that there is 1 subunit
   */
  var numberOfSubunits: Int? = null,
  /**
   * (TBC)
   */
  var oligoNucleotideType: CodeableConcept? = null,
  /**
   * The type of the sequence shall be specified based on a controlled vocabulary
   */
  var sequenceType: CodeableConcept? = null,
  var subunit: List<SubstanceNucleicAcidSubunit> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
