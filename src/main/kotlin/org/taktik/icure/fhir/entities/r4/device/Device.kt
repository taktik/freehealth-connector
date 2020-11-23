//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.device

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Item used in healthcare
 *
 * A type of a manufactured item that is used in the provision of healthcare without being
 * substantially changed through that activity. The device may be a medical or non-medical device.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Device(
  var contact: List<ContactPoint> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * The reference to the definition for the device
   */
  var definition: Reference? = null,
  var deviceName: List<DeviceDeviceName> = listOf(),
  /**
   * The distinct identification string
   */
  var distinctIdentifier: String? = null,
  /**
   * Date and time of expiry of this device (if applicable)
   */
  var expirationDate: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Logical id of this artifact
   */
  override var id: String? = null,
  var identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override var implicitRules: String? = null,
  /**
   * Language of the resource content
   */
  override var language: String? = null,
  /**
   * Where the device is found
   */
  var location: Reference? = null,
  /**
   * Lot number of manufacture
   */
  var lotNumber: String? = null,
  /**
   * Date when the device was made
   */
  var manufactureDate: String? = null,
  /**
   * Name of device manufacturer
   */
  var manufacturer: String? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  /**
   * The model number for the device
   */
  var modelNumber: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  var note: List<Annotation> = listOf(),
  /**
   * Organization responsible for device
   */
  var owner: Reference? = null,
  /**
   * The parent device
   */
  var parent: Reference? = null,
  /**
   * The part number of the device
   */
  var partNumber: String? = null,
  /**
   * Patient to whom Device is affixed
   */
  var patient: Reference? = null,
  var property: List<DeviceProperty> = listOf(),
  var safety: List<CodeableConcept> = listOf(),
  /**
   * Serial number assigned by the manufacturer
   */
  var serialNumber: String? = null,
  var specialization: List<DeviceSpecialization> = listOf(),
  /**
   * active | inactive | entered-in-error | unknown
   */
  var status: String? = null,
  var statusReason: List<CodeableConcept> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * The kind or type of device
   */
  var type: CodeableConcept? = null,
  var udiCarrier: List<DeviceUdiCarrier> = listOf(),
  /**
   * Network address to contact device
   */
  var url: String? = null,
  var version: List<DeviceVersion> = listOf()
) : DomainResource
