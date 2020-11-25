//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.immunization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Educational material presented to patient
 *
 * Educational material presented to the patient (or guardian) at the time of vaccine
 * administration.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImmunizationEducation(
  /**
   * Educational material document identifier
   */
  var documentType: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Educational material presentation date
   */
  var presentationDate: String? = null,
  /**
   * Educational material publication date
   */
  var publicationDate: String? = null,
  /**
   * Educational material reference pointer
   */
  var reference: String? = null
) : BackboneElement
