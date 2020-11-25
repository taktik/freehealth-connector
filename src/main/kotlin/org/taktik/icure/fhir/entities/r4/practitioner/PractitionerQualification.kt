//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.practitioner

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Certification, licenses, or training pertaining to the provision of care
 *
 * The official certifications, training, and licenses that authorize or otherwise pertain to the
 * provision of care by the practitioner.  For example, a medical license issued by a medical board
 * authorizing the practitioner to practice medicine within a certian locality.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PractitionerQualification(
  /**
   * Coded representation of the qualification
   */
  var code: CodeableConcept,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * Organization that regulates and issues the qualification
   */
  var issuer: Reference? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Period during which the qualification is valid
   */
  var period: Period? = null
) : BackboneElement
