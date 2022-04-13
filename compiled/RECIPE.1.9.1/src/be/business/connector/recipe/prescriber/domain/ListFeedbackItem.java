package be.business.connector.recipe.prescriber.domain;

import java.util.Calendar;

/**
 * The Class ListFeedbackItem.
 */
public class ListFeedbackItem extends be.recipe.services.prescriber.ListFeedbackItem {

	/** The root. */
	be.recipe.services.prescriber.ListFeedbackItem root = null;

	/** The linked exception. */
	Throwable linkedException = null;

	/**
	 * Gets the linked exception.
	 *
	 * @return the linked exception
	 */
	public Throwable getLinkedException() {
		return linkedException;
	}

	/**
	 * Sets the linked exception.
	 *
	 * @param linkedException
	 *            the new linked exception
	 */
	public void setLinkedException(Throwable linkedException) {
		this.linkedException = linkedException;
	}

	/**
	 * Instantiates a new list feedback item.
	 *
	 * @param root
	 *            the root
	 */
	public ListFeedbackItem(be.recipe.services.prescriber.ListFeedbackItem root) {
		super();
		this.root = root;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object obj) {
		return root.equals(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public byte[] getContent() {
		if (linkedException != null) {
			throw new RuntimeException(linkedException);
		}
		return root.getContent();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getRid() {
		return root.getRid();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSentBy() {
		return root.getSentBy();
	}

	/**
	 * {@inheritDoc}
	 */
	public Calendar getSentDate() {
		return root.getSentDate();
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return root.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setContent(byte[] arg0) {
		root.setContent(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setRid(String arg0) {
		root.setRid(arg0);
	}

	/**
	 * Sets the sent by.
	 *
	 * @param arg0
	 *            the new sent by
	 */
	public void setSentBy(String arg0) {
		root.setSentBy(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSentDate(Calendar arg0) {
		root.setSentDate(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return root.toString();
	}
}