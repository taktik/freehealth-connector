//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproduct

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
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.marketingstatus.MarketingStatus
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Detailed definition of a medicinal product, typically for uses other than direct patient care
 * (e.g. regulatory use)
 *
 * Detailed definition of a medicinal product, typically for uses other than direct patient care
 * (e.g. regulatory use).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class MedicinalProduct(
  /**
   * Whether the Medicinal Product is subject to additional monitoring for regulatory reasons
   */
  var additionalMonitoringIndicator: CodeableConcept? = null,
  var attachedDocument: List<Reference> = listOf(),
  var clinicalTrial: List<Reference> = listOf(),
  /**
   * The dose form for a single part product, or combined form of a multiple part product
   */
  var combinedPharmaceuticalDoseForm: CodeableConcept? = null,
  var contact: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  var crossReference: List<Identifier> = listOf(),
  /**
   * If this medicine applies to human or veterinary uses
   */
  var domain: Coding? = null,
  override var extension: List<Extension> = listOf(),
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
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * The legal status of supply of the medicinal product as classified by the regulator
   */
  var legalStatusOfSupply: CodeableConcept? = null,
  var manufacturingBusinessOperation: List<MedicinalProductManufacturingBusinessOperation> =
      listOf(),
  var marketingStatus: List<MarketingStatus> = listOf(),
  var masterFile: List<Reference> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var name: List<MedicinalProductName> = listOf(),
  var packagedMedicinalProduct: List<Reference> = listOf(),
  /**
   * If authorised for use in children
   */
  var paediatricUseIndicator: CodeableConcept? = null,
  var pharmaceuticalProduct: List<Reference> = listOf(),
  var productClassification: List<CodeableConcept> = listOf(),
  var specialDesignation: List<MedicinalProductSpecialDesignation> = listOf(),
  var specialMeasures: List<String> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Regulatory type, e.g. Investigational or Authorized
   */
  var type: CodeableConcept? = null
) : DomainResource
