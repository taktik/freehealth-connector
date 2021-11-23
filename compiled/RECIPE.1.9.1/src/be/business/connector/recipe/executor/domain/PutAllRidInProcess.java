package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PutAllRidInProcess.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutAllRidInProcess")
public class PutAllRidInProcess {

	/** The put vision param sealed. */
	@XmlElement(name = "PutAllRidInProcessParamSealed")
	protected byte[] putAllRidInProcessParamSealed;

	/**
	 * Instantiates a new put all rid in process.
	 */
	public PutAllRidInProcess() {
	}

	/**
	 * @return the putAllRidInProcessParamsealed
	 */
	public byte[] getPutAllRidInProcessParamSealed() {
		return putAllRidInProcessParamSealed;
	}

	/**
	 * @param putAllRidInProcessParamsealed
	 *            the putAllRidInProcessParamsealed to set
	 */
	public void setPutAllRidInProcessParamsealed(byte[] putAllRidInProcessParamSealed) {
		this.putAllRidInProcessParamSealed = putAllRidInProcessParamSealed;
	}
}
