package be.business.connector.recipe.patient.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class ListOpenPrescriptionsResponse.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOpenPrescriptionsResponse")
public class ListOpenPrescriptionsResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private final static long serialVersionUID = 1L;

	/** The list open prescriptions result sealed. */
	@XmlElement(name = "ListOpenPrescriptionsResultSealed")
	protected byte[] listOpenPrescriptionsResultSealed;

	/**
	 * Instantiates a new list open prescriptions response.
	 */
	public ListOpenPrescriptionsResponse() {
		super();
	}

	/**
	 * Instantiates a new list open prescriptions response.
	 *
	 * @param listOpenPrescriptionsResultSealed
	 *            the list open prescriptions result sealed
	 */
	public ListOpenPrescriptionsResponse(final byte[] listOpenPrescriptionsResultSealed) {
		this.listOpenPrescriptionsResultSealed = listOpenPrescriptionsResultSealed;
	}

	/**
	 * Gets the list open prescriptions result sealed.
	 *
	 * @return the list open prescriptions result sealed
	 */
	public byte[] getListOpenPrescriptionsResultSealed() {
		return listOpenPrescriptionsResultSealed;
	}

	/**
	 * Sets the list open prescriptions result sealed.
	 *
	 * @param listOpenPrescriptionsResultSealed
	 *            the new list open prescriptions result sealed
	 */
	public void setListOpenPrescriptionsResultSealed(final byte[] listOpenPrescriptionsResultSealed) {
		this.listOpenPrescriptionsResultSealed = listOpenPrescriptionsResultSealed;
	}
}