//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.medicinalproductauthorization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * Authorization in areas within a country
 *
 * Authorization in areas within a country.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductAuthorizationJurisdictionalAuthorization(
  /**
   * Country of authorization
   */
  var country: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * The legal status of supply in a jurisdiction or region
   */
  var legalStatusOfSupply: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The start and expected end date of the authorization
   */
  var validityPeriod: Period? = null
) : BackboneElement
