//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.structuremap

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
 * Source inputs to the mapping
 *
 * Source inputs to the mapping.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class StructureMapGroupRuleSource(
  /**
   * FHIRPath expression  - must be true or the mapping engine throws an error instead of completing
   */
  var check: String? = null,
  /**
   * FHIRPath expression  - must be true or the rule does not apply
   */
  var condition: String? = null,
  /**
   * Type or variable this rule applies to
   */
  var context: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueAddress: Address? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueAge: Age? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueAnnotation: Annotation? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueAttachment: Attachment? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueBase64Binary: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueBoolean: Boolean? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueCanonical: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueCode: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueCodeableConcept: CodeableConcept? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueCoding: Coding? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueContactDetail: ContactDetail? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueContactPoint: ContactPoint? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueContributor: Contributor? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueCount: Count? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueDataRequirement: DataRequirement? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueDate: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueDateTime: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueDecimal: Float? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueDistance: Distance? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueDosage: Dosage? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueDuration: Duration? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueExpression: Expression? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueHumanName: HumanName? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueId: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueIdentifier: Identifier? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueInstant: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueInteger: Int? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueMarkdown: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueMeta: Meta? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueMoney: Money? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueOid: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueParameterDefinition: ParameterDefinition? = null,
  /**
   * Default value if no value exists
   */
  var defaultValuePeriod: Period? = null,
  /**
   * Default value if no value exists
   */
  var defaultValuePositiveInt: Int? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueQuantity: Quantity? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueRange: Range? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueRatio: Ratio? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueReference: Reference? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueRelatedArtifact: RelatedArtifact? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueSampledData: SampledData? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueSignature: Signature? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueString: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueTime: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueTiming: Timing? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueTriggerDefinition: TriggerDefinition? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueUnsignedInt: Int? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueUri: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueUrl: String? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueUsageContext: UsageContext? = null,
  /**
   * Default value if no value exists
   */
  var defaultValueUuid: String? = null,
  /**
   * Optional field for this source
   */
  var element: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * first | not_first | last | not_last | only_one
   */
  var listMode: String? = null,
  /**
   * Message to put in log if source exists (FHIRPath)
   */
  var logMessage: String? = null,
  /**
   * Specified maximum cardinality (number or *)
   */
  var max: String? = null,
  /**
   * Specified minimum cardinality
   */
  var min: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Rule only applies if source has this type
   */
  var type: String? = null,
  /**
   * Named context for field, if a field is specified
   */
  var variable: String? = null
) : BackboneElement
