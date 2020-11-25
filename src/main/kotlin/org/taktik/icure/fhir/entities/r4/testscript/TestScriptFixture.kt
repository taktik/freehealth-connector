//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testscript

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Fixture in the test script - by reference (uri)
 *
 * Fixture in the test script - by reference (uri). All fixtures are required for the test script to
 * execute.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TestScriptFixture(
  /**
   * Whether or not to implicitly create the fixture during setup
   */
  var autocreate: Boolean? = null,
  /**
   * Whether or not to implicitly delete the fixture during teardown
   */
  var autodelete: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Reference of the resource
   */
  var resource: Reference? = null
) : BackboneElement
