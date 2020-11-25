//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substanceprotein

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
 * A SubstanceProtein is defined as a single unit of a linear amino acid sequence, or a combination
 * of subunits that are either covalently linked or have a defined invariant stoichiometric
 * relationship. This includes all synthetic, recombinant and purified SubstanceProteins of defined
 * sequence, whether the use is therapeutic or prophylactic. This set of elements will be used to
 * describe albumins, coagulation factors, cytokines, growth factors, peptide/SubstanceProtein
 * hormones, enzymes, toxins, toxoids, recombinant vaccines, and immunomodulators
 *
 * A SubstanceProtein is defined as a single unit of a linear amino acid sequence, or a combination
 * of subunits that are either covalently linked or have a defined invariant stoichiometric
 * relationship. This includes all synthetic, recombinant and purified SubstanceProteins of defined
 * sequence, whether the use is therapeutic or prophylactic. This set of elements will be used to
 * describe albumins, coagulation factors, cytokines, growth factors, peptide/SubstanceProtein
 * hormones, enzymes, toxins, toxoids, recombinant vaccines, and immunomodulators.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class SubstanceProtein(
  override var contained: List<Resource> = listOf(),
  var disulfideLinkage: List<String> = listOf(),
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
   * Number of linear sequences of amino acids linked through peptide bonds. The number of subunits
   * constituting the SubstanceProtein shall be described. It is possible that the number of subunits
   * can be variable
   */
  var numberOfSubunits: Int? = null,
  /**
   * The SubstanceProtein descriptive elements will only be used when a complete or partial amino
   * acid sequence is available or derivable from a nucleic acid sequence
   */
  var sequenceType: CodeableConcept? = null,
  var subunit: List<SubstanceProteinSubunit> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
