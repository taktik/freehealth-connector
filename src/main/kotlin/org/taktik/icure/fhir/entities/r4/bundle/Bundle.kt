//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.bundle

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.signature.Signature

/**
 * Contains a collection of resources
 *
 * A container for a collection of resources.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Bundle(
  var entry: List<BundleEntry> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  /**
   * Persistent identifier for the bundle
   */
  var identifier: Identifier? = null,
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  var link: List<BundleLink> = listOf(),
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  /**
   * Digital Signature
   */
  var signature: Signature? = null,
  /**
   * When the bundle was assembled
   */
  var timestamp: String? = null,
  /**
   * If search, the total number of matches
   */
  var total: Int? = null,
  /**
   * document | message | transaction | transaction-response | batch | batch-response | history |
   * searchset | collection
   */
  var type: String? = null
) : Resource
