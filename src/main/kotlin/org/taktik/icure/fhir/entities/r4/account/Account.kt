//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.account

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
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Tracks balance, charges, for patient or cost center
 *
 * A financial tool for tracking value accrued for a particular purpose.  In the healthcare field,
 * used to track charges for a patient, cost centers, etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Account(
  override var contained: List<Resource> = listOf(),
  var coverage: List<AccountCoverage> = listOf(),
  /**
   * Explanation of purpose/use
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  var guarantor: List<AccountGuarantor> = listOf(),
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
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Human-readable label
   */
  var name: String? = null,
  /**
   * Entity managing the Account
   */
  var owner: Reference? = null,
  /**
   * Reference to a parent Account
   */
  var partOf: Reference? = null,
  /**
   * Transaction window
   */
  var servicePeriod: Period? = null,
  /**
   * active | inactive | entered-in-error | on-hold | unknown
   */
  var status: String? = null,
  var subject: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * E.g. patient, expense, depreciation
   */
  var type: CodeableConcept? = null
) : DomainResource
