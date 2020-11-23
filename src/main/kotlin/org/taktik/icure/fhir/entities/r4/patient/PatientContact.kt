//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.patient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.address.Address
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.humanname.HumanName
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * A contact party (e.g. guardian, partner, friend) for the patient
 *
 * A contact party (e.g. guardian, partner, friend) for the patient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PatientContact(
  /**
   * Address for the contact person
   */
  var address: Address? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * male | female | other | unknown
   */
  var gender: String? = null,
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * A name associated with the contact person
   */
  var name: HumanName? = null,
  /**
   * Organization that is associated with the contact
   */
  var organization: Reference? = null,
  /**
   * The period during which this contact person or organization is valid to be contacted relating
   * to this patient
   */
  var period: Period? = null,
  var relationship: List<CodeableConcept> = listOf(),
  var telecom: List<ContactPoint> = listOf()
) : BackboneElement
