//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testreport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * The operation to perform
 *
 * The operation performed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TestReportSetupActionOperation(
  /**
   * A link to further details on the result
   */
  var detail: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * A message associated with the result
   */
  var message: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * pass | skip | fail | warning | error
   */
  var result: String? = null
) : BackboneElement
