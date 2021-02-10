//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.specimendefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.duration.Duration
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Specimen in container intended for testing by lab
 *
 * Specimen conditioned in a container as expected by the testing laboratory.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SpecimenDefinitionTypeTested(
  /**
   * The specimen's container
   */
  var container: SpecimenDefinitionTypeTestedContainer? = null,
  override var extension: List<Extension> = listOf(),
  var handling: List<SpecimenDefinitionTypeTestedHandling> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Primary or secondary specimen
   */
  var isDerived: Boolean? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * preferred | alternate
   */
  var preference: String? = null,
  var rejectionCriterion: List<CodeableConcept> = listOf(),
  /**
   * Specimen requirements
   */
  var requirement: String? = null,
  /**
   * Specimen retention time
   */
  var retentionTime: Duration? = null,
  /**
   * Type of intended specimen
   */
  var type: CodeableConcept? = null
) : BackboneElement
