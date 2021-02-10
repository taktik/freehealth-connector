//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.binary

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Pure binary content defined by a format other than FHIR
 *
 * A resource that represents the data of a single raw artifact as digital content accessible in its
 * native format.  A Binary resource can contain any content, whether text, image, pdf, zip archive,
 * etc.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Binary(
  /**
   * MimeType of the binary content
   */
  var contentType: String? = null,
  /**
   * The actual content
   */
  var data: String? = null,
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  /**
   * Identifies another resource to use as proxy when enforcing access control
   */
  var securityContext: Reference? = null
) : Resource
