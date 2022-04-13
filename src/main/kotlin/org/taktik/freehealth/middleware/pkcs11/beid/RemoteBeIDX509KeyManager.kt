/*
 * Commons eID Project.
 * Copyright (C) 2012-2013 FedICT.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version
 * 3.0 as published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, see
 * http://www.gnu.org/licenses/.
 */
package org.taktik.freehealth.middleware.pkcs11.beid

import be.fedict.commons.eid.jca.BeIDManagerFactoryParameters
import javax.net.ssl.X509ExtendedKeyManager
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.PrivateKey
import javax.net.ssl.SSLEngine
import org.apache.commons.logging.LogFactory
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.net.Socket
import java.security.Principal
import java.security.cert.Certificate
import java.security.cert.X509Certificate

/**
 * eID specific [X509ExtendedKeyManager].
 *
 * @see RemoteBeIDKeyManagerFactory
 *
 * @author Frank Cornelis
 */
class RemoteBeIDX509KeyManager(beIDCard: RemoteBeIDCard, beIDSpec: BeIDManagerFactoryParameters? = null) :
    X509ExtendedKeyManager() {
    private val keyStore: KeyStore = object: KeyStore(RemoteBeIDKeyStoreSpi(beIDCard), RemoteBeIDProvider(beIDCard), "RemoteBeid") {}.apply { load(null) }

    override fun chooseClientAlias(
        keyTypes: Array<String>,
        issuers: Array<Principal>, socket: Socket
    ): String? {
        log.debug("chooseClientAlias")
        for (keyType in keyTypes) {
            log.debug("key type: $keyType")
            if ("RSA" == keyType) {
                return "beid"
            }
        }
        return null
    }

    override fun chooseServerAlias(
        keyType: String,
        issuers: Array<Principal>, socket: Socket
    ): String? {
        log.debug("chooseServerAlias")
        return null
    }

    override fun getCertificateChain(alias: String): Array<X509Certificate>? {
        log.debug("getCertificateChain: $alias")
        if ("beid" == alias) {
            val certificateChain: Array<Certificate>
            certificateChain = try {
                keyStore
                    .getCertificateChain("Authentication")
            } catch (e: KeyStoreException) {
                log.error("BeID keystore error: " + e.message, e)
                return null
            }
            return certificateChain.map<Certificate?, X509Certificate> { it as X509Certificate }.toTypedArray()
        }
        return null
    }

    override fun getClientAliases(
        keyType: String,
        issuers: Array<Principal>
    ): Array<String>? {
        log.debug("getClientAliases")
        return null
    }

    override fun getPrivateKey(alias: String): PrivateKey? {
        log.debug("getPrivateKey: $alias")
        if ("beid" == alias) {
            val privateKey: PrivateKey
            privateKey = try {
                keyStore.getKey(
                    "Authentication", null
                ) as PrivateKey
            } catch (e: Exception) {
                log.error("getKey error: " + e.message, e)
                return null
            }
            return privateKey
        }
        return null
    }

    override fun getServerAliases(
        keyType: String,
        issuers: Array<Principal>
    ): Array<String>? {
        log.debug("getServerAliases")
        return null
    }

    override fun chooseEngineClientAlias(
        keyType: Array<String>,
        issuers: Array<Principal>, engine: SSLEngine
    ): String {
        log.debug("chooseEngineClientAlias")
        return super.chooseEngineClientAlias(keyType, issuers, engine)
    }

    override fun chooseEngineServerAlias(
        keyType: String,
        issuers: Array<Principal>, engine: SSLEngine
    ): String {
        log.debug("chooseEngineServerAlias")
        return super.chooseEngineServerAlias(keyType, issuers, engine)
    }

    companion object {
        private val log = LoggerFactory.getLogger(RemoteBeIDX509KeyManager::class.java)
    }

}
