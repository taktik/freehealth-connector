package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetVision.
 *
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetVision")
public class GetVision {

	@XmlElement(name = "GetVisionParamSealed")
	protected byte[] getVisionParamSealed;

	/**
	 * Instantiates a new gets the vision.
	 */
	public GetVision() {
		super();
	}

	public byte[] getGetVisionParamSealed() {
		return getVisionParamSealed;
	}

	public void setGetVisionParamSealed(byte[] getVisionParamSealed) {
		this.getVisionParamSealed = getVisionParamSealed;
	}
}