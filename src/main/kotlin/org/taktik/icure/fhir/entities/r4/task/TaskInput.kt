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
 * Information used to perform task
 *
 * Additional information that may be needed in the execution of the task.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TaskInput(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Label for the input
   */
  var type: CodeableConcept,
  /**
   * Content to use in performing the task
   */
  var valueAddress: Address,
  /**
   * Content to use in performing the task
   */
  var valueAge: Age,
  /**
   * Content to use in performing the task
   */
  var valueAnnotation: Annotation,
  /**
   * Content to use in performing the task
   */
  var valueAttachment: Attachment,
  /**
   * Content to use in performing the task
   */
  var valueBase64Binary: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueBoolean: Boolean? = null,
  /**
   * Content to use in performing the task
   */
  var valueCanonical: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueCode: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueCodeableConcept: CodeableConcept,
  /**
   * Content to use in performing the task
   */
  var valueCoding: Coding,
  /**
   * Content to use in performing the task
   */
  var valueContactDetail: ContactDetail,
  /**
   * Content to use in performing the task
   */
  var valueContactPoint: ContactPoint,
  /**
   * Content to use in performing the task
   */
  var valueContributor: Contributor,
  /**
   * Content to use in performing the task
   */
  var valueCount: Count,
  /**
   * Content to use in performing the task
   */
  var valueDataRequirement: DataRequirement,
  /**
   * Content to use in performing the task
   */
  var valueDate: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueDateTime: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueDecimal: Float? = null,
  /**
   * Content to use in performing the task
   */
  var valueDistance: Distance,
  /**
   * Content to use in performing the task
   */
  var valueDosage: Dosage,
  /**
   * Content to use in performing the task
   */
  var valueDuration: Duration,
  /**
   * Content to use in performing the task
   */
  var valueExpression: Expression,
  /**
   * Content to use in performing the task
   */
  var valueHumanName: HumanName,
  /**
   * Content to use in performing the task
   */
  var valueId: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueIdentifier: Identifier,
  /**
   * Content to use in performing the task
   */
  var valueInstant: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueInteger: Int? = null,
  /**
   * Content to use in performing the task
   */
  var valueMarkdown: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueMeta: Meta,
  /**
   * Content to use in performing the task
   */
  var valueMoney: Money,
  /**
   * Content to use in performing the task
   */
  var valueOid: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueParameterDefinition: ParameterDefinition,
  /**
   * Content to use in performing the task
   */
  var valuePeriod: Period,
  /**
   * Content to use in performing the task
   */
  var valuePositiveInt: Int? = null,
  /**
   * Content to use in performing the task
   */
  var valueQuantity: Quantity,
  /**
   * Content to use in performing the task
   */
  var valueRange: Range,
  /**
   * Content to use in performing the task
   */
  var valueRatio: Ratio,
  /**
   * Content to use in performing the task
   */
  var valueReference: Reference,
  /**
   * Content to use in performing the task
   */
  var valueRelatedArtifact: RelatedArtifact,
  /**
   * Content to use in performing the task
   */
  var valueSampledData: SampledData,
  /**
   * Content to use in performing the task
   */
  var valueSignature: Signature,
  /**
   * Content to use in performing the task
   */
  var valueString: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueTime: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueTiming: Timing,
  /**
   * Content to use in performing the task
   */
  var valueTriggerDefinition: TriggerDefinition,
  /**
   * Content to use in performing the task
   */
  var valueUnsignedInt: Int? = null,
  /**
   * Content to use in performing the task
   */
  var valueUri: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueUrl: String? = null,
  /**
   * Content to use in performing the task
   */
  var valueUsageContext: UsageContext,
  /**
   * Content to use in performing the task
   */
  var valueUuid: String? = null
) : BackboneElement
