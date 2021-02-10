//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.composition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Attests to accuracy of composition
 *
 * A participant who has attested to the accuracy of the composition/document.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CompositionAttester(
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * personal | professional | legal | official
   */
  var mode: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Who attested the composition
   */
  var party: Reference? = null,
  /**
   * When the composition was attested
   */
  var time: String? = null
) : BackboneElement
