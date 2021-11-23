package be.business.connector.recipe.prescriber.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PutVisionResponse.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutVisionResponse")
public class PutVisionResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private final static long serialVersionUID = 1L;

	/** The put vision result sealed. */
	@XmlElement(name = "PutVisionResultSealed")
	protected byte[] putVisionResultSealed;

	/**
	 * Instantiates a new put vision response.
	 */
	public PutVisionResponse() {
		super();
	}

	/**
	 * Gets the put vision result sealed.
	 *
	 * @return the put vision result sealed
	 */
	public byte[] getPutVisionResultSealed() {
		return putVisionResultSealed;
	}

	/**
	 * Sets the put vision result sealed.
	 *
	 * @param putVisionResultSealed
	 *            the new put vision result sealed
	 */
	public void setPutVisionResultSealed(byte[] putVisionResultSealed) {
		this.putVisionResultSealed = putVisionResultSealed;
	}
}