package be.business.connector.recipe.executor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

/**
 * The Class ListPrescriptionHistoryResult.
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPrescriptionHistoryResult")
public class ListPrescriptionHistoryResult extends ResponseType {

	/** The items. */
	@XmlElement
	private List<ListPrescriptionHistoryItem> items;

	/**
	 * Instantiates a new list prescription history result.
	 */
	public ListPrescriptionHistoryResult() {
		super();
	}

	/**
	 * Instantiates a new list prescription history response.
	 *
	 * @param items
	 *            the items
	 */
	public ListPrescriptionHistoryResult(List<ListPrescriptionHistoryItem> items) {
		super();
		this.items = items;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public List<ListPrescriptionHistoryItem> getItems() {
		return items;
	}

	/**
	 * Sets the item.
	 *
	 * @param items
	 *            the new item
	 */
	public void setItem(List<ListPrescriptionHistoryItem> items) {
		this.items = items;
	}

	/**
	 * Adds the.
	 *
	 * @param item
	 *            the item
	 */
	public void add(ListPrescriptionHistoryItem item) {
		if (items == null) {
			items = new ArrayList<ListPrescriptionHistoryItem>();
		}
		items.add(item);
	}
}
