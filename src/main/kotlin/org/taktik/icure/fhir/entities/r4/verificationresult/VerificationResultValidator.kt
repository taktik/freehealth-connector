//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.verificationresult

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.signature.Signature

/**
 * Information about the entity validating information
 *
 * Information about the entity validating information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class VerificationResultValidator(
  /**
   * Validator signature
   */
  var attestationSignature: Signature? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * A digital identity certificate associated with the validator
   */
  var identityCertificate: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Reference to the organization validating information
   */
  var organization: Reference
) : BackboneElement
