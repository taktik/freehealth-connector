package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetAllReservationsResponse.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllReservationsResponse")
@XmlRootElement(namespace = "http://services.recipe.be")
public class GetAllReservationsResponse {

	/** The Constant serialVersionUID. */
	private final static long serialVersionUID = 1L;

	/** The put vision result sealed. */
	@XmlElement(name = "GetAllReservationsResultSealed")
	protected byte[] getAllReservationsResultSealed;

	/**
	 * Instantiates a new gets the all reservations response.
	 */
	public GetAllReservationsResponse() {
		super();
	}

	/**
	 * @return the getAllReservationsResultSealed
	 */
	public byte[] getGetAllReservationsResultSealed() {
		return getAllReservationsResultSealed;
	}

	/**
	 * @param getAllReservationsResultSealed
	 *            the getAllReservationsResultSealed to set
	 */
	public void setGetAllReservationsResultSealed(byte[] getAllReservationsResultSealed) {
		this.getAllReservationsResultSealed = getAllReservationsResultSealed;
	}
}