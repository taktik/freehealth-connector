//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.molecularsequence

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * An set of value as quality of sequence
 *
 * An experimental feature attribute that defines the quality of the feature in a quantitative way,
 * such as a phred quality score
 * ([SO:0001686](http://www.sequenceontology.org/browser/current_svn/term/SO:0001686)).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class MolecularSequenceQuality(
  /**
   * End position of the sequence
   */
  var end: Int? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * F-score
   */
  var fScore: Float? = null,
  /**
   * False positives where the non-REF alleles in the Truth and Query Call Sets match
   */
  var gtFP: Float? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  /**
   * Method to get quality
   */
  var method: CodeableConcept? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Precision of comparison
   */
  var precision: Float? = null,
  /**
   * False positives
   */
  var queryFP: Float? = null,
  /**
   * True positives from the perspective of the query data
   */
  var queryTP: Float? = null,
  /**
   * Recall of comparison
   */
  var recall: Float? = null,
  /**
   * Receiver Operator Characteristic (ROC) Curve
   */
  var roc: MolecularSequenceQualityRoc? = null,
  /**
   * Quality score for the comparison
   */
  var score: Quantity? = null,
  /**
   * Standard sequence for comparison
   */
  var standardSequence: CodeableConcept? = null,
  /**
   * Start position of the sequence
   */
  var start: Int? = null,
  /**
   * False negatives
   */
  var truthFN: Float? = null,
  /**
   * True positives from the perspective of the truth data
   */
  var truthTP: Float? = null,
  /**
   * indel | snp | unknown
   */
  var type: String? = null
) : BackboneElement
