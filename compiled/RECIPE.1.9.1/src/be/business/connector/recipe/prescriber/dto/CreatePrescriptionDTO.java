/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.prescriber.dto;

import org.apache.commons.lang3.Validate;

/**
 * The Class CreatePrescriptionDTO.
 *
 * @author <a href="mailto:bruno.casneuf@recip-e.be">Bruno Casneuf</a>
 */
public class CreatePrescriptionDTO {

	private int sequenceNumber;

	/** The feedback requested. */
	private final boolean feedbackRequested;

	/** The patient id. */
	private final String patientId;

	/** The prescription. */
	private byte[] prescription;

	/** The prescription type. */
	private final String prescriptionType;

	/** The visi. */
	private final String visi;

	private String rid;

	private Exception exception;

	private boolean errorOccured;

	/**
	 * Instantiates a new creates the prescription DTO.
	 *
	 * @param feedbackRequested
	 *            the feedback requested
	 * @param patientId
	 *            the patient id
	 * @param prescription
	 *            the prescription
	 * @param prescriptionType
	 *            the prescription type
	 * @param visi
	 *            the visi
	 */
	public CreatePrescriptionDTO(final int sequenceNumber, final boolean feedbackRequested, final String patientId, byte[] prescription,
			final String prescriptionType, final String visi) {
		Validate.notNull(sequenceNumber);
		this.sequenceNumber = sequenceNumber;
		this.feedbackRequested = feedbackRequested;
		this.patientId = patientId;
		this.prescription = prescription;
		this.prescriptionType = prescriptionType;
		this.visi = visi;
	}

	/**
	 * Checks if is feedback requested.
	 *
	 * @return the feedbackRequested
	 */
	public boolean isFeedbackRequested() {
		return feedbackRequested;
	}

	/**
	 * Gets the patient id.
	 *
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * Gets the prescription.
	 *
	 * @return the prescription
	 */
	public byte[] getPrescription() {
		return prescription;
	}

	/**
	 * Gets the prescription type.
	 *
	 * @return the prescriptionType
	 */
	public String getPrescriptionType() {
		return prescriptionType;
	}

	/**
	 * Gets the visi.
	 *
	 * @return the visi
	 */
	public String getVisi() {
		return visi;
	}

	/**
	 * @return the rid
	 */
	public String getRid() {
		return rid;
	}

	/**
	 * @param rid
	 *            the rid to set
	 */
	public void setRid(final String rid) {
		this.rid = rid;
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception
	 *            the exception to set
	 */
	public void setException(final Exception exception) {
		this.exception = exception;
	}

	/**
	 * @param b
	 */
	public void setErrorOccured(final boolean errorOccured) {
		this.errorOccured = errorOccured;
	}

	/**
	 * @return the errorOccured
	 */
	public boolean isErrorOccured() {
		return errorOccured;
	}
	
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the sequenceNumber
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param prescription2
	 */
	public void setPrescription(byte[] prescription) {
		this.prescription = prescription;
	}
}