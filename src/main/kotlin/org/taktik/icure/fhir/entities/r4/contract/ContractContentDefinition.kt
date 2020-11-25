//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.contract

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Contract precursor content
 *
 * Precusory content developed with a focus and intent of supporting the formation a Contract
 * instance, which may be associated with and transformable into a Contract.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContractContentDefinition(
  /**
   * Publication Ownership
   */
  var copyright: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * When published
   */
  var publicationDate: String? = null,
  /**
   * amended | appended | cancelled | disputed | entered-in-error | executable | executed |
   * negotiable | offered | policy | rejected | renewed | revoked | resolved | terminated
   */
  var publicationStatus: String? = null,
  /**
   * Publisher Entity
   */
  var publisher: Reference? = null,
  /**
   * Detailed Content Type Definition
   */
  var subType: CodeableConcept? = null,
  /**
   * Content structure and use
   */
  var type: CodeableConcept
) : BackboneElement
