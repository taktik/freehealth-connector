//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.auditevent

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Actor involved in the event
 *
 * An actor taking an active role in the event or activity that is logged.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class AuditEventAgent(
  /**
   * Alternative User identity
   */
  var altId: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Where
   */
  var location: Reference? = null,
  /**
   * Type of media
   */
  var media: Coding? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Human friendly name for the agent
   */
  var name: String? = null,
  /**
   * Logical network location for application activity
   */
  var network: AuditEventAgentNetwork? = null,
  var policy: List<String> = listOf(),
  var purposeOfUse: List<CodeableConcept> = listOf(),
  /**
   * Whether user is initiator
   */
  var requestor: Boolean? = null,
  var role: List<CodeableConcept> = listOf(),
  /**
   * How agent participated
   */
  var type: CodeableConcept? = null,
  /**
   * Identifier of who
   */
  var who: Reference? = null
) : BackboneElement
