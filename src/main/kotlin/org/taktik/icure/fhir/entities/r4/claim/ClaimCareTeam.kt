//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claim

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Members of the care team
 *
 * The members of the team who provided the products and services.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimCareTeam(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Practitioner or organization
   */
  var provider: Reference,
  /**
   * Practitioner credential or specialization
   */
  var qualification: CodeableConcept? = null,
  /**
   * Indicator of the lead practitioner
   */
  var responsible: Boolean? = null,
  /**
   * Function within the team
   */
  var role: CodeableConcept? = null,
  /**
   * Order of care team
   */
  var sequence: Int? = null
) : BackboneElement
