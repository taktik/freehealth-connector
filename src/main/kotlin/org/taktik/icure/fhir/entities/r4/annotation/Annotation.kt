//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.annotation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Element
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Text node with attribution
 *
 * A  text note which also  contains information about who made the statement and when.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Annotation(
  /**
   * Individual responsible for the annotation
   */
  var authorReference: Reference? = null,
  /**
   * Individual responsible for the annotation
   */
  var authorString: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * The annotation  - text content (as markdown)
   */
  var text: String? = null,
  /**
   * When the annotation was made
   */
  var time: String? = null
) : Element
