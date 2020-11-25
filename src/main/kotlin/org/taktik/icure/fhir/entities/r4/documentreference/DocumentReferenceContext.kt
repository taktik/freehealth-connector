//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.documentreference

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Clinical context of document
 *
 * The clinical context in which the document was prepared.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DocumentReferenceContext(
  var encounter: List<Reference> = listOf(),
  var event: List<CodeableConcept> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Kind of facility where patient was seen
   */
  var facilityType: CodeableConcept? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Time of service that is being documented
   */
  var period: Period? = null,
  /**
   * Additional details about where the content was created (e.g. clinical specialty)
   */
  var practiceSetting: CodeableConcept? = null,
  var related: List<Reference> = listOf(),
  /**
   * Patient demographics from source
   */
  var sourcePatientInfo: Reference? = null
) : BackboneElement
