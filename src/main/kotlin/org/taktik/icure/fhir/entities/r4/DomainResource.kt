//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.taktik.icure.fhir.deserializer.FhirResourceDeserializer
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.narrative.Narrative

/**
 * A resource with narrative, extensions, and contained resources
 *
 * A resource that includes narrative, extensions, and contained resources.
 */
@JsonDeserialize(using = FhirResourceDeserializer::class)
interface DomainResource : Resource {
  var contained: List<Resource>

  var extension: List<Extension>

  var modifierExtension: List<Extension>

  /**
   * Text summary of the resource, for human interpretation
   */
  var text: Narrative?
}
