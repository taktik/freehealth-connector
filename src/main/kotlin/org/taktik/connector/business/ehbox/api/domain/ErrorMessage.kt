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

import java.util.ArrayList

class ErrorMessage<T> : Message<T>() {
    var title: String? = null
    var errorPublicationId: String? = null
    var errorCode: String? = null
    val errorMsg: MutableList<String> = ArrayList()

    override fun toString(): String {
        return "ErrorMessage [getErrorTitle()=" + this.title + "]" + "Message [id=" + this.id + ", publicationId=" + this.publicationId + ", sender=" + this.sender + ", destinations=" + this.getDestinations() + ", important=" + this.isImportant + ", encrypted=" + this.isEncrypted + "]"
    }
}
