package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class GetPrescriptionStatusParam.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
public class GetPrescriptionStatusParam {

	/** The symm key. */
	private byte[] symmKey;

	/** The rid. */
	private String rid;

	/**
	 * Instantiates a new gets the prescription status param.
	 */
	public GetPrescriptionStatusParam() {
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
	 * Sets the symm key.
	 *
	 * @param symmKey
	 *            the new symm key
	 */
	public void setSymmKey(final byte[] symmKey) {
		this.symmKey = symmKey;
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
	 * Gets the rid.
	 *
	 * @return the rid
	 */
	public String getRid() {
		return rid;
	}
}
