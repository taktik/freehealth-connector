package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetAllRidInProcess.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllRidInProcess")
public class GetAllRidInProcess {

	@XmlElement(name = "GetAllRidInProcessParamSealed")
	protected byte[] getAllRidInProcessParamSealed;

	/**
	 * Instantiates a new gets the all rid in process.
	 */
	public GetAllRidInProcess() {
		super();
	}

	/**
	 * @return the getAllRidInProcessParam
	 */
	public byte[] getGetAllRidInProcessParamSealed() {
		return getAllRidInProcessParamSealed;
	}

	/**
	 * @param getAllRidInProcessParam
	 *            the getAllRidInProcessParam to set
	 */
	public void setGetAllRidInProcessParam(byte[] getAllRidInProcessParamSealed) {
		this.getAllRidInProcessParamSealed = getAllRidInProcessParamSealed;
	}
}