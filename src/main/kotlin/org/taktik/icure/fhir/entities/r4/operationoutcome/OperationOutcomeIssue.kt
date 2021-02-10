//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.operationoutcome

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * A single issue associated with the action
 *
 * An error, warning, or information message that results from a system action.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class OperationOutcomeIssue(
  /**
   * Error or warning code
   */
  var code: String? = null,
  /**
   * Additional details about the error
   */
  var details: CodeableConcept? = null,
  /**
   * Additional diagnostic information about the issue
   */
  var diagnostics: String? = null,
  var expression: List<String> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var location: List<String> = listOf(),
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * fatal | error | warning | information
   */
  var severity: String? = null
) : BackboneElement
