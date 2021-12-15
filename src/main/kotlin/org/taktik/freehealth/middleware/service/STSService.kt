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

package org.taktik.freehealth.middleware.service

import org.springframework.web.multipart.MultipartFile
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.dto.CertificateInfo
import java.security.KeyStore
import java.util.UUID

interface STSService {
    fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String,
        passPhrase: String,
        quality: String = "doctor",
        tokenId: UUID? = null,
        extraDesignators: List<Pair<String, String>> = listOf(),
        keyStore: KeyStore? = null
    ): SamlTokenResult

    fun getHolderOfKeysEtk(credential: KeyStoreCredential, nihiiOrSsin: String?): EncryptionToken?
}
