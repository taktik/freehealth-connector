package be.business.connector.recipe.prescriber.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class GetAllRidInProcessResponse.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllRidInProcessResponse")
public class GetAllRidInProcessResponse implements Serializable {

	private final static long serialVersionUID = 1L;

	@XmlElement(name = "GetAllRidInProcessResultSealed")
	protected byte[] getAllRidInProcessResultSealed;

	/**
	 * Instantiates a new gets the all rid in process response.
	 */
	public GetAllRidInProcessResponse() {
		super();
	}

	/**
	 * @return the getAllRidInProcessResultSealed
	 */
	public byte[] getGetAllRidInProcessResultSealed() {
		return getAllRidInProcessResultSealed;
	}

	/**
	 * @param getAllRidInProcessResultSealed
	 *            the getAllRidInProcessResultSealed to set
	 */
	public void setGetAllRidInProcessResultSealed(byte[] getAllRidInProcessResultSealed) {
		this.getAllRidInProcessResultSealed = getAllRidInProcessResultSealed;
	}

}