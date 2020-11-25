//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.catalogentry

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * An entry in a catalog
 *
 * Catalog entries are wrappers that contextualize items included in a catalog.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class CatalogEntry(
  var additionalCharacteristic: List<CodeableConcept> = listOf(),
  var additionalClassification: List<CodeableConcept> = listOf(),
  var additionalIdentifier: List<Identifier> = listOf(),
  var classification: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
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
   * When was this catalog last updated
   */
  var lastUpdated: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Whether the entry represents an orderable item
   */
  var orderable: Boolean? = null,
  /**
   * The item that is being defined
   */
  var referencedItem: Reference,
  var relatedEntry: List<CatalogEntryRelatedEntry> = listOf(),
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * The type of item - medication, device, service, protocol or other
   */
  var type: CodeableConcept? = null,
  /**
   * The date until which this catalog entry is expected to be active
   */
  var validTo: String? = null,
  /**
   * The time period in which this catalog entry is expected to be active
   */
  var validityPeriod: Period? = null
) : DomainResource
