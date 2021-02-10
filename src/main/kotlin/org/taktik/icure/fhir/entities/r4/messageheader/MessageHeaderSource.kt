//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.messageheader

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Message source application
 *
 * The source application from which this message originated.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MessageHeaderSource(
  /**
   * Human contact for problems
   */
  var contact: ContactPoint? = null,
  /**
   * Actual message source address or id
   */
  var endpoint: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name of system
   */
  var name: String? = null,
  /**
   * Name of software running the system
   */
  var software: String? = null,
  /**
   * Version of software running
   */
  var version: String? = null
) : BackboneElement
