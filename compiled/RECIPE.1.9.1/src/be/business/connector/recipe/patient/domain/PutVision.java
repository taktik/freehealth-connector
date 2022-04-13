package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PutVision.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutVision")
public class PutVision {

	/** The put vision param sealed. */
	@XmlElement(name = "PutVisionParamSealed")
	protected byte[] putVisionParamSealed;

	/**
	 * Instantiates a new put vision.
	 */
	public PutVision() {
		super();
	}

	/**
	 * Gets the put vision param sealed.
	 *
	 * @return the put vision param sealed
	 */
	public byte[] getPutVisionParamSealed() {
		return putVisionParamSealed;
	}

	/**
	 * Sets the put vision param sealed.
	 *
	 * @param putVisionParamSealed
	 *            the new put vision param sealed
	 */
	public void setPutVisionParamSealed(byte[] putVisionParamSealed) {
		this.putVisionParamSealed = putVisionParamSealed;
	}
}