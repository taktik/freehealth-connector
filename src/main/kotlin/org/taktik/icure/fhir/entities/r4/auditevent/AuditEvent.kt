//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.auditevent

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
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Event record kept for security purposes
 *
 * A record of an event made for purposes of maintaining a security log. Typical uses include
 * detection of intrusion attempts and monitoring for inappropriate usage.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class AuditEvent(
  /**
   * Type of action performed during the event
   */
  var action: String? = null,
  var agent: List<AuditEventAgent> = listOf(),
  override var contained: List<Resource> = listOf(),
  var entity: List<AuditEventEntity> = listOf(),
  override var extension: List<Extension> = listOf(),
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
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Whether the event succeeded or failed
   */
  var outcome: String? = null,
  /**
   * Description of the event outcome
   */
  var outcomeDesc: String? = null,
  /**
   * When the activity occurred
   */
  var period: Period? = null,
  var purposeOfEvent: List<CodeableConcept> = listOf(),
  /**
   * Time when the event was recorded
   */
  var recorded: String? = null,
  /**
   * Audit Event Reporter
   */
  var source: AuditEventSource,
  var subtype: List<Coding> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Type/identifier of event
   */
  var type: Coding
) : DomainResource
