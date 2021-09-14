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
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.ConfigValidator
import org.taktik.connector.technical.exception.TechnicalConnectorException
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
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.dto.CertificateInfo
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.IOException
import java.io.StringReader
import java.io.StringWriter
import java.security.KeyStore
import java.time.Instant
import java.util.*
import java.util.concurrent.ExecutionException
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

    val freehealthStsService: org.taktik.connector.technical.service.sts.STSService = org.taktik.connector.technical.service.sts.impl.STSServiceImpl()
    val transformerFactory: TransformerFactory = TransformerFactory.newInstance()
    val config: ConfigValidator = ConfigFactory.getConfigValidator()

    override fun isAcceptance() = config.getProperty("endpoint.sts").contains("-acpt")

    override fun registerToken(tokenId: UUID, token: String, quality: String) {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document = builder.parse(InputSource(StringReader(token)))
        val assertion = document.documentElement

        tokensMap[tokenId] = SamlTokenResult(tokenId, token, System.currentTimeMillis(), SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis, quality)
        log.info("tokensMap size: ${tokensMap.size}")
    }

    override fun getSAMLToken(tokenId: UUID, keystoreId: UUID, passPhrase: String): SAMLToken? {
        return tokensMap[tokenId]?.let {
            val keystore = getKeyStore(keystoreId, passPhrase)
            val result = DOMResult()
            transformerFactory.newTransformer().transform(StreamSource(StringReader(it.token!!)), result)
            return result.node?.firstChild?.let { el ->
                SAMLTokenFactory.getInstance()
                    .createSamlToken(el as Element, KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, it.quality))
            }
        }
    }

    override fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String, //nihii for medical house and niss for doctor
        passPhrase: String,
        quality: String,
        tokenId: UUID?,
        extraDesignators: List<Pair<String, String>>
    ): SamlTokenResult? {
        val now = System.currentTimeMillis()
        val currentToken = tokenId?.let { id -> tokensMap[id] }
        val isStillRecommendedForUse = currentToken?.let {
            val valid = it.validity
            val ts = it.timestamp

            if (valid == null || ts == null || quality != it.quality) {
                false
            } else {
                val totalValidity = valid - ts
                val remainingValidity = valid - now
                remainingValidity > 0 && totalValidity > 0 && remainingValidity.toDouble() / totalValidity.toDouble() > 0.5
            }
        } ?: false

        if (isStillRecommendedForUse) return currentToken

        val keystore = getKeyStore(keystoreId, passPhrase)
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val etk = getHolderOfKeysEtk(credential, nihiiOrSsin)
        if (!hokPrivateKeys.containsKey(etk?.certificate?.serialNumber?.toString(10))) {
            throw java.lang.IllegalArgumentException("The certificate from the ETK don't match with the one in the encryption keystore")
        }

        val designators = when (quality) {
            "medicalhouse" -> listOf(
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
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:medicalhouse:nihii-number:recognisedmedicalhouse:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:recognisedorganization:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "guardpost" -> listOf(
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
            "sortingcenter" -> listOf(
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:sortingcenter:nihii-number",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:sortingcenter:nihii-number",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:sortingcenter:nihii-number",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:sortingcenter:nihii-number:recognisedsortingcenter:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:sortingcenter:nihii-number:recognisedsortingcenter:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "officedoctors" -> listOf(
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:officedoctors:nihii-number",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:officedoctors:nihii-number",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:officedoctors:nihii-number",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:officedoctors:nihii-number:recognisedofficedoctors:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:officedoctors:nihii-number:recognisedofficedoctors:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "groupofnurses" -> listOf(
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:groupofnurses:nihii-number",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:groupofnurses:nihii-number",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:groupofnurses:nihii-number",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:groupofnurses:nihii-number:recognisedgroupofnurses:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:groupofnurses:nihii-number:recognisedgroupofnurses:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "dentist" -> listOf(
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace"),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:fpsph:dentist:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:nihii:dentist:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:professional:dentist:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "doctor" -> listOf(
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
                    "urn:be:fgov:person:ssin:ehealth:1.0:givenname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:surname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:fpsph:doctor:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "nurse" -> listOf(
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace"),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:nurse:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:nurse:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:nihii:nurse:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:givenname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:surname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:fpsph:nurse:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "physiotherapist" -> listOf(
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace"),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:professional:physiotherapist:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:nihii:physiotherapist:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:givenname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:surname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:fpsph:physiotherapist:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            "midwife" -> listOf(
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin",
                    "urn:be:fgov:identification-namespace"
                ),
                SAMLAttributeDesignator("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace"),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:professional:midwife:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:nihii:midwife:nihii11",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:givenname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:surname",
                    "urn:be:fgov:certified-namespace:ehealth"
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:person:ssin:ehealth:1.0:fpsph:midwife:boolean",
                    "urn:be:fgov:certified-namespace:ehealth"
                )
            )
            else -> throw IllegalArgumentException("unsupported quality")
        } + extraDesignators.map { SAMLAttributeDesignator(it.second, it.first) }

        val attributes = when (quality) {
            "medicalhouse" -> listOf(
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
            )
            "sortingcenter" -> listOf(
                SAMLAttribute(
                    "urn:be:fgov:ehealth:1.0:sortingcenter:nihii-number",
                    "urn:be:fgov:identification-namespace",
                    nihiiOrSsin
                ),
                SAMLAttribute(
                    "urn:be:fgov:ehealth:1.0:certificateholder:sortingcenter:nihii-number",
                    "urn:be:fgov:identification-namespace",
                    nihiiOrSsin
                )
            )
            "guardpost" -> listOf(
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
            )
            "officedoctors" -> listOf(
                SAMLAttribute(
                    "urn:be:fgov:ehealth:1.0:officedoctors:nihii-number",
                    "urn:be:fgov:identification-namespace",
                    nihiiOrSsin
                ),
                SAMLAttribute(
                    "urn:be:fgov:ehealth:1.0:certificateholder:officedoctors:nihii-number",
                    "urn:be:fgov:identification-namespace",
                    nihiiOrSsin
                )
            )
            "groupofnurses" -> listOf(
                SAMLAttribute(
                    "urn:be:fgov:ehealth:1.0:groupofnurses:nihii-number",
                    "urn:be:fgov:identification-namespace",
                    nihiiOrSsin
                ),
                SAMLAttribute(
                    "urn:be:fgov:ehealth:1.0:certificateholder:groupofnurses:nihii-number",
                    "urn:be:fgov:identification-namespace",
                    nihiiOrSsin
                )
            )
            else -> listOf(
                SAMLAttribute(
                    "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin",
                    "urn:be:fgov:identification-namespace",
                    nihiiOrSsin
                ), SAMLAttribute("urn:be:fgov:person:ssin", "urn:be:fgov:identification-namespace", nihiiOrSsin)
            )
        }

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
                SamlTokenResult(randomUUID, samlToken, now, SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis, quality)
            tokensMap[randomUUID] = samlTokenResult
            log.info("tokensMap size: ${tokensMap.size}")

            samlTokenResult
        } catch (e: TechnicalConnectorException) {
            log.info("STS token request failure: ${e.errorCode} : ${e.message} : ${e.stackTrace}")
            currentToken // FIXME: should throw if no currentToken
        }
    }

    override fun getKeystoreInfo(keystoreId: UUID, passPhrase: String): CertificateInfo {
        val keystore = getKeyStore(keystoreId, passPhrase)
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, "doctor") //Shouldn't assume but won't be used
        val parser = CertificateParser(credential.certificate)

        return CertificateInfo(credential.certificate.notAfter.time, parser.type, parser.id, parser.application, parser.owner)
    }

    override fun checkTokenValid(tokenId: UUID): Boolean {
        return tokensMap[tokenId]?.let {
            (it.token?.length ?: 0) > 0 && (it.validity ?: 0) > Instant.now().toEpochMilli()
        } ?: false
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
        } catch (e: java.lang.IllegalStateException) {
            log.info("Invalid certificate: ${parser.id} : ${parser.identifier} : ${parser.application} - nihii/ssin: ${nihiiOrSsin
                ?: ""}")
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

    val keystoreCache = CacheBuilder.newBuilder().maximumSize(2000).expireAfterAccess(1, TimeUnit.HOURS).build(object : CacheLoader<Pair<UUID, String>, KeyStore>() {
        override fun load(key: Pair<UUID, String>): KeyStore {
            val keyStoreData =
                keystoresMap[key.first]
                    ?: throw(MissingKeystoreException("Missing Keystore, please upload a keystore and use the returned keystoreId"))
            return KeyManager.getKeyStore(keyStoreData.inputStream(), "PKCS12", key.second.toCharArray())
        }
    })

    override fun getKeyStore(keystoreId: UUID, passPhrase: String): KeyStore? {
        return try { keystoreCache.get(Pair(keystoreId, passPhrase)) } catch(ex:ExecutionException) {
            (ex.cause as? IOException)?.let { throw IllegalArgumentException(it.message ?: "Decryption exception") } ?: throw (ex.cause ?: ex)
        }
    }

    override fun checkIfKeystoreExist(keystoreId: UUID): Boolean {
        return keystoresMap.get(keystoreId) != null
    }

}
