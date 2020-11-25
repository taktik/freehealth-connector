//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.consent

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Constraints to the base Consent.policyRule
 *
 * An exception to the base policy of this consent. An exception can be an addition or removar of
 * access permissions.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ConsentProvision(
  var action: List<CodeableConcept> = listOf(),
  var actor: List<ConsentProvisionActor> = listOf(),
  @JsonProperty("class")
  var class_fhir: List<Coding> = listOf(),
  var code: List<CodeableConcept> = listOf(),
  var data: List<ConsentProvisionData> = listOf(),
  /**
   * Timeframe for data controlled by this rule
   */
  var dataPeriod: Period? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Timeframe for this rule
   */
  var period: Period? = null,
  var provision: List<ConsentProvision> = listOf(),
  var purpose: List<Coding> = listOf(),
  var securityLabel: List<Coding> = listOf(),
  /**
   * deny | permit
   */
  var type: String? = null
) : BackboneElement
