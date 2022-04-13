package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.business.connector.recipe.domain.PrescriptionStatus;

/**
 * The Class ListPrescriptionHistoryItem.
 *
 * @author bruno.casneuf
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPrescriptionHistoryItem")
public class ListPrescriptionHistoryItem {

	/** The rid. */
	private String rid;

	/** The status. */
	private PrescriptionStatus prescriptionStatus;

	/**
	 * Instantiates a new list prescription history item.
	 */
	public ListPrescriptionHistoryItem() {
		super();
	}

	/**
	 * Instantiates a new list prescription history item.
	 *
	 * @param status
	 *            the status
	 * @param rid
	 *            the rid
	 */
	public ListPrescriptionHistoryItem(String rid, PrescriptionStatus prescriptionStatus) {
		super();
		this.rid = rid;
		this.prescriptionStatus = prescriptionStatus;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public PrescriptionStatus getPrescriptionStatus() {
		return prescriptionStatus;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
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
}