//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.diagnosticreport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A Diagnostic report - a combination of request information, atomic results, images,
 * interpretation, as well as formatted reports
 *
 * The findings and interpretation of diagnostic  tests performed on patients, groups of patients,
 * devices, and locations, and/or specimens derived from these. The report includes clinical context
 * such as requesting and provider information, and some mix of atomic results, images, textual and
 * coded interpretations, and formatted representation of diagnostic reports.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class DiagnosticReport(
  var basedOn: List<Reference> = listOf(),
  var category: List<CodeableConcept> = listOf(),
  /**
   * Name/Code for this diagnostic report
   */
  var code: CodeableConcept,
  /**
   * Clinical conclusion (interpretation) of test results
   */
  var conclusion: String? = null,
  var conclusionCode: List<CodeableConcept> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Clinically relevant time/time-period for report
   */
  var effectiveDateTime: String? = null,
  /**
   * Clinically relevant time/time-period for report
   */
  var effectivePeriod: Period? = null,
  /**
   * Health care event when test ordered
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  var imagingStudy: List<Reference> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * DateTime this version was made
   */
  var issued: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var media: List<DiagnosticReportMedia> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var performer: List<Reference> = listOf(),
  var presentedForm: List<Attachment> = listOf(),
  var result: List<Reference> = listOf(),
  var resultsInterpreter: List<Reference> = listOf(),
  var specimen: List<Reference> = listOf(),
  /**
   * registered | partial | preliminary | final +
   */
  var status: String? = null,
  /**
   * The subject of the report - usually, but not always, the patient
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
