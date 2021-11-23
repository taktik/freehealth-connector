package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetVisionResult")
public class GetVisionResult extends ResponseType {

	/** The vision. */
	private String vision;

	/**
	 * Gets the vision.
	 *
	 * @return the vision
	 */
	public String getVision() {
		return vision;
	}

	/**
	 * Sets the vision.
	 *
	 * @param vision
	 *            the new vision
	 */
	public void setVision(String vision) {
		this.vision = vision;
	}
}