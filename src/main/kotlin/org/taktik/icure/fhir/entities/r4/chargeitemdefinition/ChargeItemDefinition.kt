//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.chargeitemdefinition

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
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * Definition of properties and rules about how the price and the applicability of a ChargeItem can
 * be determined
 *
 * The ChargeItemDefinition resource provides the properties that apply to the (billing) codes
 * necessary to calculate costs and prices. The properties may differ largely depending on type and
 * realm, therefore this resource gives only a rough structure and requires profiling for each type of
 * billing code system.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class ChargeItemDefinition(
  var applicability: List<ChargeItemDefinitionApplicability> = listOf(),
  /**
   * When the charge item definition was approved by publisher
   */
  var approvalDate: String? = null,
  /**
   * Billing codes or product types this definition applies to
   */
  var code: CodeableConcept? = null,
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
  var derivedFromUri: List<String> = listOf(),
  /**
   * Natural language description of the charge item definition
   */
  var description: String? = null,
  /**
   * When the charge item definition is expected to be used
   */
  var effectivePeriod: Period? = null,
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
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
  var instance: List<Reference> = listOf(),
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * When the charge item definition was last reviewed
   */
  var lastReviewDate: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var partOf: List<String> = listOf(),
  var propertyGroup: List<ChargeItemDefinitionPropertyGroup> = listOf(),
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  var replaces: List<String> = listOf(),
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this charge item definition (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this charge item definition, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  /**
   * Business version of the charge item definition
   */
  var version: String? = null
) : DomainResource
