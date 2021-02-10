//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.documentreference

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Document referenced
 *
 * The document and format referenced. There may be multiple content element repetitions, each with
 * a different format.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class DocumentReferenceContent(
  /**
   * Where to access the document
   */
  var attachment: Attachment,
  override var extension: List<Extension> = listOf(),
  /**
   * Format/content rules for the document
   */
  var format: Coding? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
