/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.connector.business.recipe.prescriber.domain

import java.util.Calendar

/**
 * The Class ListFeedbackItem.
 */
class ListFeedbackItem(val root: be.recipe.services.prescriber.ListFeedbackItem) : be.recipe.services.prescriber.ListFeedbackItem() {

    /** The root.  */

    /** The linked exception.  */
    /**
     * Gets the linked exception.
     *
     * @return the linked exception
     */
    /**
     * Sets the linked exception.
     *
     * @param linkedException the new linked exception
     */
    var linkedException: Throwable? = null

    /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
    override fun equals(obj: Any?): Boolean {
        return root == obj
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getContent()
	 */
    override fun getContent(): ByteArray {
        if (linkedException != null) {
            throw RuntimeException(linkedException)
        }
        return root.content
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getRid()
	 */
    override fun getRid(): String {
        return root.rid
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getSentBy()
	 */
    override fun getSentBy(): String {
        return root.sentBy
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#getSentDate()
	 */
    override fun getSentDate(): Calendar {
        return root.sentDate
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
    override fun hashCode(): Int {
        return root.hashCode()
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setContent(byte[])
	 */
    override fun setContent(arg0: ByteArray) {
        root.content = arg0
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setRid(java.lang.String)
	 */
    override fun setRid(arg0: String) {
        root.rid = arg0
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setSentBy(java.lang.String)
	 */
    override fun setSentBy(arg0: String) {
        root.sentBy = arg0
    }

    /* (non-Javadoc)
	 * @see be.recipe.services.prescriber.ListFeedbackItem#setSentDate(java.util.Calendar)
	 */
    override fun setSentDate(arg0: Calendar) {
        root.sentDate = arg0
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return root.toString()
    }
}
