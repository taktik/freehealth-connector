//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.verificationresult

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
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Describes validation requirements, source(s), status and dates for one or more elements
 *
 * Describes validation requirements, source(s), status and dates for one or more elements.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class VerificationResult(
  /**
   * Information about the entity attesting to information
   */
  var attestation: VerificationResultAttestation? = null,
  override var contained: List<Resource> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * fatal | warn | rec-only | none
   */
  var failureAction: CodeableConcept? = null,
  /**
   * Frequency of revalidation
   */
  var frequency: Timing? = null,
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
   * The date/time validation was last completed (including failed validations)
   */
  var lastPerformed: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * none | initial | periodic
   */
  var need: CodeableConcept? = null,
  /**
   * The date when target is next validated, if appropriate
   */
  var nextScheduled: String? = null,
  var primarySource: List<VerificationResultPrimarySource> = listOf(),
  /**
   * attested | validated | in-process | req-revalid | val-fail | reval-fail
   */
  var status: String? = null,
  /**
   * When the validation status was updated
   */
  var statusDate: String? = null,
  var target: List<Reference> = listOf(),
  var targetLocation: List<String> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var validationProcess: List<CodeableConcept> = listOf(),
  /**
   * nothing | primary | multiple
   */
  var validationType: CodeableConcept? = null,
  var validator: List<VerificationResultValidator> = listOf()
) : DomainResource
