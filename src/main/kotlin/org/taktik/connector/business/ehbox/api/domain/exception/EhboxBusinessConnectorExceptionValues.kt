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

package org.taktik.connector.business.ehbox.api.domain.exception

enum class EhboxBusinessConnectorExceptionValues private constructor(val errorCode: String, val message: String) {
    ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM("error.ehbox.document.outputstream", "Error occured while writing file {0}"),
    INVALID_EHBOX_NEWS_NEWSTITLE("invalid.ehbox.news.newstitle", "News title can not be null."),
    ERROR_BUSINESS_CODE_REASON("error.business.code.reason", "Error while processing business call (code={0}): {1}"),
    NO_QUALITY_SET("no.quality.set", "No quality has been set"),
    CRYPTO_NOT_PROPERLY_INITIALIZED("crypto.not.properly.initialized", "Crypto has'nt been properly initialized"),
    PROPERTY_MISSING("property.missing", "The required property [{0}] is missing")
}
