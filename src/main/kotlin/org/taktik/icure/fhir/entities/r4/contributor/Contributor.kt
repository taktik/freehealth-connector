//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contributor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Contributor information
 *
 * A contributor to the content of a knowledge asset, including authors, editors, reviewers, and
 * endorsers.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Contributor(
  var contact: List<ContactDetail> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Who contributed the content
   */
  var name: String? = null,
  /**
   * author | editor | reviewer | endorser
   */
  var type: String? = null
) : Element
