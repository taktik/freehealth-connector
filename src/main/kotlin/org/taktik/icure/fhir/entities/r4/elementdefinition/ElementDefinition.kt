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
 * Definition of an element in a resource or extension
 *
 * Captures constraints on each element within the resource, profile, or extension.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ElementDefinition(
  var alias: List<String> = listOf(),
  /**
   * Base definition information for tools
   */
  var base: ElementDefinitionBase? = null,
  /**
   * ValueSet details if this is coded
   */
  var binding: ElementDefinitionBinding? = null,
  var code: List<Coding> = listOf(),
  /**
   * Comments about the use of this element
   */
  var comment: String? = null,
  var condition: List<String> = listOf(),
  var constraint: List<ElementDefinitionConstraint> = listOf(),
  /**
   * Reference to definition of content for the element
   */
  var contentReference: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueAddress: Address? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueAge: Age? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueAnnotation: Annotation? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueAttachment: Attachment? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueBase64Binary: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueBoolean: Boolean? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueCanonical: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueCode: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueCodeableConcept: CodeableConcept? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueCoding: Coding? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueContactDetail: ContactDetail? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueContactPoint: ContactPoint? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueContributor: Contributor? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueCount: Count? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueDataRequirement: DataRequirement? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueDate: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueDateTime: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueDecimal: Float? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueDistance: Distance? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueDosage: Dosage? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueDuration: Duration? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueExpression: Expression? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueHumanName: HumanName? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueId: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueIdentifier: Identifier? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueInstant: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueInteger: Int? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueMarkdown: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueMeta: Meta? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueMoney: Money? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueOid: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueParameterDefinition: ParameterDefinition? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValuePeriod: Period? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValuePositiveInt: Int? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueQuantity: Quantity? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueRange: Range? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueRatio: Ratio? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueReference: Reference? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueRelatedArtifact: RelatedArtifact? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueSampledData: SampledData? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueSignature: Signature? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueString: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueTime: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueTiming: Timing? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueTriggerDefinition: TriggerDefinition? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueUnsignedInt: Int? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueUri: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueUrl: String? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueUsageContext: UsageContext? = null,
  /**
   * Specified value if missing from instance
   */
  var defaultValueUuid: String? = null,
  /**
   * Full formal definition as narrative text
   */
  var definition: String? = null,
  var example: List<ElementDefinitionExample> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Value must be exactly this
   */
  var fixedAddress: Address? = null,
  /**
   * Value must be exactly this
   */
  var fixedAge: Age? = null,
  /**
   * Value must be exactly this
   */
  var fixedAnnotation: Annotation? = null,
  /**
   * Value must be exactly this
   */
  var fixedAttachment: Attachment? = null,
  /**
   * Value must be exactly this
   */
  var fixedBase64Binary: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedBoolean: Boolean? = null,
  /**
   * Value must be exactly this
   */
  var fixedCanonical: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedCode: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedCodeableConcept: CodeableConcept? = null,
  /**
   * Value must be exactly this
   */
  var fixedCoding: Coding? = null,
  /**
   * Value must be exactly this
   */
  var fixedContactDetail: ContactDetail? = null,
  /**
   * Value must be exactly this
   */
  var fixedContactPoint: ContactPoint? = null,
  /**
   * Value must be exactly this
   */
  var fixedContributor: Contributor? = null,
  /**
   * Value must be exactly this
   */
  var fixedCount: Count? = null,
  /**
   * Value must be exactly this
   */
  var fixedDataRequirement: DataRequirement? = null,
  /**
   * Value must be exactly this
   */
  var fixedDate: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedDateTime: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedDecimal: Float? = null,
  /**
   * Value must be exactly this
   */
  var fixedDistance: Distance? = null,
  /**
   * Value must be exactly this
   */
  var fixedDosage: Dosage? = null,
  /**
   * Value must be exactly this
   */
  var fixedDuration: Duration? = null,
  /**
   * Value must be exactly this
   */
  var fixedExpression: Expression? = null,
  /**
   * Value must be exactly this
   */
  var fixedHumanName: HumanName? = null,
  /**
   * Value must be exactly this
   */
  var fixedId: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedIdentifier: Identifier? = null,
  /**
   * Value must be exactly this
   */
  var fixedInstant: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedInteger: Int? = null,
  /**
   * Value must be exactly this
   */
  var fixedMarkdown: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedMeta: Meta? = null,
  /**
   * Value must be exactly this
   */
  var fixedMoney: Money? = null,
  /**
   * Value must be exactly this
   */
  var fixedOid: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedParameterDefinition: ParameterDefinition? = null,
  /**
   * Value must be exactly this
   */
  var fixedPeriod: Period? = null,
  /**
   * Value must be exactly this
   */
  var fixedPositiveInt: Int? = null,
  /**
   * Value must be exactly this
   */
  var fixedQuantity: Quantity? = null,
  /**
   * Value must be exactly this
   */
  var fixedRange: Range? = null,
  /**
   * Value must be exactly this
   */
  var fixedRatio: Ratio? = null,
  /**
   * Value must be exactly this
   */
  var fixedReference: Reference? = null,
  /**
   * Value must be exactly this
   */
  var fixedRelatedArtifact: RelatedArtifact? = null,
  /**
   * Value must be exactly this
   */
  var fixedSampledData: SampledData? = null,
  /**
   * Value must be exactly this
   */
  var fixedSignature: Signature? = null,
  /**
   * Value must be exactly this
   */
  var fixedString: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedTime: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedTiming: Timing? = null,
  /**
   * Value must be exactly this
   */
  var fixedTriggerDefinition: TriggerDefinition? = null,
  /**
   * Value must be exactly this
   */
  var fixedUnsignedInt: Int? = null,
  /**
   * Value must be exactly this
   */
  var fixedUri: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedUrl: String? = null,
  /**
   * Value must be exactly this
   */
  var fixedUsageContext: UsageContext? = null,
  /**
   * Value must be exactly this
   */
  var fixedUuid: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * If this modifies the meaning of other elements
   */
  var isModifier: Boolean? = null,
  /**
   * Reason that this element is marked as a modifier
   */
  var isModifierReason: String? = null,
  /**
   * Include when _summary = true?
   */
  var isSummary: Boolean? = null,
  /**
   * Name for element to display with or prompt for element
   */
  var label: String? = null,
  var mapping: List<ElementDefinitionMapping> = listOf(),
  /**
   * Maximum Cardinality (a number or *)
   */
  var max: String? = null,
  /**
   * Max length for strings
   */
  var maxLength: Int? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueDate: String? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueDateTime: String? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueDecimal: Float? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueInstant: String? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueInteger: Int? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValuePositiveInt: Int? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueQuantity: Quantity? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueTime: String? = null,
  /**
   * Maximum Allowed Value (for some types)
   */
  var maxValueUnsignedInt: Int? = null,
  /**
   * Implicit meaning when this element is missing
   */
  var meaningWhenMissing: String? = null,
  /**
   * Minimum Cardinality
   */
  var min: Int? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueDate: String? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueDateTime: String? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueDecimal: Float? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueInstant: String? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueInteger: Int? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValuePositiveInt: Int? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueQuantity: Quantity? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueTime: String? = null,
  /**
   * Minimum Allowed Value (for some types)
   */
  var minValueUnsignedInt: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * If the element must be supported
   */
  var mustSupport: Boolean? = null,
  /**
   * What the order of the elements means
   */
  var orderMeaning: String? = null,
  /**
   * Path of the element in the hierarchy of elements
   */
  var path: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternAddress: Address? = null,
  /**
   * Value must have at least these property values
   */
  var patternAge: Age? = null,
  /**
   * Value must have at least these property values
   */
  var patternAnnotation: Annotation? = null,
  /**
   * Value must have at least these property values
   */
  var patternAttachment: Attachment? = null,
  /**
   * Value must have at least these property values
   */
  var patternBase64Binary: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternBoolean: Boolean? = null,
  /**
   * Value must have at least these property values
   */
  var patternCanonical: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternCode: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternCodeableConcept: CodeableConcept? = null,
  /**
   * Value must have at least these property values
   */
  var patternCoding: Coding? = null,
  /**
   * Value must have at least these property values
   */
  var patternContactDetail: ContactDetail? = null,
  /**
   * Value must have at least these property values
   */
  var patternContactPoint: ContactPoint? = null,
  /**
   * Value must have at least these property values
   */
  var patternContributor: Contributor? = null,
  /**
   * Value must have at least these property values
   */
  var patternCount: Count? = null,
  /**
   * Value must have at least these property values
   */
  var patternDataRequirement: DataRequirement? = null,
  /**
   * Value must have at least these property values
   */
  var patternDate: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternDateTime: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternDecimal: Float? = null,
  /**
   * Value must have at least these property values
   */
  var patternDistance: Distance? = null,
  /**
   * Value must have at least these property values
   */
  var patternDosage: Dosage? = null,
  /**
   * Value must have at least these property values
   */
  var patternDuration: Duration? = null,
  /**
   * Value must have at least these property values
   */
  var patternExpression: Expression? = null,
  /**
   * Value must have at least these property values
   */
  var patternHumanName: HumanName? = null,
  /**
   * Value must have at least these property values
   */
  var patternId: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternIdentifier: Identifier? = null,
  /**
   * Value must have at least these property values
   */
  var patternInstant: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternInteger: Int? = null,
  /**
   * Value must have at least these property values
   */
  var patternMarkdown: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternMeta: Meta? = null,
  /**
   * Value must have at least these property values
   */
  var patternMoney: Money? = null,
  /**
   * Value must have at least these property values
   */
  var patternOid: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternParameterDefinition: ParameterDefinition? = null,
  /**
   * Value must have at least these property values
   */
  var patternPeriod: Period? = null,
  /**
   * Value must have at least these property values
   */
  var patternPositiveInt: Int? = null,
  /**
   * Value must have at least these property values
   */
  var patternQuantity: Quantity? = null,
  /**
   * Value must have at least these property values
   */
  var patternRange: Range? = null,
  /**
   * Value must have at least these property values
   */
  var patternRatio: Ratio? = null,
  /**
   * Value must have at least these property values
   */
  var patternReference: Reference? = null,
  /**
   * Value must have at least these property values
   */
  var patternRelatedArtifact: RelatedArtifact? = null,
  /**
   * Value must have at least these property values
   */
  var patternSampledData: SampledData? = null,
  /**
   * Value must have at least these property values
   */
  var patternSignature: Signature? = null,
  /**
   * Value must have at least these property values
   */
  var patternString: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternTime: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternTiming: Timing? = null,
  /**
   * Value must have at least these property values
   */
  var patternTriggerDefinition: TriggerDefinition? = null,
  /**
   * Value must have at least these property values
   */
  var patternUnsignedInt: Int? = null,
  /**
   * Value must have at least these property values
   */
  var patternUri: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternUrl: String? = null,
  /**
   * Value must have at least these property values
   */
  var patternUsageContext: UsageContext? = null,
  /**
   * Value must have at least these property values
   */
  var patternUuid: String? = null,
  var representation: List<String> = listOf(),
  /**
   * Why this resource has been created
   */
  var requirements: String? = null,
  /**
   * Concise definition for space-constrained presentation
   */
  var short: String? = null,
  /**
   * If this slice definition constrains an inherited slice definition (or not)
   */
  var sliceIsConstraining: Boolean? = null,
  /**
   * Name for this particular element (in a set of slices)
   */
  var sliceName: String? = null,
  /**
   * This element is sliced - slices follow
   */
  var slicing: ElementDefinitionSlicing? = null,
  var type: List<ElementDefinitionType> = listOf()
) : BackboneElement
