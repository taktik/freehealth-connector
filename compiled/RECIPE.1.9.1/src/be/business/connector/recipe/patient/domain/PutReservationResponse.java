package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PutReservationResponse.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutReservationResponse")
public class PutReservationResponse {

	/** The put vision result sealed. */
	@XmlElement(name = "PutReservationResultSealed")
	protected byte[] putReservationResultSealed;

	/**
	 * Instantiates a new put reservation response.
	 */
	public PutReservationResponse() {
		super();
	}

	/**
	 * @return the putReservationResultSealed
	 */
	public byte[] getPutReservationResultSealed() {
		return putReservationResultSealed;
	}

	/**
	 * @param putReservationResultSealed
	 *            the putReservationResultSealed to set
	 */
	public void setPutReservationResultSealed(byte[] putReservationResultSealed) {
		this.putReservationResultSealed = putReservationResultSealed;
	}
}