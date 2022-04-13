package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetPrescriptionStatus.
 *
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPrescriptionStatus")
public class GetPrescriptionStatus {

	/** The get prescription status sealed. */
	@XmlElement(name = "GetPrescriptionStatusParamSealed")
	protected byte[] getPrescriptionStatusParamSealed;

	/**
	 * Instantiates a new gets the prescription status.
	 */
	public GetPrescriptionStatus() {
		super();
	}

	/**
	 * Gets the gets the prescription status param sealed.
	 *
	 * @return the getPrescriptionStatusParamSealed
	 */
	public byte[] getGetPrescriptionStatusParamSealed() {
		return getPrescriptionStatusParamSealed;
	}

	/**
	 * Sets the gets the prescription status param sealed.
	 *
	 * @param getPrescriptionStatusParamSealed
	 *            the getPrescriptionStatusParamSealed to set
	 */
	public void setGetPrescriptionStatusParamSealed(byte[] getPrescriptionStatusParamSealed) {
		this.getPrescriptionStatusParamSealed = getPrescriptionStatusParamSealed;
	}

}