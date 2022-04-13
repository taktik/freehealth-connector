package be.business.connector.recipe.executor.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.business.connector.recipe.domain.Page;

/**
 * The Class ListPrescriptionHistory.
 *
 * @author bruno.casneuf
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPrescriptionHistoryParam")
public class ListPrescriptionHistoryParam {

	/** The symm key. */
	private byte[] symmKey;

	/** The patient id. */
	private String patientId;

	/** The page. */
	@NotNull
	@Valid
	private Page page;

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
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

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
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
}