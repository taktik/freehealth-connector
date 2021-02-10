//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.procedure

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * The people who performed the procedure
 *
 * Limited to "real" people rather than equipment.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ProcedurePerformer(
  /**
   * The reference to the practitioner
   */
  var actor: Reference,
  override var extension: List<Extension> = listOf(),
  /**
   * Type of performance
   */
  var function: CodeableConcept? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Organization the device or practitioner was acting for
   */
  var onBehalfOf: Reference? = null
) : BackboneElement
