package be.business.connector.recipe.prescriber.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.business.connector.recipe.domain.Page;

/**
 * The Class ListOpenPrescriptionsParam.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOpenPrescriptionsParam")
public class ListOpenPrescriptionsParam {

	/** The patient id. */
	private String patientId;

	// /** The prescriber id. */
	// private String prescriberId;

	/** The page. */
	@NotNull
	@Valid
	private Page page;

	/** The symm key. */
	private byte[] symmKey;

	/**
	 * Gets the patient id.
	 *
	 * @return the patient id
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * Sets the patient id.
	 *
	 * @param patientId
	 *            the new patient id
	 */
	public void setPatientId(final String patientId) {
		this.patientId = patientId;
	}

	// /**
	// * Gets the prescriber id.
	// *
	// * @return the prescriber id
	// */
	// public String getPrescriberId() {
	// return prescriberId;
	// }
	//
	// /**
	// * Sets the prescriber id.
	// *
	// * @param prescriberId the new prescriber id
	// */
	// public void setPrescriberId(final String prescriberId) {
	// this.prescriberId = prescriberId;
	// }

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page
	 *            the new page
	 */
	public void setPage(final Page page) {
		this.page = page;
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
}
