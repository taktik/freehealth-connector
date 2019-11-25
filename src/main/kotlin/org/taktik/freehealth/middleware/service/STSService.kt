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
    fun uploadKeystore(data: ByteArray): UUID
    fun uploadKeystore(file: MultipartFile): UUID
    fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String,
        passPhrase: String,
        medicalHouse: Boolean = false,
        guardPost: Boolean = false,
        tokenId: UUID? = null,
        extraDesignators: List<Pair<String, String>> = listOf()
    ): SamlTokenResult?

    fun registerToken(tokenId: UUID, token: String)
    fun getSAMLToken(tokenId: UUID, keystoreId: UUID, passPhrase: String): SAMLToken?
    fun getKeyStore(keystoreId: UUID, passPhrase: String): KeyStore?
    fun checkIfKeystoreExist(keystoreId: UUID): Boolean
    fun getHolderOfKeysEtk(credential: KeyStoreCredential, nihiiOrSsin: String?): EncryptionToken?
    fun checkTokenValid(tokenId: UUID): Boolean
    fun getKeystoreInfo(keystoreId: UUID, passPhrase: String): CertificateInfo
}
