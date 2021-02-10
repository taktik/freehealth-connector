//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.specimendefinition

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

/**
 * Kind of specimen
 *
 * A kind of specimen with associated set of requirements.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class SpecimenDefinition(
  var collection: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Business identifier of a kind of specimen
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
  var patientPreparation: List<CodeableConcept> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Time aspect for collection
   */
  var timeAspect: String? = null,
  /**
   * Kind of material to collect
   */
  var typeCollected: CodeableConcept? = null,
  var typeTested: List<SpecimenDefinitionTypeTested> = listOf()
) : DomainResource
