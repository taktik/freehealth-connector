package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetPrescriptionStatusResponse.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPrescriptionStatusResponse")
public class GetPrescriptionStatusResponse {

	/** The Constant serialVersionUID. */
	private final static long serialVersionUID = 1L;

	/** The get prescription status result sealed. */
	@XmlElement(name = "GetPrescriptionStatusResultSealed")
	protected byte[] getPrescriptionStatusResultSealed;

	/**
	 * Instantiates a new gets the prescription status response.
	 */
	public GetPrescriptionStatusResponse() {
		super();
	}

	/**
	 * Gets the gets the prescription status result sealed.
	 *
	 * @return the gets the prescription status result sealed
	 */
	public byte[] getGetPrescriptionStatusResultSealed() {
		return getPrescriptionStatusResultSealed;
	}

	/**
	 * Sets the gets the prescription status result sealed.
	 *
	 * @param getPrescriptionStatusResultSealed
	 *            the new gets the prescription status result sealed
	 */
	public void setGetPrescriptionStatusResultSealed(byte[] getPrescriptionStatusResultSealed) {
		this.getPrescriptionStatusResultSealed = getPrescriptionStatusResultSealed;
	}
}