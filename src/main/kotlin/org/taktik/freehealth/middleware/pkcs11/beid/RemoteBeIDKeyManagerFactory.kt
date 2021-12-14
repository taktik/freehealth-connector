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
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.lang.IllegalStateException
import java.security.InvalidAlgorithmParameterException
import javax.net.ssl.ManagerFactoryParameters
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.UnrecoverableKeyException
import java.security.KeyStore
import javax.net.ssl.KeyManager
import javax.net.ssl.KeyManagerFactorySpi

/**
 * eID specific [KeyManagerFactory]. Can be used for mutual TLS
 * authentication.
 *
 *
 * Usage:
 *
 * <pre>
 * import javax.net.ssl.KeyManagerFactory;
 * import javax.net.ssl.SSLContext;
 * ...
 * KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(&quot;BeID&quot;);
 * SSLContext sslContext = SSLContext.getInstance(&quot;TLS&quot;);
 * sslContext.init(keyManagerFactory.getKeyManagers(), ..., ...);
</pre> *
 *
 * @see RemoteBeIDX509KeyManager
 *
 * @see BeIDManagerFactoryParameters
 *
 * @author Frank Cornelis
 */
class RemoteBeIDKeyManagerFactory(private val beIDCard: RemoteBeIDCard) : KeyManagerFactorySpi() {
    private var beIDSpec: BeIDManagerFactoryParameters? = null
    override fun engineGetKeyManagers(): Array<KeyManager> {
        log.debug("engineGetKeyManagers")
        val beidKeyManager: KeyManager
        beidKeyManager = try { RemoteBeIDX509KeyManager(beIDCard, beIDSpec) } catch (e: Exception) { throw IllegalStateException(e) }
        return arrayOf(beidKeyManager)
    }

    @Throws(InvalidAlgorithmParameterException::class)
    override fun engineInit(spec: ManagerFactoryParameters?) {
        log.debug("engineInit(spec)")
        if (null == spec) { return }
        beIDSpec = spec as? BeIDManagerFactoryParameters ?: throw InvalidAlgorithmParameterException()
    }

    @Throws(KeyStoreException::class, NoSuchAlgorithmException::class, UnrecoverableKeyException::class)
    override fun engineInit(keyStore: KeyStore, password: CharArray) { log.debug("engineInit(KeyStore,password)") }

    companion object {
        private val log = LoggerFactory.getLogger(RemoteBeIDKeyManagerFactory::class.java)
    }
}
