//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.composition

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
 * A set of resources composed into a single coherent clinical statement with clinical attestation
 *
 * A set of healthcare-related information that is assembled together into a single logical package
 * that provides a single coherent statement of meaning, establishes its own context and that has
 * clinical attestation with regard to who is making the statement. A Composition defines the structure
 * and narrative content necessary for a document. However, a Composition alone does not constitute a
 * document. Rather, the Composition must be the first entry in a Bundle where Bundle.type=document,
 * and any other resources referenced from Composition must be included as subsequent entries in the
 * Bundle (for example Patient, Practitioner, Encounter, etc.).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Composition(
  var attester: List<CompositionAttester> = listOf(),
  var author: List<Reference> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  /**
   * As defined by affinity domain
   */
  var confidentiality: String? = null,
  override var contained: List<Resource> = listOf(),
  /**
   * Organization which maintains the composition
   */
  var custodian: Reference? = null,
  /**
   * Composition editing time
   */
  var date: String? = null,
  /**
   * Context of the Composition
   */
  var encounter: Reference? = null,
  var event: List<CompositionEvent> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Version-independent identifier for the Composition
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
  var relatesTo: List<CompositionRelatesTo> = listOf(),
  var section: List<CompositionSection> = listOf(),
  /**
   * preliminary | final | amended | entered-in-error
   */
  var status: String? = null,
  /**
   * Who and/or what the composition is about
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Human Readable name/title
   */
  var title: String? = null,
  /**
   * Kind of composition (LOINC if possible)
   */
  var type: CodeableConcept
) : DomainResource
