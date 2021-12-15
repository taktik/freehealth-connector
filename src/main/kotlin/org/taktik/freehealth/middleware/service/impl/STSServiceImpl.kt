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
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.ConfigValidator
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.service.sts.utils.SAMLHelper
import org.taktik.connector.technical.utils.CertificateParser
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import org.taktik.freehealth.middleware.service.STSService
import java.io.StringWriter
import java.security.KeyStore
import java.util.*
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

@Service
class STSServiceImpl(val keyDepotService: KeyDepotService, val remoteKeystoreService: RemoteKeystoreService) : STSService {
    private val log = LogFactory.getLog(this.javaClass)

    val freehealthStsService: org.taktik.connector.technical.service.sts.STSService = org.taktik.connector.technical.service.sts.impl.STSServiceImpl()
    val transformerFactory: TransformerFactory = TransformerFactory.newInstance()
    val config: ConfigValidator = ConfigFactory.getConfigValidator()

     fun isAcceptance() = config.getProperty("endpoint.sts").contains("-acpt")

    override fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String, //nihii for medical house and niss for doctor
        passPhrase: String,
        quality: String,
        tokenId: UUID?,
        extraDesignators: List<Pair<String, String>>,
        keyStore: KeyStore?
    ): SamlTokenResult {
        val now = System.currentTimeMillis()
        val credential = KeyStoreCredential(keystoreId, keyStore, "authentication", passPhrase, quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keyStore, passPhrase.toCharArray())
        val etk = getHolderOfKeysEtk(credential, nihiiOrSsin)
        if (hokPrivateKeys.isNotEmpty() && !hokPrivateKeys.containsKey(etk?.certificate?.serialNumber?.toString(10))) {
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
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:recognisedorganization:boolean",
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
                ),
                SAMLAttributeDesignator(
                    "urn:be:fgov:ehealth:1.0:certificateholder:recognisedorganization:boolean",
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

        return SamlTokenResult(
            null,
            randomUUID,
            null,
            samlToken,
            now,
            SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis,
            quality
        )
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


}
