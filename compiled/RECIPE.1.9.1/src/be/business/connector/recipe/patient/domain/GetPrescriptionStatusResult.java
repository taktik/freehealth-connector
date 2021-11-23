package be.business.connector.recipe.patient.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.business.connector.recipe.domain.PrescriptionStatus;
import be.recipe.services.core.ResponseType;

/**
 * The Class GetPrescriptionStatusResult.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPrescriptionStatusResult")
public class GetPrescriptionStatusResult extends ResponseType implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The status. */
	private PrescriptionStatus prescriptionStatus;

	public GetPrescriptionStatusResult() {
		super();
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public PrescriptionStatus getPrescriptionStatus() {
		return prescriptionStatus;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setPrescriptionStatus(final PrescriptionStatus prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}

	/**
	 * Instantiates a new gets the prescription status result.
	 *
	 * @param status
	 *            the status
	 */
	public GetPrescriptionStatusResult(final PrescriptionStatus prescriptionStatus) {
		super();
		this.prescriptionStatus = prescriptionStatus;
	}

}
