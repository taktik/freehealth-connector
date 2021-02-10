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
 * Processing errors
 *
 * Errors encountered during the processing of the adjudication.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ClaimResponseError(
  /**
   * Error code detailing processing issues
   */
  var code: CodeableConcept,
  /**
   * Detail sequence number
   */
  var detailSequence: Int? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Item sequence number
   */
  var itemSequence: Int? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Subdetail sequence number
   */
  var subDetailSequence: Int? = null
) : BackboneElement
