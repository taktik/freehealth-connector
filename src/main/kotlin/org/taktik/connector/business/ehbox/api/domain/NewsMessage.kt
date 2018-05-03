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

package org.taktik.connector.business.ehbox.api.domain

import org.taktik.connector.technical.enumeration.MimeType
import org.slf4j.LoggerFactory

class NewsMessage<T> : DocumentMessage<T>() {
    var news: Document?
        @Deprecated("") get() = this.document
        @Deprecated("") set(news) {
            this.document = news
        }

    init {
        val document = Document()
        document.mimeType = MimeType.plaintext.value
        this.document = document
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(NewsMessage::class.java)
    }
}
