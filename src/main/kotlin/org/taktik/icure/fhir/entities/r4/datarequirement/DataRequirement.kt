//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.datarequirement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Describes a required data item
 *
 * Describes a required data item for evaluation in terms of the type of data, and optional code or
 * date-based filters of the data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DataRequirement(
  var codeFilter: List<DataRequirementCodeFilter> = listOf(),
  var dateFilter: List<DataRequirementDateFilter> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Number of results
   */
  var limit: Int? = null,
  var mustSupport: List<String> = listOf(),
  var profile: List<String> = listOf(),
  var sort: List<DataRequirementSort> = listOf(),
  /**
   * E.g. Patient, Practitioner, RelatedPerson, Organization, Location, Device
   */
  var subjectCodeableConcept: CodeableConcept? = null,
  /**
   * E.g. Patient, Practitioner, RelatedPerson, Organization, Location, Device
   */
  var subjectReference: Reference? = null,
  /**
   * The type of the required data
   */
  var type: String? = null
) : Element
