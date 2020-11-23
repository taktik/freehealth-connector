//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.substancesourcematerial

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * 4.9.13.8.1 Hybrid species maternal organism ID (Optional)
 *
 * 4.9.13.8.1 Hybrid species maternal organism ID (Optional).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubstanceSourceMaterialOrganismHybrid(
  override var extension: List<Extension> = listOf(),
  /**
   * The hybrid type of an organism shall be specified
   */
  var hybridType: CodeableConcept? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * The identifier of the maternal species constituting the hybrid organism shall be specified
   * based on a controlled vocabulary. For plants, the parents aren’t always known, and it is unlikely
   * that it will be known which is maternal and which is paternal
   */
  var maternalOrganismId: String? = null,
  /**
   * The name of the maternal species constituting the hybrid organism shall be specified. For
   * plants, the parents aren’t always known, and it is unlikely that it will be known which is
   * maternal and which is paternal
   */
  var maternalOrganismName: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * The identifier of the paternal species constituting the hybrid organism shall be specified
   * based on a controlled vocabulary
   */
  var paternalOrganismId: String? = null,
  /**
   * The name of the paternal species constituting the hybrid organism shall be specified
   */
  var paternalOrganismName: String? = null
) : BackboneElement
