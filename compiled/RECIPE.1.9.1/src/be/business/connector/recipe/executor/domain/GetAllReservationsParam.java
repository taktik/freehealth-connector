package be.business.connector.recipe.executor.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.business.connector.recipe.domain.Page;

/**
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllReservationsParam")
@XmlRootElement(namespace = "http://services.recipe.be")
public class GetAllReservationsParam {

	/** The symm key. */
	private byte[] symmKey;

	/** The set of date. */
	private Date startDate;

	/** The page. */
	@Valid
	private Page page;

	/**
	 * Instantiates a new gets the all reservations param.
	 */
	public GetAllReservationsParam() {
		super();
	}

	/**
	 * Gets the symm key.
	 *
	 * @return the symm key
	 */
	public byte[] getSymmKey() {
		return symmKey;
	}

	/**
	 * Sets the symm key.
	 *
	 * @param symmKey
	 *            the new symm key
	 */
	public void setSymmKey(final byte[] symmKey) {
		this.symmKey = symmKey;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
