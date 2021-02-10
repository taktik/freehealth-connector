//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claim

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.address.Address
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Details of the event
 *
 * Details of an accident which resulted in injuries which required the products and services listed
 * in the claim.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimAccident(
  /**
   * When the incident occurred
   */
  var date: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Where the event occurred
   */
  var locationAddress: Address? = null,
  /**
   * Where the event occurred
   */
  var locationReference: Reference? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The nature of the accident
   */
  var type: CodeableConcept? = null
) : BackboneElement
