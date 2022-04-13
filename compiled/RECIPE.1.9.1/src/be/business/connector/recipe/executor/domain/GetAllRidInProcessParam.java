package be.business.connector.recipe.executor.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.business.connector.recipe.domain.Page;

@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllRidInProcessParam")
public class GetAllRidInProcessParam {
	/** The symm key. */
	private byte[] symmKey;

	/** The page. */
	@NotNull
	@Valid
	private Page page;

	public GetAllRidInProcessParam() {
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
}