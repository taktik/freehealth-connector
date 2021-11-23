package be.business.connector.recipe.patient.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

/**
 * The Class ListOpenPrescriptionsResult.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOpenPrescriptionsResult")
public class ListOpenPrescriptionsResult extends ResponseType {

	/** The items. */
	@XmlElement
	private List<String> items;

	public ListOpenPrescriptionsResult() {
	}

	/**
	 * Instantiates a new list open prescriptions result.
	 *
	 * @param items
	 *            the items
	 */
	public ListOpenPrescriptionsResult(final List<String> items) {
		this.items = items;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items
	 *            the new items
	 */
	public void setItems(final List<String> items) {
		this.items = items;
	}
}