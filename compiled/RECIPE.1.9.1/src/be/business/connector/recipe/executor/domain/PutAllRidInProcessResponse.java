package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PutAllRidInProcessResponse.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutAllRidInProcessResponse")
public class PutAllRidInProcessResponse {

	/** The put vision result sealed. */
	@XmlElement(name = "PutAllRidInProcessResultSealed")
	protected byte[] putAllRidInProcessResultSealed;

	/**
	 * Instantiates a new put all rid in process response.
	 */
	public PutAllRidInProcessResponse() {
		super();
	}

	/**
	 * @return the putAllRidInProcessResultSealed
	 */
	public byte[] getPutAllRidInProcessResultSealed() {
		return putAllRidInProcessResultSealed;
	}

	/**
	 * @param putAllRidInProcessResultSealed
	 *            the putAllRidInProcessResultSealed to set
	 */
	public void setPutAllRidInProcessResultSealed(byte[] putAllRidInProcessResultSealed) {
		this.putAllRidInProcessResultSealed = putAllRidInProcessResultSealed;
	}

}