package be.business.connector.recipe.executor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

/**
 * The Class GetAllReservationsResult.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllReservationsResult")
@XmlRootElement(namespace = "http://services.recipe.be")
public class GetAllReservationsResult extends ResponseType {

	/** The reservations. */
	private List<Reservation> reservations;

	public GetAllReservationsResult() {
		super();
	}

	/**
	 * Instantiates a new gets the all reservations result.
	 *
	 * @param reservations
	 *            the reservations
	 */
	public GetAllReservationsResult(List<Reservation> reservations) {
		super();
		this.reservations = reservations;
	}

	/**
	 * Adds the.
	 *
	 * @param rid
	 *            the rid
	 */
	public void add(final Reservation rid) {
		if (reservations == null) {
			reservations = new ArrayList<Reservation>();
		}
		reservations.add(rid);
	}

	/**
	 * Gets the reservations.
	 *
	 * @return the reservations
	 */
	public List<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * Sets the reservations.
	 *
	 * @param reservations
	 *            the new reservations
	 */
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
}