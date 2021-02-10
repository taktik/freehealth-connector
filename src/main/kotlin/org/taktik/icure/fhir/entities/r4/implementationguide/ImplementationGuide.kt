//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.implementationguide

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * A set of rules about how FHIR is used
 *
 * A set of rules of how a particular interoperability or standards problem is solved - typically
 * through the use of FHIR resources. This resource is used to gather all the parts of an
 * implementation guide into a logical whole and to publish a computable definition of all the parts.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ImplementationGuide(
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Use and/or publishing restrictions
   */
  var copyright: String? = null,
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * Information needed to build the IG
   */
  var definition: ImplementationGuideDefinition? = null,
  var dependsOn: List<ImplementationGuideDependsOn> = listOf(),
  /**
   * Natural language description of the implementation guide
   */
  var description: String? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var fhirVersion: List<String> = listOf(),
  @JsonProperty("global")
  var global_fhir: List<ImplementationGuideGlobal> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * SPDX license code for this IG (or not-open-source)
   */
  var license: String? = null,
  /**
   * Information about an assembled IG
   */
  var manifest: ImplementationGuideManifest? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this implementation guide (computer friendly)
   */
  var name: String? = null,
  /**
   * NPM Package name for IG
   */
  var packageId: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this implementation guide (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this implementation guide, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the implementation guide
   */
  var version: String? = null
) : DomainResource
