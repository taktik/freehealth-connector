package be.business.connector.recipe.patient.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * The Class ListAddressedPrescriptionResult.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListAddressedPrescriptionItem")
public class ListPatientPrescriptionItem {

	/** The rid. */
	private String rid = null;

	/** The prescriber label. */
	private String prescriberLabel = null;

	/** The creation date. */
	private Date creationDate = null;

	/** The prescriber id. */
	private String prescriberId = null;

	/**
	 * Instantiates a new list addressed prescription result.
	 *
	 */
	public ListPatientPrescriptionItem() {
	}

	/**
	 * Instantiates a new list patient prescription item.
	 *
	 * @param rid
	 *            the rid
	 * @param prescriberLabel
	 *            the prescriber label
	 * @param creationDate
	 *            the creation date
	 * @param prescriberId
	 *            the prescriber id
	 */
	public ListPatientPrescriptionItem(String rid, String prescriberLabel, Date creationDate, String prescriberId) {
		super();
		this.rid = rid;
		this.prescriberLabel = prescriberLabel;
		this.creationDate = new Date(creationDate.getTime());
		this.prescriberId = prescriberId;
	}

	// /**
	// * Instantiates a new list patient prescription item.
	// *
	// * @param prescription
	// * the prescription
	// */
	// public ListPatientPrescriptionItem(Prescription prescription) {
	// super();
	// this.rid = prescription.getRid();
	// // TODO : fetch the label of the prescriber from MAzDA
	// this.prescriberId = prescription.getPrescriberId().toString();
	// this.creationDate = prescription.getCreationDate();
	// this.prescriberLabel = prescription.getPrescriberLabel();
	// }

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
	 * Gets the prescriber label.
	 *
	 * @return the prescriber label
	 */
	public String getPrescriberLabel() {
		return prescriberLabel;
	}

	/**
	 * Sets the prescriber label.
	 *
	 * @param prescriberLabel
	 *            the new prescriber label
	 */
	public void setPrescriberLabel(String prescriberLabel) {
		this.prescriberLabel = prescriberLabel;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return new Date(creationDate.getTime());
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = new Date(creationDate.getTime());
	}

	/**
	 * Sets the prescriber id.
	 *
	 * @param prescriberId
	 *            the prescriberId to set
	 */
	public void setPrescriberId(String prescriberId) {
		this.prescriberId = prescriberId;
	}

	/**
	 * Gets the prescriber id.
	 *
	 * @return the prescriberId
	 */
	public String getPrescriberId() {
		return prescriberId;
	}
}
