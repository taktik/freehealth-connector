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

package org.taktik.connector.business.ehbox.v3.exception

import org.taktik.connector.business.ehbox.api.domain.Message
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.exception.UnsealConnectorException
import java.util.ArrayList

class EhboxCryptoException : TechnicalConnectorException {
    var ehBoxMessage: Message<*>? = null
        private set
    private var exceptions: MutableList<UnsealConnectorException>? = null

    constructor(exceptions: MutableList<UnsealConnectorException>, ehBoxMessage: Message<*>) : super(
        TechnicalConnectorExceptionValues.ERROR_CRYPTO,
        "Unable to decrypt ehbox message."
    ) {
        this.exceptions = exceptions
        this.ehBoxMessage = ehBoxMessage
    }

    constructor(exception: UnsealConnectorException, ehBoxMessage: Message<*>?) : super(
        TechnicalConnectorExceptionValues.ERROR_CRYPTO,
        exception as Throwable,
        "Unable to decrypt ehbox message."
    ) {
        this.exceptions = ArrayList()
        this.exceptions!!.add(exception)
        this.ehBoxMessage = ehBoxMessage
    }

    fun getExceptions(): List<UnsealConnectorException>? {
        return this.exceptions
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
