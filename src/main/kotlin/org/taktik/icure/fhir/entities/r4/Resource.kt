//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.taktik.icure.fhir.deserializer.FhirResourceDeserializer
import kotlin.String

/**
 * Base Resource
 *
 * This is the base resource type for everything.
 */
@JsonDeserialize(using = FhirResourceDeserializer::class)
interface Resource {
  /**
   * Logical id of this artifact
   */
  var id: String?

  /**
   * A set of rules under which this content was created
   */
  var implicitRules: String?

  /**
   * Language of the resource content
   */
  var language: String?

  /**
   * Metadata about the resource
   */
  var meta: Meta?
}
