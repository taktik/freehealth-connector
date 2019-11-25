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
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.hazelcast.core.IMap
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues.ERROR_CONFIG
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sts.SAMLTokenFactory
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.service.sts.utils.SAMLHelper
import org.taktik.connector.technical.utils.CertificateParser
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.dto.CertificateInfo
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.StringReader
import java.io.StringWriter
import java.security.KeyStore
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

@Service
class STSServiceImpl(val keystoresMap: IMap<UUID, ByteArray>, val tokensMap: IMap<UUID, SamlTokenResult>, val keyDepotService: KeyDepotService) : STSService {
    private val log = LogFactory.getLog(this.javaClass)

    val freehealthStsService: org.taktik.connector.technical.service.sts.STSService =
        org.taktik.connector.technical.service.sts.impl.STSServiceImpl()
    val transformerFactory = TransformerFactory.newInstance()

    override fun registerToken(tokenId: UUID, token: String) {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document = builder.parse( InputSource( StringReader(token)))
        val assertion = document.documentElement

        tokensMap[tokenId] =
            SamlTokenResult(tokenId, token, System.currentTimeMillis(), SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis)
        log.info("tokensMap size: ${tokensMap.size}")
    }

    override fun getSAMLToken(tokenId: UUID, keystoreId: UUID, passPhrase: String): SAMLToken? {
        return tokensMap[tokenId]?.let {
            val keyStore = getKeyStore(keystoreId, passPhrase)
            val result = DOMResult()
            transformerFactory.newTransformer().transform(StreamSource(StringReader(it.token!!)), result)
            return result.node?.firstChild?.let {el ->
                SAMLTokenFactory.getInstance()
                    .createSamlToken(el as Element, KeyStoreCredential(keystoreId, keyStore, "authentication", passPhrase))
            }
        }
    }

