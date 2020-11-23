//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.extension

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
 * Optional Extensions Element
 *
 * Optional Extension Element - found in all resources.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Extension(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * identifies the meaning of the extension
   */
  var url: String? = null,
  /**
   * Value of extension
   */
  var valueAddress: Address? = null,
  /**
   * Value of extension
   */
  var valueAge: Age? = null,
  /**
   * Value of extension
   */
  var valueAnnotation: Annotation? = null,
  /**
   * Value of extension
   */
  var valueAttachment: Attachment? = null,
  /**
   * Value of extension
   */
  var valueBase64Binary: String? = null,
  /**
   * Value of extension
   */
  var valueBoolean: Boolean? = null,
  /**
   * Value of extension
   */
  var valueCanonical: String? = null,
  /**
   * Value of extension
   */
  var valueCode: String? = null,
  /**
   * Value of extension
   */
  var valueCodeableConcept: CodeableConcept? = null,
  /**
   * Value of extension
   */
  var valueCoding: Coding? = null,
  /**
   * Value of extension
   */
  var valueContactDetail: ContactDetail? = null,
  /**
   * Value of extension
   */
  var valueContactPoint: ContactPoint? = null,
  /**
   * Value of extension
   */
  var valueContributor: Contributor? = null,
  /**
   * Value of extension
   */
  var valueCount: Count? = null,
  /**
   * Value of extension
   */
  var valueDataRequirement: DataRequirement? = null,
  /**
   * Value of extension
   */
  var valueDate: String? = null,
  /**
   * Value of extension
   */
  var valueDateTime: String? = null,
  /**
   * Value of extension
   */
  var valueDecimal: Float? = null,
  /**
   * Value of extension
   */
  var valueDistance: Distance? = null,
  /**
   * Value of extension
   */
  var valueDosage: Dosage? = null,
  /**
   * Value of extension
   */
  var valueDuration: Duration? = null,
  /**
   * Value of extension
   */
  var valueExpression: Expression? = null,
  /**
   * Value of extension
   */
  var valueHumanName: HumanName? = null,
  /**
   * Value of extension
   */
  var valueId: String? = null,
  /**
   * Value of extension
   */
  var valueIdentifier: Identifier? = null,
  /**
   * Value of extension
   */
  var valueInstant: String? = null,
  /**
   * Value of extension
   */
  var valueInteger: Int? = null,
  /**
   * Value of extension
   */
  var valueMarkdown: String? = null,
  /**
   * Value of extension
   */
  var valueMeta: Meta? = null,
  /**
   * Value of extension
   */
  var valueMoney: Money? = null,
  /**
   * Value of extension
   */
  var valueOid: String? = null,
  /**
   * Value of extension
   */
  var valueParameterDefinition: ParameterDefinition? = null,
  /**
   * Value of extension
   */
  var valuePeriod: Period? = null,
  /**
   * Value of extension
   */
  var valuePositiveInt: Int? = null,
  /**
   * Value of extension
   */
  var valueQuantity: Quantity? = null,
  /**
   * Value of extension
   */
  var valueRange: Range? = null,
  /**
   * Value of extension
   */
  var valueRatio: Ratio? = null,
  /**
   * Value of extension
   */
  var valueReference: Reference? = null,
  /**
   * Value of extension
   */
  var valueRelatedArtifact: RelatedArtifact? = null,
  /**
   * Value of extension
   */
  var valueSampledData: SampledData? = null,
  /**
   * Value of extension
   */
  var valueSignature: Signature? = null,
  /**
   * Value of extension
   */
  var valueString: String? = null,
  /**
   * Value of extension
   */
  var valueTime: String? = null,
  /**
   * Value of extension
   */
  var valueTiming: Timing? = null,
  /**
   * Value of extension
   */
  var valueTriggerDefinition: TriggerDefinition? = null,
  /**
   * Value of extension
   */
  var valueUnsignedInt: Int? = null,
  /**
   * Value of extension
   */
  var valueUri: String? = null,
  /**
   * Value of extension
   */
  var valueUrl: String? = null,
  /**
   * Value of extension
   */
  var valueUsageContext: UsageContext? = null,
  /**
   * Value of extension
   */
  var valueUuid: String? = null
) : Element
