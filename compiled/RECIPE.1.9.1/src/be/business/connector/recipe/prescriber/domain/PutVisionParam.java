package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PutVisionParam.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutVisionParam")
public class PutVisionParam {

	/** The symm key. */
	private byte[] symmKey;

	/** The rid. */
	private String rid;

	/** The vision. */
	private String vision;

	/**
	 * Instantiates a new put vision param.
	 */
	public PutVisionParam() {
		super();
	}

	/**
	 * Gets the rid.
	 *
	 * @return the rid
	 */
	public String getRid() {
		return rid;
	}

	/**
	 * Sets the rid.
	 *
	 * @param rid
	 *            the new rid
	 */
	public void setRid(String rid) {
		this.rid = rid;
	}

	/**
	 * Gets the vision.
	 *
	 * @return the vision
	 */
	public String getVision() {
		return vision;
	}

	/**
	 * Sets the vision.
	 *
	 * @param vision
	 *            the new vision
	 */
	public void setVision(String vision) {
		this.vision = vision;
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
