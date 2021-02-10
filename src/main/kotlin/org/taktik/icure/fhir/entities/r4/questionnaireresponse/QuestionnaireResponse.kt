//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.questionnaireresponse

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
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
 * A structured set of questions and their answers
 *
 * A structured set of questions and their answers. The questions are ordered and grouped into
 * coherent subsets, corresponding to the structure of the grouping of the questionnaire being
 * responded to.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class QuestionnaireResponse(
  /**
   * Person who received and recorded the answers
   */
  var author: Reference? = null,
  /**
   * Date the answers were gathered
   */
  var authored: String? = null,
  var basedOn: List<Reference> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Encounter created as part of
   */
  var encounter: Reference? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Unique id for this set of answers
   */
  var identifier: Identifier? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  var item: List<QuestionnaireResponseItem> = listOf(),
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var partOf: List<Reference> = listOf(),
  /**
   * Form being answered
   */
  var questionnaire: String? = null,
  /**
   * The person who answered the questions
   */
  var source: Reference? = null,
  /**
   * in-progress | completed | amended | entered-in-error | stopped
   */
  var status: String? = null,
  /**
   * The subject of the questions
   */
  var subject: Reference? = null,
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
