package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

/**
 * The Class PutVisionResult.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutVisionResult")
public class PutVisionResult extends ResponseType {
	/** The status. */
	private String responseStatus;

	/**
	 * Instantiates a new put vision result.
	 */
	public PutVisionResult() {
	}

	/**
	 * Instantiates a new put vision result.
	 *
	 * @param status
	 *            the status
	 */
	public PutVisionResult(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getResponseStatus() {
		return responseStatus;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
}
