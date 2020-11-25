//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.diagnosticreport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Key images associated with this report
 *
 * A list of key images associated with this report. The images are generally created during the
 * diagnostic process, and may be directly of the patient, or of treated specimens (i.e. slides of
 * interest).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DiagnosticReportMedia(
  /**
   * Comment about the image (e.g. explanation)
   */
  var comment: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Reference to the image source
   */
  var link: Reference,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
