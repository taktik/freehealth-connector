//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.specimendefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * Specimen handling before testing
 *
 * Set of instructions for preservation/transport of the specimen at a defined temperature interval,
 * prior the testing process.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SpecimenDefinitionTypeTestedHandling(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Preservation instruction
   */
  var instruction: String? = null,
  /**
   * Maximum preservation time
   */
  var maxDuration: Duration? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Temperature qualifier
   */
  var temperatureQualifier: CodeableConcept? = null,
  /**
   * Temperature range
   */
  var temperatureRange: Range? = null
) : BackboneElement
