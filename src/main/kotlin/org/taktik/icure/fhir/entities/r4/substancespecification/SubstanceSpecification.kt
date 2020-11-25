//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancespecification

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The detailed description of a substance, typically at a level beyond what is used for prescribing
 *
 * The detailed description of a substance, typically at a level beyond what is used for
 * prescribing.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class SubstanceSpecification(
  var code: List<SubstanceSpecificationString> = listOf(),
  /**
   * Textual comment about this record of a substance
   */
  var comment: String? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Textual description of the substance
   */
  var description: String? = null,
  /**
   * If the substance applies to only human or veterinary use
   */
  var domain: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Identifier by which this substance is known
   */
  var identifier: Identifier? = null,
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
  var moiety: List<SubstanceSpecificationMoiety> = listOf(),
  var molecularWeight: List<SubstanceSpecificationStructureIsotopeMolecularWeight> = listOf(),
  var name: List<SubstanceSpecificationName> = listOf(),
  /**
   * Data items specific to nucleic acids
   */
  var nucleicAcid: Reference? = null,
  /**
   * Data items specific to polymers
   */
  var polymer: Reference? = null,
  var property: List<SubstanceSpecificationProperty> = listOf(),
  /**
   * Data items specific to proteins
   */
  var protein: Reference? = null,
  /**
   * General information detailing this substance
   */
  var referenceInformation: Reference? = null,
  var relationship: List<SubstanceSpecificationRelationship> = listOf(),
  var source: List<Reference> = listOf(),
  /**
   * Material or taxonomic/anatomical source for the substance
   */
  var sourceMaterial: Reference? = null,
  /**
   * Status of substance within the catalogue e.g. approved
   */
  var status: CodeableConcept? = null,
  /**
   * Structural information
   */
  var structure: SubstanceSpecificationStructure? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * High level categorization, e.g. polymer or nucleic acid
   */
  var type: CodeableConcept? = null
) : DomainResource
