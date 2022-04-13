/**
 * Copyright (C) 2010 Recip-e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package be.business.connector.recipe.patient.domain;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

/**
 * The Class GetPrescriptionForPatientResult.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPrescriptionForPatientResult")
public class GetPrescriptionForPatientResult extends ResponseType {

	/**
	 * The rid.
	 */
	private String rid;

	/**
	 * The creation date.
	 */
	private Calendar creationDate;

	/**
	 * The patient id.
	 */
	private String prescriberId;

	/**
	 * The feedback allowed.
	 */
	private boolean feedbackAllowed = true;

	/**
	 * The prescription.
	 */
	private byte[] prescription;

	/**
	 * The prescription type.
	 */
	private String prescriptionType;

	/**
	 * The encryption key id.
	 */
	private String encryptionKeyId;

	/**
	 * Instantiates a new prescription response for executor.
	 */
	public GetPrescriptionForPatientResult() {

	}

	/**
	 * Instantiates a new prescription response for executor.
	 *
	 * @param prescription
	 *            the value of prescription
	 */
	public GetPrescriptionForPatientResult(byte[] prescription) {
		super();
		this.prescription = prescription;
	}

	/**
	 * Gets the prescription type.
	 *
	 * @return the prescription type
	 */
	public String getPrescriptionType() {

		return prescriptionType;
	}

	/**
	 * Sets the prescription type.
	 *
	 * @param prescriptionType
	 *            the new prescription type
	 */
	public void setPrescriptionType(String prescriptionType) {

		this.prescriptionType = prescriptionType;
	}

	/**
	 * Sets the feedback allowed.
	 *
	 * @param feedbackAllowed
	 *            the new feedback allowed
	 */
	public void setFeedbackAllowed(boolean feedbackAllowed) {

		this.feedbackAllowed = feedbackAllowed;
	}

	/**
	 * Gets the rid.
	 *
	 * @return the rid
	 */
	public String getRid() {

		return rid;
	}

	/**
	 * Sets the rid.
	 *
	 * @param rid
	 *            the new rid
	 */
	public void setRid(String rid) {

		this.rid = rid;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Calendar getCreationDate() {

		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(Calendar creationDate) {

		this.creationDate = creationDate;
	}

	/**
	 * Gets the prescriber id.
	 *
	 * @return the prescriber id
	 */
	public String getPrescriberId() {

		return prescriberId;
	}

	/**
	 * Sets the prescriber id.
	 *
	 * @param prescriberId
	 *            the new prescriber id
	 */
	public void setPrescriberId(String prescriberId) {

		this.prescriberId = prescriberId;
	}

	/**
	 * Checks if is feedback allowed.
	 *
	 * @return true, if is feedback allowed
	 */
	public boolean isFeedbackAllowed() {

		return feedbackAllowed;
	}

	/**
	 * Sets the feedback requested.
	 *
	 * @param feedbackAllowed
	 *            the new feedback requested
	 */
	@Deprecated
	public void setFeedbackRequested(boolean feedbackAllowed) {

		this.feedbackAllowed = feedbackAllowed;
	}

	/**
	 * Gets the prescription.
	 *
	 * @return the prescription
	 */
	public byte[] getPrescription() {

		return (byte[]) prescription.clone();
	}

	/**
	 * Sets the prescription.
	 *
	 * @param prescription
	 *            the new prescription
	 */
	public void setPrescription(byte[] prescription) {

		this.prescription = (byte[]) prescription.clone();
	}

	/**
	 * Gets the encryption key id.
	 *
	 * @return the encryption key id
	 */
	public String getEncryptionKeyId() {

		return encryptionKeyId;
	}

	/**
	 * Sets the encryption key id.
	 *
	 * @param encryptionKeyId
	 *            the new encryption key id
	 */
	public void setEncryptionKeyId(String encryptionKeyId) {

		this.encryptionKeyId = encryptionKeyId;
	}

}
