package be.business.connector.recipe.executor.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutAllRidInProcessParam")
public class PutAllRidInProcessParam {
	/** The rids. */
	private List<String> rids;

	/** The symm key. */
	private byte[] symmKey;

	/**
	 * Instantiates a new put all rid in process.
	 */
	public PutAllRidInProcessParam() {
	}

	/**
	 * Instantiates a new put all rid in process.
	 *
	 * @param rids
	 *            the rids
	 * @param executorId
	 *            the executor id
	 */
	public PutAllRidInProcessParam(List<String> rids) {
		this.rids = rids;
	}

	/**
	 * Gets the rids.
	 *
	 * @return the rids
	 */
	public List<String> getRids() {
		return rids;
	}

	/**
	 * Sets the rids.
	 *
	 * @param rids
	 *            the new rids
	 */
	public void setRids(List<String> rids) {
		this.rids = rids;
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

	// /**
	// * Gets the executor id.
	// *
	// * @return the executor id
	// */
	// public String getExecutorId() {
	// return executorId;
	// }
	//
	// /**
	// * Sets the executor id.
	// *
	// * @param executorId
	// * the new executor id
	// */
	// public void setExecutorId(String executorId) {
	// this.executorId = executorId;
	// }
}
