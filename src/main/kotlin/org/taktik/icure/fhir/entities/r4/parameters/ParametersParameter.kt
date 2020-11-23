//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.parameters

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
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
 * Operation Parameter
 *
 * A parameter passed to or received from the operation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ParametersParameter(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name from the definition
   */
  var name: String? = null,
  var part: List<ParametersParameter> = listOf(),
  /**
   * If parameter is a whole resource
   */
  var resource: Resource? = null,
  /**
   * If parameter is a data type
   */
  var valueAddress: Address? = null,
  /**
   * If parameter is a data type
   */
  var valueAge: Age? = null,
  /**
   * If parameter is a data type
   */
  var valueAnnotation: Annotation? = null,
  /**
   * If parameter is a data type
   */
  var valueAttachment: Attachment? = null,
  /**
   * If parameter is a data type
   */
  var valueBase64Binary: String? = null,
  /**
   * If parameter is a data type
   */
  var valueBoolean: Boolean? = null,
  /**
   * If parameter is a data type
   */
  var valueCanonical: String? = null,
  /**
   * If parameter is a data type
   */
  var valueCode: String? = null,
  /**
   * If parameter is a data type
   */
  var valueCodeableConcept: CodeableConcept? = null,
  /**
   * If parameter is a data type
   */
  var valueCoding: Coding? = null,
  /**
   * If parameter is a data type
   */
  var valueContactDetail: ContactDetail? = null,
  /**
   * If parameter is a data type
   */
  var valueContactPoint: ContactPoint? = null,
  /**
   * If parameter is a data type
   */
  var valueContributor: Contributor? = null,
  /**
   * If parameter is a data type
   */
  var valueCount: Count? = null,
  /**
   * If parameter is a data type
   */
  var valueDataRequirement: DataRequirement? = null,
  /**
   * If parameter is a data type
   */
  var valueDate: String? = null,
  /**
   * If parameter is a data type
   */
  var valueDateTime: String? = null,
  /**
   * If parameter is a data type
   */
  var valueDecimal: Float? = null,
  /**
   * If parameter is a data type
   */
  var valueDistance: Distance? = null,
  /**
   * If parameter is a data type
   */
  var valueDosage: Dosage? = null,
  /**
   * If parameter is a data type
   */
  var valueDuration: Duration? = null,
  /**
   * If parameter is a data type
   */
  var valueExpression: Expression? = null,
  /**
   * If parameter is a data type
   */
  var valueHumanName: HumanName? = null,
  /**
   * If parameter is a data type
   */
  var valueId: String? = null,
  /**
   * If parameter is a data type
   */
  var valueIdentifier: Identifier? = null,
  /**
   * If parameter is a data type
   */
  var valueInstant: String? = null,
  /**
   * If parameter is a data type
   */
  var valueInteger: Int? = null,
  /**
   * If parameter is a data type
   */
  var valueMarkdown: String? = null,
  /**
   * If parameter is a data type
   */
  var valueMeta: Meta? = null,
  /**
   * If parameter is a data type
   */
  var valueMoney: Money? = null,
  /**
   * If parameter is a data type
   */
  var valueOid: String? = null,
  /**
   * If parameter is a data type
   */
  var valueParameterDefinition: ParameterDefinition? = null,
  /**
   * If parameter is a data type
   */
  var valuePeriod: Period? = null,
  /**
   * If parameter is a data type
   */
  var valuePositiveInt: Int? = null,
  /**
   * If parameter is a data type
   */
  var valueQuantity: Quantity? = null,
  /**
   * If parameter is a data type
   */
  var valueRange: Range? = null,
  /**
   * If parameter is a data type
   */
  var valueRatio: Ratio? = null,
  /**
   * If parameter is a data type
   */
  var valueReference: Reference? = null,
  /**
   * If parameter is a data type
   */
  var valueRelatedArtifact: RelatedArtifact? = null,
  /**
   * If parameter is a data type
   */
  var valueSampledData: SampledData? = null,
  /**
   * If parameter is a data type
   */
  var valueSignature: Signature? = null,
  /**
   * If parameter is a data type
   */
  var valueString: String? = null,
  /**
   * If parameter is a data type
   */
  var valueTime: String? = null,
  /**
   * If parameter is a data type
   */
  var valueTiming: Timing? = null,
  /**
   * If parameter is a data type
   */
  var valueTriggerDefinition: TriggerDefinition? = null,
  /**
   * If parameter is a data type
   */
  var valueUnsignedInt: Int? = null,
  /**
   * If parameter is a data type
   */
  var valueUri: String? = null,
  /**
   * If parameter is a data type
   */
  var valueUrl: String? = null,
  /**
   * If parameter is a data type
   */
  var valueUsageContext: UsageContext? = null,
  /**
   * If parameter is a data type
   */
  var valueUuid: String? = null
) : BackboneElement
