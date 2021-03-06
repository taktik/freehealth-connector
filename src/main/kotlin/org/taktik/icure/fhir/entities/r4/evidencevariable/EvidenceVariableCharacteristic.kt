//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.evidencevariable

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.datarequirement.DataRequirement
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.expression.Expression
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.timing.Timing
import org.taktik.icure.fhir.entities.r4.triggerdefinition.TriggerDefinition
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * What defines the members of the evidence element
 *
 * A characteristic that defines the members of the evidence element. Multiple characteristics are
 * applied with "and" semantics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class EvidenceVariableCharacteristic(
  /**
   * What code or expression defines members?
   */
  var definitionCanonical: String? = null,
  /**
   * What code or expression defines members?
   */
  var definitionCodeableConcept: CodeableConcept,
  /**
   * What code or expression defines members?
   */
  var definitionDataRequirement: DataRequirement,
  /**
   * What code or expression defines members?
   */
  var definitionExpression: Expression,
  /**
   * What code or expression defines members?
   */
  var definitionReference: Reference,
  /**
   * What code or expression defines members?
   */
  var definitionTriggerDefinition: TriggerDefinition,
  /**
   * Natural language description of the characteristic
   */
  var description: String? = null,
  /**
   * Whether the characteristic includes or excludes members
   */
  var exclude: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * mean | median | mean-of-mean | mean-of-median | median-of-mean | median-of-median
   */
  var groupMeasure: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * What time period do participants cover
   */
  var participantEffectiveDateTime: String? = null,
  /**
   * What time period do participants cover
   */
  var participantEffectiveDuration: Duration? = null,
  /**
   * What time period do participants cover
   */
  var participantEffectivePeriod: Period? = null,
  /**
   * What time period do participants cover
   */
  var participantEffectiveTiming: Timing? = null,
  /**
   * Observation time from study start
   */
  var timeFromStart: Duration? = null,
  var usageContext: List<UsageContext> = listOf()
) : BackboneElement
