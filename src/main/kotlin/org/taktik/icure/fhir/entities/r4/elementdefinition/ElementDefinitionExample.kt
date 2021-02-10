//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.elementdefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.address.Address
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
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
 * Example value (as defined for type)
 *
 * A sample value for this element demonstrating the type of information that would typically be
 * found in the element.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinitionExample(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Describes the purpose of this example
   */
  var label: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueAddress: Address,
  /**
   * Value of Example (one of allowed types)
   */
  var valueAge: Age,
  /**
   * Value of Example (one of allowed types)
   */
  var valueAnnotation: Annotation,
  /**
   * Value of Example (one of allowed types)
   */
  var valueAttachment: Attachment,
  /**
   * Value of Example (one of allowed types)
   */
  var valueBase64Binary: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueBoolean: Boolean? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueCanonical: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueCode: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueCodeableConcept: CodeableConcept,
  /**
   * Value of Example (one of allowed types)
   */
  var valueCoding: Coding,
  /**
   * Value of Example (one of allowed types)
   */
  var valueContactDetail: ContactDetail,
  /**
   * Value of Example (one of allowed types)
   */
  var valueContactPoint: ContactPoint,
  /**
   * Value of Example (one of allowed types)
   */
  var valueContributor: Contributor,
  /**
   * Value of Example (one of allowed types)
   */
  var valueCount: Count,
  /**
   * Value of Example (one of allowed types)
   */
  var valueDataRequirement: DataRequirement,
  /**
   * Value of Example (one of allowed types)
   */
  var valueDate: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueDateTime: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueDecimal: Float? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueDistance: Distance,
  /**
   * Value of Example (one of allowed types)
   */
  var valueDosage: Dosage,
  /**
   * Value of Example (one of allowed types)
   */
  var valueDuration: Duration,
  /**
   * Value of Example (one of allowed types)
   */
  var valueExpression: Expression,
  /**
   * Value of Example (one of allowed types)
   */
  var valueHumanName: HumanName,
  /**
   * Value of Example (one of allowed types)
   */
  var valueId: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueIdentifier: Identifier,
  /**
   * Value of Example (one of allowed types)
   */
  var valueInstant: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueInteger: Int? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueMarkdown: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueMeta: Meta,
  /**
   * Value of Example (one of allowed types)
   */
  var valueMoney: Money,
  /**
   * Value of Example (one of allowed types)
   */
  var valueOid: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueParameterDefinition: ParameterDefinition,
  /**
   * Value of Example (one of allowed types)
   */
  var valuePeriod: Period,
  /**
   * Value of Example (one of allowed types)
   */
  var valuePositiveInt: Int? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueQuantity: Quantity,
  /**
   * Value of Example (one of allowed types)
   */
  var valueRange: Range,
  /**
   * Value of Example (one of allowed types)
   */
  var valueRatio: Ratio,
  /**
   * Value of Example (one of allowed types)
   */
  var valueReference: Reference,
  /**
   * Value of Example (one of allowed types)
   */
  var valueRelatedArtifact: RelatedArtifact,
  /**
   * Value of Example (one of allowed types)
   */
  var valueSampledData: SampledData,
  /**
   * Value of Example (one of allowed types)
   */
  var valueSignature: Signature,
  /**
   * Value of Example (one of allowed types)
   */
  var valueString: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueTime: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueTiming: Timing,
  /**
   * Value of Example (one of allowed types)
   */
  var valueTriggerDefinition: TriggerDefinition,
  /**
   * Value of Example (one of allowed types)
   */
  var valueUnsignedInt: Int? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueUri: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueUrl: String? = null,
  /**
   * Value of Example (one of allowed types)
   */
  var valueUsageContext: UsageContext,
  /**
   * Value of Example (one of allowed types)
   */
  var valueUuid: String? = null
) : Element
