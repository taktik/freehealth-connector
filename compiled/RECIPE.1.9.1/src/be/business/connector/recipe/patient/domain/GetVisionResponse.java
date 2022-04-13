package be.business.connector.recipe.patient.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetVisionResponse.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getVisionResponse", propOrder = { "getVisionResultSealed" })
@XmlRootElement(name = "getVisionResponse", namespace = "http:/services.recipe.be/patient")
public class GetVisionResponse implements Serializable {

	private final static long serialVersionUID = 1L;
	@XmlElement(name = "GetVisionResultSealed")
	protected byte[] getVisionResultSealed;

	public GetVisionResponse() {
	}

	/**
	 * Instantiates a new gets the vision response.
	 *
	 * @param getVisionResultSealed
	 *            the get get vision result sealed
	 */
	public GetVisionResponse(byte[] getVisionResultSealed) {
		this.getVisionResultSealed = getVisionResultSealed;
	}

	/**
	 * Gets the value of the getVisionResultSealed property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getGetVisionResultSealed() {
		return getVisionResultSealed;
	}

	/**
	 * Sets the value of the getVisionResultSealed property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setGetVisionResultSealed(byte[] value) {
		this.getVisionResultSealed = value;
	}
}