//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.devicedefinition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.prodcharacteristic.ProdCharacteristic
import org.taktik.icure.fhir.entities.r4.productshelflife.ProductShelfLife
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * An instance of a medical-related component of a medical device
 *
 * The characteristics, operational status and capabilities of a medical-related component of a
 * medical device.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class DeviceDefinition(
  var capability: List<DeviceDefinitionCapability> = listOf(),
  var contact: List<ContactPoint> = listOf(),
  override var contained: List<Resource> = listOf(),
  var deviceName: List<DeviceDefinitionDeviceName> = listOf(),
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
  var languageCode: List<CodeableConcept> = listOf(),
  /**
   * Name of device manufacturer
   */
  var manufacturerReference: Reference? = null,
  /**
   * Name of device manufacturer
   */
  var manufacturerString: String? = null,
  var material: List<DeviceDefinitionMaterial> = listOf(),
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
   * Access to on-line information
   */
  var onlineInformation: String? = null,
  /**
   * Organization responsible for device
   */
  var owner: Reference? = null,
  /**
   * The parent device it can be part of
   */
  var parentDevice: Reference? = null,
  /**
   * Dimensions, color etc.
   */
  var physicalCharacteristics: ProdCharacteristic? = null,
  var property: List<DeviceDefinitionProperty> = listOf(),
  /**
   * The quantity of the device present in the packaging (e.g. the number of devices present in a
   * pack, or the number of devices in the same package of the medicinal product)
   */
  var quantity: Quantity? = null,
  var safety: List<CodeableConcept> = listOf(),
  var shelfLifeStorage: List<ProductShelfLife> = listOf(),
  var specialization: List<DeviceDefinitionSpecialization> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null,
  /**
   * What kind of device or device system this is
   */
  var type: CodeableConcept? = null,
  var udiDeviceIdentifier: List<DeviceDefinitionUdiDeviceIdentifier> = listOf(),
  /**
   * Network address to contact device
   */
  var url: String? = null,
  var version: List<String> = listOf()
) : DomainResource
