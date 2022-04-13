package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllRidInProcessParam")
public class GetAllRidInProcessParam {
	/** The symm key. */
	private byte[] symmKey;

	/** The executor id. */
	private String executorId;

	public GetAllRidInProcessParam() {
	}

	public GetAllRidInProcessParam(String executorId) {
		this.executorId = executorId;
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

	/**
	 * Gets the symm key.
	 *
	 * @return the symm key
	 */
	public byte[] getSymmKey() {
		return symmKey;
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
	 * Sets the executor id.
	 *
	 * @param executorId
	 *            the new executor id
	 */
	public void setExecutorId(final String executorId) {
		this.executorId = executorId;
	}
}
