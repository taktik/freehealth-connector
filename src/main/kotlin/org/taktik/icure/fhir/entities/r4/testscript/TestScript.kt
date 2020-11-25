//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testscript

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactdetail.ContactDetail
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.usagecontext.UsageContext

/**
 * Describes a set of tests
 *
 * A structured set of tests against a FHIR server or client implementation to determine compliance
 * against the FHIR specification.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class TestScript(
  var contact: List<ContactDetail> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Use and/or publishing restrictions
   */
  var copyright: String? = null,
  /**
   * Date last changed
   */
  var date: String? = null,
  /**
   * Natural language description of the test script
   */
  var description: String? = null,
  var destination: List<TestScriptDestination> = listOf(),
  /**
   * For testing purposes, not real usage
   */
  var experimental: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  var fixture: List<TestScriptFixture> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Additional identifier for the test script
   */
  var identifier: Identifier? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var jurisdiction: List<CodeableConcept> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  /**
   * Required capability that is assumed to function correctly on the FHIR server being tested
   */
  var metadata: TestScriptMetadata? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Name for this test script (computer friendly)
   */
  var name: String? = null,
  var origin: List<TestScriptOrigin> = listOf(),
  var profile: List<Reference> = listOf(),
  /**
   * Name of the publisher (organization or individual)
   */
  var publisher: String? = null,
  /**
   * Why this test script is defined
   */
  var purpose: String? = null,
  /**
   * A series of required setup operations before tests are executed
   */
  var setup: TestScriptSetup? = null,
  /**
   * draft | active | retired | unknown
   */
  var status: String? = null,
  /**
   * A series of required clean up steps
   */
  var teardown: TestScriptTeardown? = null,
  var test: List<TestScriptTest> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * Name for this test script (human friendly)
   */
  var title: String? = null,
  /**
   * Canonical identifier for this test script, represented as a URI (globally unique)
   */
  var url: String? = null,
  var useContext: List<UsageContext> = listOf(),
  var variable: List<TestScriptVariable> = listOf(),
  /**
   * Business version of the test script
   */
  var version: String? = null
) : DomainResource
