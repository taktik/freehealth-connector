//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contract

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * Entity being ascribed responsibility
 *
 * An actor taking a role in an activity for which it can be assigned some degree of responsibility
 * for the activity taking place.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractTermAction(
  /**
   * Episode associated with action
   */
  var context: Reference? = null,
  var contextLinkId: List<String> = listOf(),
  /**
   * True if the term prohibits the  action
   */
  var doNotPerform: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Purpose for the Contract Term Action
   */
  var intent: CodeableConcept,
  var linkId: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * When action happens
   */
  var occurrenceDateTime: String? = null,
  /**
   * When action happens
   */
  var occurrencePeriod: Period? = null,
  /**
   * When action happens
   */
  var occurrenceTiming: Timing? = null,
  /**
   * Actor that wil execute (or not) the action
   */
  var performer: Reference? = null,
  var performerLinkId: List<String> = listOf(),
  /**
   * Competency of the performer
   */
  var performerRole: CodeableConcept? = null,
  var performerType: List<CodeableConcept> = listOf(),
  var reason: List<String> = listOf(),
  var reasonCode: List<CodeableConcept> = listOf(),
  var reasonLinkId: List<String> = listOf(),
  var reasonReference: List<Reference> = listOf(),
  var requester: List<Reference> = listOf(),
  var requesterLinkId: List<String> = listOf(),
  var securityLabelNumber: List<Int> = listOf(),
  /**
   * State of the action
   */
  var status: CodeableConcept,
  var subject: List<ContractTermActionSubject> = listOf(),
  /**
   * Type or form of the action
   */
  var type: CodeableConcept
) : BackboneElement
