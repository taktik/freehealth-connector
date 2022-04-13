package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutReservationParam")
public class PutReservationParam {

	/** The symm key. */
	private byte[] symmKey;

	/** The rid. */
	private String rid;

	/** The executor id. */
	private String executorId;

	/**
	 * Gets the rid.
	 *
	 * @return the rid
	 */
	public String getRid() {
		return rid;
	}

	/**
	 * Gets the executor id.
	 *
	 * @return the executor id
	 */
	public String getExecutorId() {
		return executorId;
	}

	/**
	 * Instantiates a new put reservation.
	 *
	 * @param rid
	 *            the rid
	 * @param executorId
	 *            the executor id
	 */
	public PutReservationParam(String rid, String executorId) {
		super();
		this.rid = rid;
		this.executorId = executorId;
	}

	public PutReservationParam() {
		super();
	}

	/**
	 * Sets the symm key.
	 *
	 * @param symmKey
	 *            the new symm key
	 */
	public void setSymmKey(byte[] symmKey) {
		this.symmKey = symmKey;
	}

	/**
	 * Gets the symm key.
	 *
	 * @return the symm key
	 */
	public byte[] getSymmKey() {
		return symmKey;
	}
}
