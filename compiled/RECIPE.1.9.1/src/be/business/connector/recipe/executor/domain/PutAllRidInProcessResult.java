package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutAllRidInProcessResult")
public class PutAllRidInProcessResult extends ResponseType {

	/** The status. */
	private String statusMessage;

	/**
	 * Instantiates a new put all rid in process result.
	 */
	public PutAllRidInProcessResult() {
		super();
	}

	/**
	 * Instantiates a new put all rid in process result.
	 *
	 * @param status
	 *            the status
	 */
	public PutAllRidInProcessResult(String statusMessage) {
		super();
		this.statusMessage = statusMessage;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatusMesage() {
		return statusMessage;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatusMesage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
