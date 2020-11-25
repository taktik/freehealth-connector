//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.relatedartifact

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Related artifacts for a knowledge resource
 *
 * Related artifacts such as additional documentation, justification, or bibliographic references.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RelatedArtifact(
  /**
   * Bibliographic citation for the artifact
   */
  var citation: String? = null,
  /**
   * Brief description of the related artifact
   */
  var display: String? = null,
  /**
   * What document is being referenced
   */
  var document: Attachment? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Short label
   */
  var label: String? = null,
  /**
   * What resource is being referenced
   */
  var resource: String? = null,
  /**
   * documentation | justification | citation | predecessor | successor | derived-from | depends-on
   * | composed-of
   */
  var type: String? = null,
  /**
   * Where the artifact can be accessed
   */
  var url: String? = null
) : Element
