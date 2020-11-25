//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.datarequirement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period

/**
 * What dates/date ranges are expected
 *
 * Date filters specify additional constraints on the data in terms of the applicable date range for
 * specific elements. Each date filter specifies an additional constraint on the data, i.e. date
 * filters are AND'ed, not OR'ed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DataRequirementDateFilter(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * A date-valued attribute to filter on
   */
  var path: String? = null,
  /**
   * A date valued parameter to search on
   */
  var searchParam: String? = null,
  /**
   * The value of the filter, as a Period, DateTime, or Duration value
   */
  var valueDateTime: String? = null,
  /**
   * The value of the filter, as a Period, DateTime, or Duration value
   */
  var valueDuration: Duration? = null,
  /**
   * The value of the filter, as a Period, DateTime, or Duration value
   */
  var valuePeriod: Period? = null
) : Element
