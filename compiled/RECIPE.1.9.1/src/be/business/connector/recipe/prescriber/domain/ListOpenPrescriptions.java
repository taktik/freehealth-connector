package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class ListOpenPrescriptions.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOpenPrescriptions")
public class ListOpenPrescriptions {

	/** The list open prescriptions param sealed. */
	@XmlElement(name = "ListOpenPrescriptionsParamSealed")
	protected byte[] listOpenPrescriptionsParamSealed;

	/**
	 * Instantiates a new list open prescriptions.
	 */
	public ListOpenPrescriptions() {
		super();
	}

	/**
	 * Gets the list open prescriptions param sealed.
	 *
	 * @return the list open prescriptions param sealed
	 */
	public byte[] getListOpenPrescriptionsParamSealed() {
		return listOpenPrescriptionsParamSealed;
	}

	/**
	 * Sets the list open prescriptions param sealed.
	 *
	 * @param listOpenPrescriptionsParamSealed
	 *            the new list open prescriptions param sealed
	 */
	public void setListOpenPrescriptionsParamSealed(final byte[] listOpenPrescriptionsParamSealed) {
		this.listOpenPrescriptionsParamSealed = listOpenPrescriptionsParamSealed;
	}
}