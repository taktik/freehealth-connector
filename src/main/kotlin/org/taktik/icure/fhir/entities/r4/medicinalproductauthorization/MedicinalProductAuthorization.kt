//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductauthorization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The regulatory authorization of a medicinal product
 *
 * The regulatory authorization of a medicinal product.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicinalProductAuthorization(
  override var contained: List<Resource> = listOf(),
  var country: List<CodeableConcept> = listOf(),
  /**
   * A period of time after authorization before generic product applicatiosn can be submitted
   */
  var dataExclusivityPeriod: Period? = null,
  /**
   * The date when the first authorization was granted by a Medicines Regulatory Agency
   */
  var dateOfFirstAuthorization: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Marketing Authorization Holder
   */
  var holder: Reference? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Date of first marketing authorization for a company's new medicinal product in any country in
   * the World
   */
  var internationalBirthDate: String? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  var jurisdictionalAuthorization: List<MedicinalProductAuthorizationJurisdictionalAuthorization> =
      listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * The legal framework against which this authorization is granted
   */
  var legalBasis: CodeableConcept? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The regulatory procedure for granting or amending a marketing authorization
   */
  var procedure: MedicinalProductAuthorizationProcedure? = null,
  /**
   * Medicines Regulatory Agency
   */
  var regulator: Reference? = null,
  /**
   * The date when a suspended the marketing or the marketing authorization of the product is
   * anticipated to be restored
   */
  var restoreDate: String? = null,
  /**
   * The status of the marketing authorization
   */
  var status: CodeableConcept? = null,
  /**
   * The date at which the given status has become applicable
   */
  var statusDate: String? = null,
  /**
   * The medicinal product that is being authorized
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * The beginning of the time period in which the marketing authorization is in the specific status
   * shall be specified A complete date consisting of day, month and year shall be specified using the
   * ISO 8601 date format
   */
  var validityPeriod: Period? = null
) : DomainResource
