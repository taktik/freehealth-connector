//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Entries in the list
 *
 * Entries in this list.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ListEntry(
  /**
   * When item added to list
   */
  var date: String? = null,
  /**
   * If this item is actually marked as deleted
   */
  var deleted: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Status/Workflow information about this item
   */
  var flag: CodeableConcept? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Actual entry
   */
  var item: Reference,
  override var modifierExtension: List<Extension> = listOf()
) : BackboneElement
