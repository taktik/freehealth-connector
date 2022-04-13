package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetAllReservations.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllReservations")
@XmlRootElement(namespace = "http://services.recipe.be")
public class GetAllReservations {

	/** The put vision param sealed. */
	@XmlElement(name = "GetAllReservationsParamSealed")
	protected byte[] getAllReservationsParamSealed;

	/**
	 * Instantiates a new gets the all reservations.
	 */
	public GetAllReservations() {
		super();
	}

	/**
	 * @return the getAllReservationsParamSealed
	 */
	public byte[] getGetAllReservationsParamSealed() {
		return getAllReservationsParamSealed;
	}

	/**
	 * @param getAllReservationsParamSealed
	 *            the getAllReservationsParamSealed to set
	 */
	public void setGetAllReservationsParamSealed(byte[] getAllReservationsParamSealed) {
		this.getAllReservationsParamSealed = getAllReservationsParamSealed;
	}
}