//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contract

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Legal Agreement
 *
 * Legally enforceable, formally recorded unilateral or bilateral directive i.e., a policy or
 * agreement.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Contract(
  var alias: List<String> = listOf(),
  /**
   * Effective time
   */
  var applies: Period? = null,
  /**
   * Source of Contract
   */
  var author: Reference? = null,
  var authority: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Contract precursor content
   */
  var contentDefinition: ContractContentDefinition? = null,
  /**
   * Content derived from the basal information
   */
  var contentDerivative: CodeableConcept? = null,
  var domain: List<Reference> = listOf(),
  /**
   * Contract cessation cause
   */
  var expirationType: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  var friendly: List<ContractFriendly> = listOf(),
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
   * Source Contract Definition
   */
  var instantiatesCanonical: Reference? = null,
  /**
   * External Contract Definition
   */
  var instantiatesUri: String? = null,
  /**
   * When this Contract was issued
   */
  var issued: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var legal: List<ContractLegal> = listOf(),
  /**
   * Negotiation status
   */
  var legalState: CodeableConcept? = null,
  /**
   * Binding Contract
   */
  var legallyBindingAttachment: Attachment? = null,
  /**
   * Binding Contract
   */
  var legallyBindingReference: Reference? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Computer friendly designation
   */
  var name: String? = null,
  var relevantHistory: List<Reference> = listOf(),
  var rule: List<ContractRule> = listOf(),
  /**
   * Range of Legal Concerns
   */
  var scope: CodeableConcept? = null,
  var signer: List<ContractSigner> = listOf(),
  var site: List<Reference> = listOf(),
  /**
   * amended | appended | cancelled | disputed | entered-in-error | executable | executed |
   * negotiable | offered | policy | rejected | renewed | revoked | resolved | terminated
   */
  var status: String? = null,
  var subType: List<CodeableConcept> = listOf(),
  var subject: List<Reference> = listOf(),
  /**
   * Subordinate Friendly name
   */
  var subtitle: String? = null,
  var supportingInfo: List<Reference> = listOf(),
  var term: List<ContractTerm> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Human Friendly name
   */
  var title: String? = null,
  /**
   * Focus of contract interest
   */
  var topicCodeableConcept: CodeableConcept? = null,
  /**
   * Focus of contract interest
   */
  var topicReference: Reference? = null,
  /**
   * Legal instrument category
   */
  var type: CodeableConcept? = null,
  /**
   * Basal definition
   */
  var url: String? = null,
  /**
   * Business edition
   */
  var version: String? = null
) : DomainResource
