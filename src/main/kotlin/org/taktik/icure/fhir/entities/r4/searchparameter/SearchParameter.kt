//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.searchparameter

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
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * Search parameter for a resource
 *
 * A search parameter that defines a named search item that can be used to search/filter on a
 * resource.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class SearchParameter(
  var base: List<String> = listOf(),
  var chain: List<String> = listOf(),
  /**
   * Code used in URL
   */
  var code: String? = null,
  var comparator: List<String> = listOf(),
  var component: List<SearchParameterComponent> = listOf(),
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * Original definition for the search parameter
   */
  var derivedFrom: String? = null,
  /**
   * Natural language description of the search parameter
   */
  var description: String? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  /**
   * FHIRPath expression that extracts the values
   */
  var expression: String? = null,
  override var extension: List<Extension> = listOf(),
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
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  var modifier: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Allow multiple parameters (and)
   */
  var multipleAnd: Boolean? = null,
  /**
   * Allow multiple values per parameter (or)
   */
  var multipleOr: Boolean? = null,
  /**
   * Name for this search parameter (computer friendly)
   */
  var name: String? = null,
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this search parameter is defined
   */
  var purpose: String? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  var target: List<String> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * number | date | string | token | reference | composite | quantity | uri | special
   */
  var type: String? = null,
  /**
   * Canonical identifier for this search parameter, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the search parameter
   */
  var version: String? = null,
  /**
   * XPath that extracts the values
   */
  var xpath: String? = null,
  /**
   * normal | phonetic | nearby | distance | other
   */
  var xpathUsage: String? = null
) : DomainResource
