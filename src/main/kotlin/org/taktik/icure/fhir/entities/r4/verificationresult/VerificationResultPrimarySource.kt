//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.verificationresult

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Information about the primary source(s) involved in validation
 *
 * Information about the primary source(s) involved in validation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class VerificationResultPrimarySource(
  /**
   * yes | no | undetermined
   */
  var canPushUpdates: CodeableConcept? = null,
  var communicationMethod: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var pushTypeAvailable: List<CodeableConcept> = listOf(),
  var type: List<CodeableConcept> = listOf(),
  /**
   * When the target was validated against the primary source
   */
  var validationDate: String? = null,
  /**
   * successful | failed | unknown
   */
  var validationStatus: CodeableConcept? = null,
  /**
   * Reference to the primary source
   */
  var who: Reference? = null
) : BackboneElement
