//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.task

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.address.Address
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.contributor.Contributor
import org.taktik.icure.fhir.entities.r4.count.Count
import org.taktik.icure.fhir.entities.r4.datarequirement.DataRequirement
import org.taktik.icure.fhir.entities.r4.distance.Distance
import org.taktik.icure.fhir.entities.r4.dosage.Dosage
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.expression.Expression
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.humanname.HumanName
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.money.Money
import org.taktik.icure.fhir.entities.r4.parameterdefinition.ParameterDefinition
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.relatedartifact.RelatedArtifact
import org.taktik.icure.fhir.entities.r4.sampleddata.SampledData
import org.taktik.icure.fhir.entities.r4.signature.Signature
import org.taktik.icure.fhir.entities.r4.timing.Timing
import org.taktik.icure.fhir.entities.r4.triggerdefinition.TriggerDefinition
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * Information produced as part of task
 *
 * Outputs produced by the Task.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TaskOutput(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Label for output
   */
  var type: CodeableConcept,
  /**
   * Result of output
   */
  var valueAddress: Address,
  /**
   * Result of output
   */
  var valueAge: Age,
  /**
   * Result of output
   */
  var valueAnnotation: Annotation,
  /**
   * Result of output
   */
  var valueAttachment: Attachment,
  /**
   * Result of output
   */
  var valueBase64Binary: String? = null,
  /**
   * Result of output
   */
  var valueBoolean: Boolean? = null,
  /**
   * Result of output
   */
  var valueCanonical: String? = null,
  /**
   * Result of output
   */
  var valueCode: String? = null,
  /**
   * Result of output
   */
  var valueCodeableConcept: CodeableConcept,
  /**
   * Result of output
   */
  var valueCoding: Coding,
  /**
   * Result of output
   */
  var valueContactDetail: ContactDetail,
  /**
   * Result of output
   */
  var valueContactPoint: ContactPoint,
  /**
   * Result of output
   */
  var valueContributor: Contributor,
  /**
   * Result of output
   */
  var valueCount: Count,
  /**
   * Result of output
   */
  var valueDataRequirement: DataRequirement,
  /**
   * Result of output
   */
  var valueDate: String? = null,
  /**
   * Result of output
   */
  var valueDateTime: String? = null,
  /**
   * Result of output
   */
  var valueDecimal: Float? = null,
  /**
   * Result of output
   */
  var valueDistance: Distance,
  /**
   * Result of output
   */
  var valueDosage: Dosage,
  /**
   * Result of output
   */
  var valueDuration: Duration,
  /**
   * Result of output
   */
  var valueExpression: Expression,
  /**
   * Result of output
   */
  var valueHumanName: HumanName,
  /**
   * Result of output
   */
  var valueId: String? = null,
  /**
   * Result of output
   */
  var valueIdentifier: Identifier,
  /**
   * Result of output
   */
  var valueInstant: String? = null,
  /**
   * Result of output
   */
  var valueInteger: Int? = null,
  /**
   * Result of output
   */
  var valueMarkdown: String? = null,
  /**
   * Result of output
   */
  var valueMeta: Meta,
  /**
   * Result of output
   */
  var valueMoney: Money,
  /**
   * Result of output
   */
  var valueOid: String? = null,
  /**
   * Result of output
   */
  var valueParameterDefinition: ParameterDefinition,
  /**
   * Result of output
   */
  var valuePeriod: Period,
  /**
   * Result of output
   */
  var valuePositiveInt: Int? = null,
  /**
   * Result of output
   */
  var valueQuantity: Quantity,
  /**
   * Result of output
   */
  var valueRange: Range,
  /**
   * Result of output
   */
  var valueRatio: Ratio,
  /**
   * Result of output
   */
  var valueReference: Reference,
  /**
   * Result of output
   */
  var valueRelatedArtifact: RelatedArtifact,
  /**
   * Result of output
   */
  var valueSampledData: SampledData,
  /**
   * Result of output
   */
  var valueSignature: Signature,
  /**
   * Result of output
   */
  var valueString: String? = null,
  /**
   * Result of output
   */
  var valueTime: String? = null,
  /**
   * Result of output
   */
  var valueTiming: Timing,
  /**
   * Result of output
   */
  var valueTriggerDefinition: TriggerDefinition,
  /**
   * Result of output
   */
  var valueUnsignedInt: Int? = null,
  /**
   * Result of output
   */
  var valueUri: String? = null,
  /**
   * Result of output
   */
  var valueUrl: String? = null,
  /**
   * Result of output
   */
  var valueUsageContext: UsageContext,
  /**
   * Result of output
   */
  var valueUuid: String? = null
) : BackboneElement
