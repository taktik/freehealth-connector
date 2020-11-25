//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.claimresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Note concerning adjudication
 *
 * A note that describes or explains adjudication results in a human readable form.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimResponseProcessNote(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Language of the text
   */
  var language: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Note instance identifier
   */
  var number: Int? = null,
  /**
   * Note explanatory text
   */
  var text: String? = null,
  /**
   * display | print | printoper
   */
  var type: String? = null
) : BackboneElement
