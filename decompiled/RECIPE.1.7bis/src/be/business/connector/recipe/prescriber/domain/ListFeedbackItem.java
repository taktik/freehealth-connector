package org.taktik.connector.business.recipe.prescriber.domain;

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
	 * @param linkedException the new linked exception
	 */
	public void setLinkedException(Throwable linkedException) {
		this.linkedException = linkedException;
	}

	/**
	 * Instantiates a new list feedback item.
	 *
	 * @param root the root
	 */
	public ListFeedbackItem(be.recipe.services.prescriber.ListFeedbackItem root) {
		super();
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return root.equals(obj);
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getContent()
	 */
	public byte[] getContent() {
		if( linkedException != null ){
			throw new RuntimeException(linkedException);
		}
		return root.getContent();
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getRid()
	 */
	public String getRid() {
		return root.getRid();
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getSentBy()
	 */
	public String getSentBy() {
		return root.getSentBy();
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getSentDate()
	 */
	public Calendar getSentDate() {
		return root.getSentDate();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return root.hashCode();
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setContent(byte[])
	 */
	public void setContent(byte[] arg0) {
		root.setContent(arg0);
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setRid(java.lang.String)
	 */
	public void setRid(String arg0) {
		root.setRid(arg0);
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setSentBy(java.lang.String)
	 */
	public void setSentBy(String arg0) {
		root.setSentBy(arg0);
	}

	/* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setSentDate(java.util.Calendar)
	 */
	public void setSentDate(Calendar arg0) {
		root.setSentDate(arg0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return root.toString();
	}
	
	
	
}
