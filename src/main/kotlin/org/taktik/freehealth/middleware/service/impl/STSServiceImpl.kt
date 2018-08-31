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

package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import com.hazelcast.core.IMap
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues.ERROR_CONFIG
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.sts.SAMLTokenFactory
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.service.sts.utils.SAMLHelper
import org.taktik.connector.technical.utils.CertificateParser
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.StringReader
import java.io.StringWriter
import java.security.KeyStore
import java.time.Instant
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

@Service
class STSServiceImpl(val keystoresMap: IMap<UUID, ByteArray>, val tokensMap: IMap<UUID, SamlTokenResult>) : STSService {
    val freehealthStsService: org.taktik.connector.technical.service.sts.STSService =
        org.taktik.connector.technical.service.sts.impl.STSServiceImpl()
    val freehealthKeyDepotService: org.taktik.connector.technical.service.keydepot.KeyDepotService =
        org.taktik.connector.technical.service.keydepot.impl.KeyDepotServiceImpl()
    val transformer = TransformerFactory.newInstance().newTransformer()

    override fun registerToken(tokenId: UUID, token: String) {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document = builder.parse( InputSource( StringReader(token)))
        val assertion = document.documentElement

        tokensMap[tokenId] =
            SamlTokenResult(tokenId, token, SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis)
    }

    override fun getSAMLToken(tokenId: UUID, keystoreId: UUID, passPhrase: String): SAMLToken? {
        return tokensMap[tokenId]?.let {
            val keyStore = getKeyStore(keystoreId, passPhrase)
            val result = DOMResult()
            transformer.transform(StreamSource(StringReader(it.token)), result)
            return SAMLTokenFactory.getInstance()
                .createSamlToken(
                    result.node.firstChild as Element,
                    KeyStoreCredential(keyStore, "authentication", passPhrase)
                )
        }
    }

    override fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String, //nihii for medical house and niss for doctor
        passPhrase: String,
        medicalHouse: Boolean,
        extraDesignators: List<Pair<String, String>>
    ): SamlTokenResult {
        val keyStore = getKeyStore(keystoreId, passPhrase)
        val credential = KeyStoreCredential(keyStore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keyStore, passPhrase.toCharArray())
        val etk = getHolderOfKeysEtk(credential)
        if (!hokPrivateKeys.containsKey(etk.certificate.serialNumber.toString(10))) {
            throw TechnicalConnectorException(
                ERROR_CONFIG,
                arrayOf<Any>("The certificate from the ETK don't match with the one in the encryption keystore")
            )
        }

        val designators = if (medicalHouse) listOf(
            SAMLAttributeDesignator(
                "urn:fgov:be:1.0:medicalhouse:nihii-number",
                "urn:be:fgov:identification-namespace"
            ),
            SAMLAttributeDesignator(
                "urn:fgov:be:1.0:certificateholder:medicalhouse:nihii-number",
                "urn:be:fgov:identification-namespace"
            ),
            SAMLAttributeDesignator(
                "urn:fgov:be:1.0:medicalhouse:nihii-number",
                "urn:be:fgov:certified-namespace:ehealth"
            ),
            SAMLAttributeDesignator(
                "urn:fgov:be:1.0:certificateholder:medicalhouse:nihii-number",
                "urn:be:fgov:certified-namespace:ehealth"
            )
        ) else listOf(
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin",
                "urn:be:fgov:identification-namespace"
            ),
            SAMLAttributeDesignator("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace"),
            SAMLAttributeDesignator(
                "urn:be:fgov:person:ssin:doctor:boolean",
                "urn:be:fgov:certified-namespace:ehealth"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:person:ssin:ehealth:1.0:doctor:nihii11",
                "urn:be:fgov:certified-namespace:ehealth"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:person:ssin:ehealth:1.0:fpsph:doctor:boolean",
                "urn:be:fgov:certified-namespace:ehealth"
            )
        ) + extraDesignators.map { SAMLAttributeDesignator(it.second, it.first) }

        val attributes = if (medicalHouse) listOf(
            SAMLAttribute(
                "urn:be:fgov:ehealth:1.0:medicalhouse:nihii-number",
                "urn:be:fgov:identification-namespace",
                nihiiOrSsin
            ),
            SAMLAttribute(
                "urn:be:fgov:ehealth:1.0:certificateholder:medicalhouse:nihii-number",
                "urn:be:fgov:identification-namespace",
                nihiiOrSsin
            )
        ) else listOf(
            SAMLAttribute(
                "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin",
                "urn:be:fgov:identification-namespace",
                nihiiOrSsin
            ), SAMLAttribute("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace", nihiiOrSsin)
        )

        val assertion =
            freehealthStsService.getToken(
                credential,
                credential,
                attributes,
                designators,
                "urn:oasis:names:tc:SAML:1.0:cm:holder-of-key",
                24
            )

        //Serialize
        val result = StreamResult(StringWriter())
        transformer.transform(DOMSource(assertion), result)
        val randomUUID = UUID.randomUUID()
        val samlToken = result.writer.toString()


        val samlTokenResult =
            SamlTokenResult(randomUUID, samlToken, SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis)
        tokensMap[randomUUID] = samlTokenResult
        return samlTokenResult
    }

    override fun checkTokenValid(tokenId: UUID): Boolean {
        return tokensMap.get(tokenId)?.let { (it.validity ?: 0) > Instant.now().toEpochMilli() } ?: false
    }

    override fun getHolderOfKeysEtk(credential: KeyStoreCredential): EncryptionToken {
        val cert = credential.certificate

        val parser = CertificateParser(cert)
        val identifierType = parser.identifier
        val identifierValue = parser.id
        val application = parser.application

        val etk = this.getEtk(identifierType, java.lang.Long.parseLong(identifierValue), application)
        return etk
    }

    override fun uploadKeystore(file: MultipartFile): UUID {
        val keystoreId = UUID.randomUUID()
        keystoresMap.put(keystoreId, file.bytes)
        return keystoreId
    }

    override fun uploadKeystore(data: ByteArray): UUID {
        val keystoreId = UUID.randomUUID()
        keystoresMap.put(keystoreId, data)
        return keystoreId
    }

    override fun getKeyStore(keystoreId: UUID, passPhrase: String): KeyStore? {
        val keyStoreData =
            keystoresMap.get(keystoreId)
                ?: throw(IllegalArgumentException("Missing Keystore, please upload a keystore and use the returned keystoreId"))
        val keyStore = KeyManager.getKeyStore(keyStoreData.inputStream(), "PKCS12", passPhrase.toCharArray())
        return keyStore
    }

    @Throws(TechnicalConnectorException::class)
    fun getEtk(identifierType: IdentifierType, identifierValue: Long, application: String): EncryptionToken {
        return this.freehealthKeyDepotService.getETKSet(
            identifierType,
            identifierType.formatIdentifierValue(identifierValue),
            application
        )?.let { if (it.size == 1) it.iterator().next() else null } ?: throw TechnicalConnectorException(
            ERROR_ETK_NOTFOUND,
            arrayOfNulls<Any>(0)
        )
    }

    override fun checkIfKeystoreExist(keystoreId: UUID): Boolean{
        return keystoresMap.get(keystoreId) != null
    }

}
