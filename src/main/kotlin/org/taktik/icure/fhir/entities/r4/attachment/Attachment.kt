//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.attachment

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Content in a format defined elsewhere
 *
 * For referring to data content defined in other formats.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Attachment(
  /**
   * Mime type of the content, with charset etc.
   */
  var contentType: String? = null,
  /**
   * Date attachment was first created
   */
  var creation: String? = null,
  /**
   * Data inline, base64ed
   */
  var data: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Hash of the data (sha-1, base64ed)
   */
  var hash: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Human language of the content (BCP-47)
   */
  var language: String? = null,
  /**
   * Number of bytes of content (if url provided)
   */
  var size: Int? = null,
  /**
   * Label to display in place of the data
   */
  var title: String? = null,
  /**
   * Uri where the data can be found
   */
  var url: String? = null
) : Element
