package be.business.connector.recipe.prescriber.domain;

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
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllRidInProcess")
public class GetAllRidInProcess {

	@XmlElement(name = "GetAllRidInProcessParam")
	protected byte[] getAllRidInProcessParam;

	/**
	 * Instantiates a new gets the all rid in process.
	 */
	public GetAllRidInProcess() {
		super();
	}

	/**
	 * @return the getAllRidInProcessParam
	 */
	public byte[] getGetAllRidInProcessParam() {
		return getAllRidInProcessParam;
	}

	/**
	 * @param getAllRidInProcessParam
	 *            the getAllRidInProcessParam to set
	 */
	public void setGetAllRidInProcessParam(byte[] getAllRidInProcessParam) {
		this.getAllRidInProcessParam = getAllRidInProcessParam;
	}
}