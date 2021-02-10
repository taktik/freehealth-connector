//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.familymemberhistory

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.age.Age
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.range.Range

/**
 * Condition that the related person had
 *
 * The significant Conditions (or condition) that the family member had. This is a repeating section
 * to allow a system to represent more than one condition per resource, though there is nothing
 * stopping multiple resources - one per condition.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class FamilyMemberHistoryCondition(
  /**
   * Condition suffered by relation
   */
  var code: CodeableConcept,
  /**
   * Whether the condition contributed to the cause of death
   */
  var contributedToDeath: Boolean? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * When condition first manifested
   */
  var onsetAge: Age? = null,
  /**
   * When condition first manifested
   */
  var onsetPeriod: Period? = null,
  /**
   * When condition first manifested
   */
  var onsetRange: Range? = null,
  /**
   * When condition first manifested
   */
  var onsetString: String? = null,
  /**
   * deceased | permanent disability | etc.
   */
  var outcome: CodeableConcept? = null
) : BackboneElement
