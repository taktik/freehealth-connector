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

package org.taktik.connector.business.ehbox.v3.validator

import org.taktik.connector.business.ehbox.v3.validator.impl.EhboxReplyValidatorImpl
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper

class ValidatorFactory private constructor() {

    init {
        throw UnsupportedOperationException()
    }

    companion object {
        private val PROP_EHBOX_VALIDATOR = "org.taktik.connector.business.ehbox.v3.validator"
        private val factory = ConfigurableFactoryHelper<EhboxReplyValidatorImpl>(PROP_EHBOX_VALIDATOR, EhboxReplyValidatorImpl::class.java.name)

        val validator: EhboxReplyValidator
            @Throws(TechnicalConnectorException::class)
            get() = factory.implementation
    }
}