    override fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String, //nihii for medical house and niss for doctor
        passPhrase: String,
        medicalHouse: Boolean,
        guardPost: Boolean,
        tokenId: UUID?,
        extraDesignators: List<Pair<String, String>>
    ): SamlTokenResult? {
        val now = System.currentTimeMillis()

        val currentToken = tokenId?.let { id -> tokensMap[id]}
        val isStillRecommendedForUse = currentToken?.let {
            val valid = it.validity
            val ts = it.timestamp

            if (valid == null || ts == null) {
                false
            } else {
                val totalValidity = valid - ts
                val remainingValidity = valid - now
                remainingValidity > 0 && totalValidity > 0 && remainingValidity.toDouble() / totalValidity.toDouble() > 0.5
            }
        } ?: false

        if (isStillRecommendedForUse) return currentToken

        val keyStore = getKeyStore(keystoreId, passPhrase)
        val credential = KeyStoreCredential(keystoreId, keyStore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keyStore, passPhrase.toCharArray())
        val etk = getHolderOfKeysEtk(credential, nihiiOrSsin)
        if (!hokPrivateKeys.containsKey(etk?.certificate?.serialNumber?.toString(10))) {
            throw TechnicalConnectorException(
                ERROR_CONFIG,"The certificate from the ETK don't match with the one in the encryption keystore"
            )
        }

        val designators = if (medicalHouse) listOf(
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:medicalhouse:nihii-number",
                "urn:be:fgov:identification-namespace"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:certificateholder:medicalhouse:nihii-number",
                "urn:be:fgov:identification-namespace"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:certificateholder:medicalhouse:nihii-number",
                "urn:be:fgov:certified-namespace:ehealth"
            )
            ,
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:medicalhouse:nihii-number:recognisedmedicalhouse:nihii11",
                "urn:be:fgov:certified-namespace:ehealth"
            )
        ) else if (guardPost) listOf(
            SAMLAttributeDesignator(
            "urn:be:fgov:ehealth:1.0:guardpost:nihii-number",
            "urn:be:fgov:identification-namespace"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:certificateholder:guardpost:nihii-number",
                "urn:be:fgov:identification-namespace"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:certificateholder:guardpost:nihii-number",
                "urn:be:fgov:certified-namespace:ehealth"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:guardpost:nihii-number:recognisedguardpost:nihii11",
                "urn:be:fgov:certified-namespace:ehealth"
            ),
            SAMLAttributeDesignator(
                "urn:be:fgov:ehealth:1.0:guardpost:nihii-number:recognisedguardpost:boolean",
                "urn:be:fgov:certified-namespace:ehealth"
            )
        )
        else listOf(
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
        ) else if (guardPost) listOf(
            SAMLAttribute(
                "urn:be:fgov:ehealth:1.0:guardpost:nihii-number",
                "urn:be:fgov:identification-namespace",
                nihiiOrSsin
            ),
            SAMLAttribute(
                "urn:be:fgov:ehealth:1.0:certificateholder:guardpost:nihii-number",
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

        return try {
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
            transformerFactory.newTransformer().transform(DOMSource(assertion), result)
            val randomUUID = UUID.randomUUID()
            val samlToken = result.writer.toString()

            val samlTokenResult =
                SamlTokenResult(randomUUID, samlToken, now, SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis)
            tokensMap[randomUUID] = samlTokenResult
            log.info("tokensMap size: ${tokensMap.size}")

            samlTokenResult
        } catch(e:TechnicalConnectorException) {
            log.info("STS token request failure: ${e.errorCode} : ${e.message} : ${e.stackTrace}")
            currentToken
        }
    }

    override fun getKeystoreInfo(keystoreId: UUID, passPhrase: String): CertificateInfo {
        val keyStore = getKeyStore(keystoreId, passPhrase)
        val credential = KeyStoreCredential(keystoreId, keyStore, "authentication", passPhrase)
        val parser = CertificateParser(credential.certificate)

        return CertificateInfo(credential.certificate.notAfter.time, parser.type, parser.id, parser.application, parser.owner)
    }

    override fun checkTokenValid(tokenId: UUID): Boolean {
        return tokensMap[tokenId]?.let { (it.token?.length ?: 0) > 0 && (it.validity ?: 0) > Instant.now().toEpochMilli() } ?: false
    }

    override fun getHolderOfKeysEtk(credential: KeyStoreCredential, nihiiOrSsin: String?): EncryptionToken? {
        val cert = credential.certificate

        val parser = CertificateParser(cert)
        return try {
            val identifierType = parser.identifier
            val identifierValue = parser.id
            val application = parser.application

            this.keyDepotService.getETKSet(
                identifierType,
                identifierType.formatIdentifierValue(java.lang.Long.parseLong(identifierValue)),
                application,
                credential.keystoreId,
                true
                                          )?.let { if (it.size == 1) it.iterator().next() else null } ?: throw TechnicalConnectorException(
                ERROR_ETK_NOTFOUND,
                arrayOfNulls<Any>(0)
                                                                                                                                          )
        } catch (e:java.lang.IllegalStateException) {
            log.info("Invalid certificate: ${parser.id} : ${parser.identifier} : ${parser.application} - nihii/ssin: ${nihiiOrSsin ?: ""}")
            null
        }
    }

    override fun uploadKeystore(file: MultipartFile): UUID {
        val keystoreId = UUID.nameUUIDFromBytes(file.bytes)
        keystoresMap[keystoreId] = file.bytes
        log.info("keystoresMap size: ${keystoresMap.size}")

        return keystoreId
    }

    override fun uploadKeystore(data: ByteArray): UUID {
        val keystoreId = UUID.nameUUIDFromBytes(data)
        keystoresMap[keystoreId] = data
        log.info("keystoresMap size: ${keystoresMap.size}")

        return keystoreId
    }

    val keystoreCache = CacheBuilder.newBuilder().maximumSize(2000).expireAfterAccess(1, TimeUnit.HOURS).build(object: CacheLoader<Pair<UUID, String>,KeyStore>() {
        override fun load(key: Pair<UUID, String>): KeyStore {
            val keyStoreData =
                keystoresMap[key.first]
                    ?: throw(MissingKeystoreException("Missing Keystore, please upload a keystore and use the returned keystoreId"))
            return KeyManager.getKeyStore(keyStoreData.inputStream(), "PKCS12", key.second.toCharArray())
        }
    })

    override fun getKeyStore(keystoreId: UUID, passPhrase: String): KeyStore? {
        return keystoreCache.get(Pair(keystoreId, passPhrase))
    }

    override fun checkIfKeystoreExist(keystoreId: UUID): Boolean{
        return keystoresMap.get(keystoreId) != null
    }

}
