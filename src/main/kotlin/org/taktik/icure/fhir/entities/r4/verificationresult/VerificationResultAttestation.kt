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
import org.taktik.icure.fhir.entities.r4.signature.Signature

/**
 * Information about the entity attesting to information
 *
 * Information about the entity attesting to information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class VerificationResultAttestation(
  /**
   * The method by which attested information was submitted/retrieved
   */
  var communicationMethod: CodeableConcept? = null,
  /**
   * The date the information was attested to
   */
  var date: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * When the who is asserting on behalf of another (organization or individual)
   */
  var onBehalfOf: Reference? = null,
  /**
   * A digital identity certificate associated with the proxy entity submitting attested information
   * on behalf of the attestation source
   */
  var proxyIdentityCertificate: String? = null,
  /**
   * Proxy signature
   */
  var proxySignature: Signature? = null,
  /**
   * A digital identity certificate associated with the attestation source
   */
  var sourceIdentityCertificate: String? = null,
  /**
   * Attester signature
   */
  var sourceSignature: Signature? = null,
  /**
   * The individual or organization attesting to information
   */
  var who: Reference? = null
) : BackboneElement
