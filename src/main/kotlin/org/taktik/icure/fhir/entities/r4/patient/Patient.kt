//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.patient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.address.Address
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.humanname.HumanName
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Information about an individual or animal receiving health care services
 *
 * Demographics and other administrative information about an individual or animal receiving care or
 * other health-related services.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using= JsonDeserializer.None::class)
class Patient(
  /**
   * Whether this patient's record is in active use
   */
  var active: Boolean? = null,
  var address: List<Address> = listOf(),
  /**
   * The date of birth for the individual
   */
  var birthDate: String? = null,
  var communication: List<PatientCommunication> = listOf(),
  var contact: List<PatientContact> = listOf(),
  override var contained: List<Resource> = listOf(),
  /**
   * Indicates if the individual is deceased or not
   */
  var deceasedBoolean: Boolean? = null,
  /**
   * Indicates if the individual is deceased or not
   */
  var deceasedDateTime: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * male | female | other | unknown
   */
  var gender: String? = null,
  var generalPractitioner: List<Reference> = listOf(),
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
  var link: List<PatientLink> = listOf(),
  /**
   * Organization that is the custodian of the patient record
   */
  var managingOrganization: Reference? = null,
  /**
   * Marital (civil) status of a patient
   */
  var maritalStatus: CodeableConcept? = null,
  /**
   * Metadata about the resource
   */
  override var meta: Meta? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * Whether patient is part of a multiple birth
   */
  var multipleBirthBoolean: Boolean? = null,
  /**
   * Whether patient is part of a multiple birth
   */
  var multipleBirthInteger: Int? = null,
  var name: List<HumanName> = listOf(),
  var photo: List<Attachment> = listOf(),
  var telecom: List<ContactPoint> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override var text: Narrative? = null
) : DomainResource
