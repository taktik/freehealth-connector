package be.business.connector.recipe.patient.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;
import be.recipe.services.executor.PrescriptionWithSecurityToken;

/**
 * The Class GetOpenPrescriptionListResult.
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetOpenPrescriptionListResult")
public class GetOpenPrescriptionListResult extends ResponseType {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The prescriptions. */
	private List<PrescriptionWithSecurityToken> prescriptions;

	/**
	 * Instantiates a new gets the open prescription list result.
	 */
	public GetOpenPrescriptionListResult() {
	}

	/**
	 * Gets the prescriptions.
	 *
	 * @return the prescriptions
	 */
	public List<PrescriptionWithSecurityToken> getPrescriptions() {
		return prescriptions;
	}

	/**
	 * Sets the prescriptions.
	 *
	 * @param prescriptions
	 *            the new prescriptions
	 */
	public void setPrescriptions(List<PrescriptionWithSecurityToken> prescriptions) {
		this.prescriptions = prescriptions;
	}
}