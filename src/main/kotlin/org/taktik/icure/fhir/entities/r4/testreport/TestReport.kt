//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.testreport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Float
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Describes the results of a TestScript execution
 *
 * A summary of information based on the results of executing a TestScript.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class TestReport(
  override var contained: List<Resource> = listOf(),
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * External identifier
   */
  var identifier: Identifier? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * When the TestScript was executed and this TestReport was generated
   */
  var issued: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Informal name of the executed TestScript
   */
  var name: String? = null,
  var participant: List<TestReportParticipant> = listOf(),
  /**
   * pass | fail | pending
   */
  var result: String? = null,
  /**
   * The final score (percentage of tests passed) resulting from the execution of the TestScript
   */
  var score: Float? = null,
  /**
   * The results of the series of required setup operations before the tests were executed
   */
  var setup: TestReportSetup? = null,
  /**
   * completed | in-progress | waiting | stopped | entered-in-error
   */
  var status: String? = null,
  /**
   * The results of running the series of required clean up steps
   */
  var teardown: TestReportTeardown? = null,
  var test: List<TestReportTest> = listOf(),
  /**
   * Reference to the  version-specific TestScript that was executed to produce this TestReport
   */
  var testScript: Reference,
  /**
   * Name of the tester producing this report (Organization or individual)
   */
  var tester: String? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
