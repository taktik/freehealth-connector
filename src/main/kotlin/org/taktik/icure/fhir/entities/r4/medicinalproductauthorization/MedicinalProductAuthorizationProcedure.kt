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
 * The regulatory procedure for granting or amending a marketing authorization
 *
 * The regulatory procedure for granting or amending a marketing authorization.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MedicinalProductAuthorizationProcedure(
  var application: List<MedicinalProductAuthorizationProcedure> = listOf(),
  /**
   * Date of procedure
   */
  var dateDateTime: String? = null,
  /**
   * Date of procedure
   */
  var datePeriod: Period? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Identifier for this procedure
   */
  var identifier: Identifier? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Type of procedure
   */
  var type: CodeableConcept
) : BackboneElement
