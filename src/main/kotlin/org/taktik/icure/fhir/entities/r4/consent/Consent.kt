//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.consent

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
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A healthcare consumer's  choices to permit or deny recipients or roles to perform actions for
 * specific purposes and periods of time
 *
 * A record of a healthcare consumer’s  choices, which permits or denies identified recipient(s) or
 * recipient role(s) to perform one or more actions within a given policy context, for specific
 * purposes and periods of time.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Consent(
  var category: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * When this Consent was created or indexed
   */
  var dateTime: String? = null,
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
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var organization: List<Reference> = listOf(),
  /**
   * Who the consent applies to
   */
  var patient: Reference? = null,
  var performer: List<Reference> = listOf(),
  var policy: List<ConsentPolicy> = listOf(),
  /**
   * Regulation that this consents to
   */
  var policyRule: CodeableConcept? = null,
  /**
   * Constraints to the base Consent.policyRule
   */
  var provision: ConsentProvision? = null,
  /**
   * Which of the four areas this resource covers (extensible)
   */
  var scope: CodeableConcept,
  /**
   * Source from which this consent is taken
   */
  var sourceAttachment: Attachment? = null,
  /**
   * Source from which this consent is taken
   */
  var sourceReference: Reference? = null,
  /**
   * draft | proposed | active | rejected | inactive | entered-in-error
   */
  var status: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  var verification: List<ConsentVerification> = listOf()
) : DomainResource
