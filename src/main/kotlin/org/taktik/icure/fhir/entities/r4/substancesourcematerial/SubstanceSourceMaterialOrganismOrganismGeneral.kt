//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancesourcematerial

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * 4.9.13.7.1 Kingdom (Conditional)
 *
 * 4.9.13.7.1 Kingdom (Conditional).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSourceMaterialOrganismOrganismGeneral(
  /**
   * The class of an organism shall be specified
   */
  @JsonProperty("class")
  var class_fhir: CodeableConcept? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * The kingdom of an organism shall be specified
   */
  var kingdom: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The order of an organism shall be specified,
   */
  var order: CodeableConcept? = null,
  /**
   * The phylum of an organism shall be specified
   */
  var phylum: CodeableConcept? = null
) : BackboneElement
