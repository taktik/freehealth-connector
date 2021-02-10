//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.molecularsequence

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * External repository which contains detailed report related with observedSeq in this resource
 *
 * Configurations of the external repository. The repository shall store target's observedSeq or
 * records related with target's observedSeq.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MolecularSequenceRepository(
  /**
   * Id of the dataset that used to call for dataset in repository
   */
  var datasetId: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Repository's name
   */
  var name: String? = null,
  /**
   * Id of the read
   */
  var readsetId: String? = null,
  /**
   * directlink | openapi | login | oauth | other
   */
  var type: String? = null,
  /**
   * URI of the repository
   */
  var url: String? = null,
  /**
   * Id of the variantset that used to call for variantset in repository
   */
  var variantsetId: String? = null
) : BackboneElement
