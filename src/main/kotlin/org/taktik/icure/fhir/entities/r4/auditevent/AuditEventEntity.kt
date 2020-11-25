//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.auditevent

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Data or objects used
 *
 * Specific instances of data or objects that have been accessed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class AuditEventEntity(
  /**
   * Descriptive text
   */
  var description: String? = null,
  var detail: List<AuditEventEntityDetail> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Life-cycle stage for the entity
   */
  var lifecycle: Coding? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Descriptor for entity
   */
  var name: String? = null,
  /**
   * Query parameters
   */
  var query: String? = null,
  /**
   * What role the entity played
   */
  var role: Coding? = null,
  var securityLabel: List<Coding> = listOf(),
  /**
   * Type of entity involved
   */
  var type: Coding? = null,
  /**
   * Specific instance of resource
   */
  var what: Reference? = null
) : BackboneElement
