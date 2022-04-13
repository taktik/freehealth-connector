package be.business.connector.recipe.executor.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class Reservation.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation")
public class Reservation {

	/** The rid. */
	private String rid;

	/** The creation date. */
	private Date creationDate;

	/** The lastupdate. */
	private Date lastupdate;

	/**
	 * Instantiates a new reservation.
	 */
	public Reservation() {
		super();
	}

	/**
	 * Gets the rid.
	 *
	 * @return the rid
	 */
	public String getRid() {
		return rid;
	}

	/**
	 * Sets the rid.
	 *
	 * @param rid
	 *            the new rid
	 */
	public void setRid(String rid) {
		this.rid = rid;
	}

	// /**
	// * Gets the executorid.
	// *
	// * @return the executorid
	// */
	// public String getExecutorid() {
	// return executorid;
	// }
	//
	// /**
	// * Sets the executorid.
	// *
	// * @param executorid
	// * the new executorid
	// */
	// public void setExecutorid(String executorid) {
	// this.executorid = executorid;
	// }

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the lastupdate.
	 *
	 * @return the lastupdate
	 */
	public Date getLastupdate() {
		return lastupdate;
	}

	/**
	 * Sets the lastupdate.
	 *
	 * @param lastupdate
	 *            the new lastupdate
	 */
	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}
}