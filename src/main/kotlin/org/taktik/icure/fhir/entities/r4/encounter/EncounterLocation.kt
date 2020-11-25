//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.encounter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * List of locations where the patient has been
 *
 * List of locations where  the patient has been during this encounter.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class EncounterLocation(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Location the encounter takes place
   */
  var location: Reference,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Time period during which the patient was present at the location
   */
  var period: Period? = null,
  /**
   * The physical type of the location (usually the level in the location hierachy - bed room ward
   * etc.)
   */
  var physicalType: CodeableConcept? = null,
  /**
   * planned | active | reserved | completed
   */
  var status: String? = null
) : BackboneElement
