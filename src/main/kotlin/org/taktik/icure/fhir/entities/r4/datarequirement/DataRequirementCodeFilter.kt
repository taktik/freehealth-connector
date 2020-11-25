//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.datarequirement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * What codes are expected
 *
 * Code filters specify additional constraints on the data, specifying the value set of interest for
 * a particular element of the data. Each code filter defines an additional constraint on the data,
 * i.e. code filters are AND'ed, not OR'ed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DataRequirementCodeFilter(
  var code: List<Coding> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * A code-valued attribute to filter on
   */
  var path: String? = null,
  /**
   * A coded (token) parameter to search on
   */
  var searchParam: String? = null,
  /**
   * Valueset for the filter
   */
  var valueSet: String? = null
) : Element
