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

package org.taktik.connector.business.recipe.prescriber

import org.taktik.connector.business.recipe.common.AbstractIntegrationModule
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.service.STSService
import be.fgov.ehealth.commons.core.v1.IdentifierType
import be.fgov.ehealth.commons.core.v1.StatusType
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.etee.crypto.utils.KeyManager

open class PrescriberIntegrationModuleImpl(val stsService: STSService, keyDepotService: KeyDepotService) : AbstractIntegrationModule(keyDepotService), PrescriberIntegrationModule {


}
