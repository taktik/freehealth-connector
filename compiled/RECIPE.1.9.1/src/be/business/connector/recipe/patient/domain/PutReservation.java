package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PutReservation.
 *
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutReservation")
public class PutReservation {

	/** The put vision param sealed. */
	@XmlElement(name = "PutReservationParamSealed")
	protected byte[] putReservationParamSealed;

	public PutReservation() {
		super();
	}

	/**
	 * @return the putReservationParamSealed
	 */
	public byte[] getPutReservationParamSealed() {
		return putReservationParamSealed;
	}

	/**
	 * @param putReservationParamSealed
	 *            the putReservationParamSealed to set
	 */
	public void setPutReservationParamSealed(byte[] putReservationParamSealed) {
		this.putReservationParamSealed = putReservationParamSealed;
	}
}