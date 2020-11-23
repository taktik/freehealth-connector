//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.researchelementdefinition

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
import org.taktik.icure.fhir.entities.r4.timing.Timing
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * What defines the members of the research element
 *
 * A characteristic that defines the members of the research element. Multiple characteristics are
 * applied with "and" semantics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ResearchElementDefinitionCharacteristic(
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
   * Whether the characteristic includes or excludes members
   */
  var exclude: Boolean? = null,
  override var extension: List<Extension> = listOf(),
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
  var participantEffectiveDescription: String? = null,
  /**
   * What time period do participants cover
   */
  var participantEffectiveDuration: Duration? = null,
  /**
   * mean | median | mean-of-mean | mean-of-median | median-of-mean | median-of-median
   */
  var participantEffectiveGroupMeasure: String? = null,
  /**
   * What time period do participants cover
   */
  var participantEffectivePeriod: Period? = null,
  /**
   * Observation time from study start
   */
  var participantEffectiveTimeFromStart: Duration? = null,
  /**
   * What time period do participants cover
   */
  var participantEffectiveTiming: Timing? = null,
  /**
   * What time period does the study cover
   */
  var studyEffectiveDateTime: String? = null,
  /**
   * What time period does the study cover
   */
  var studyEffectiveDescription: String? = null,
  /**
   * What time period does the study cover
   */
  var studyEffectiveDuration: Duration? = null,
  /**
   * mean | median | mean-of-mean | mean-of-median | median-of-mean | median-of-median
   */
  var studyEffectiveGroupMeasure: String? = null,
  /**
   * What time period does the study cover
   */
  var studyEffectivePeriod: Period? = null,
  /**
   * Observation time from study start
   */
  var studyEffectiveTimeFromStart: Duration? = null,
  /**
   * What time period does the study cover
   */
  var studyEffectiveTiming: Timing? = null,
  /**
   * What unit is the outcome described in?
   */
  var unitOfMeasure: CodeableConcept? = null,
  var usageContext: List<UsageContext> = listOf()
) : BackboneElement
