/*
 * Commons eID Project.
 * Copyright (C) 2008-2013 FedICT.
 * Copyright (C) 2014-2016 e-Contract.be BVBA.
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

import be.fedict.commons.eid.client.FileType
import java.security.KeyStoreSpi
import be.fedict.commons.eid.jca.BeIDKeyStoreParameter
import java.security.NoSuchAlgorithmException
import java.security.UnrecoverableKeyException
import org.slf4j.LoggerFactory
import java.security.KeyStoreException
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.security.Key
import java.security.KeyStore.LoadStoreParameter
import java.security.UnrecoverableEntryException
import java.security.KeyStore.ProtectionParameter
import java.security.KeyStore
import java.security.PrivateKey
import java.security.KeyStore.TrustedCertificateEntry
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import javax.swing.JFrame

/**
 * eID based JCA [KeyStore]. Used to load eID key material via standard
 * JCA API calls. Once the JCA security provider has been registered you have a
 * new key store available named "BeID". Two key aliases are available:
 *
 *  * "Authentication" which gives you access to the eID authentication private
 * key and corresponding certificate chain.
 *  * "Signature" which gives you access to the eID non-repudiation private key
 * and corresponding certificate chain.
 *
 * Further the Citizen CA certificate can be accessed via the "CA" alias, the
 * Root CA certificate can be accessed via the "Root" alias, and the national
 * registration certificate can be accessed via the "RRN" alias.
 *
 *
 * Supports the eID specific [BeIDKeyStoreParameter] key store parameter.
 * You can also let any [JFrame] implement the
 * [LoadStoreParameter] interface. If you pass this to
 * [KeyStore.load] the keystore will use that Swing
 * frame as parent for positioning the dialogs.
 *
 *
 * Usage:
 *
 *
 *
 * <pre>
 * import java.security.KeyStore;
 * import java.security.cert.X509Certificate;
 * import java.security.PrivateKey;
 *
 * ...
 * KeyStore keyStore = KeyStore.getInstance("BeID");
 * keyStore.load(null);
 * X509Certificate authnCertificate = (X509Certificate) keyStore
 * .getCertificate("Authentication");
 * PrivateKey authnPrivateKey = (PrivateKey) keyStore.getKey(
 * "Authentication", null);
 * Certificate[] signCertificateChain = keyStore.getCertificateChain("Signature");
</pre> *
 *
 * @author Frank Cornelis
 * @see BeIDKeyStoreParameter
 *
 * @see BeIDProvider
 */
class RemoteBeIDKeyStoreSpi(val beIDCard: RemoteBeIDCard) : KeyStoreSpi() {
    private var keyStoreParameter: BeIDKeyStoreParameter? = null
    private var authnCertificateChain: List<X509Certificate>? = null
    private var signCertificateChain: List<X509Certificate>? = null
    private var rrnCertificateChain: List<X509Certificate>? = null
    private var citizenCaCertificate: X509Certificate? = null
    private var rootCaCertificate: X509Certificate? = null
    private var authnCertificate: X509Certificate? = null
    private var signCertificate: X509Certificate? = null
    private var rrnCertificate: X509Certificate? = null

    @Throws(NoSuchAlgorithmException::class, UnrecoverableKeyException::class)
    override fun engineGetKey(alias: String, password: CharArray?): Key? {
        log.debug("engineGetKey: $alias")
        return when (alias.toLowerCase()) {
            "authentication" -> RemoteBeIDPrivateKey(FileType.AuthentificationCertificate, beIDCard)
            "signature" -> RemoteBeIDPrivateKey(FileType.NonRepudiationCertificate, beIDCard)
            else -> null
        }
    }

    override fun engineGetCertificateChain(alias: String): Array<Certificate>? {
        log.debug("engineGetCertificateChain: $alias")
        return try {
            when (alias.toLowerCase()) {
                "signature" -> signCertificateChain ?: beIDCard.signingCertificateChain.also { signCertificateChain = it }
                "authentication" -> authnCertificateChain ?: beIDCard.authenticationCertificateChain.also { authnCertificateChain = it }
                "rrn" -> rrnCertificateChain ?: beIDCard.rRNCertificateChain.also { rrnCertificateChain = it }
                else -> null
            }?.toTypedArray()
        } catch (e: Exception) {
            log.warn("error: " + e.message, e)
            null
        }
    }

    override fun engineGetCertificate(alias: String): Certificate? {
        log.debug("engineGetCertificate: $alias")
        return try {
            when (alias.toLowerCase()) {
                "signature" -> signCertificate ?: beIDCard.signingCertificate.also { signCertificate = it }
                "authentication" -> authnCertificate ?: beIDCard.authenticationCertificate.also { authnCertificate = it }
                "ca" -> citizenCaCertificate ?: beIDCard.cACertificate.also { citizenCaCertificate = it }
                "root" -> rootCaCertificate ?: beIDCard.rootCACertificate.also { rootCaCertificate = it }
                "rrn" -> rrnCertificate ?: beIDCard.rRNCertificate.also { rrnCertificate = it }
                else -> null
            }
        } catch (e: Exception) {
            log.warn("error: " + e.message, e)
            null
        }
    }

    override fun engineGetCreationDate(alias: String) = (engineGetCertificate(alias) as? X509Certificate)?.notBefore

    @Throws(KeyStoreException::class)
    override fun engineSetKeyEntry(
        alias: String, key: Key,
        password: CharArray, chain: Array<Certificate>
    ) {
        throw KeyStoreException()
    }

    @Throws(KeyStoreException::class)
    override fun engineSetKeyEntry(
        alias: String, key: ByteArray,
        chain: Array<Certificate>
    ) {
        throw KeyStoreException()
    }

    @Throws(KeyStoreException::class)
    override fun engineSetCertificateEntry(
        alias: String,
        cert: Certificate
    ) {
        throw KeyStoreException()
    }

    @Throws(KeyStoreException::class)
    override fun engineDeleteEntry(alias: String) {
        throw KeyStoreException()
    }

    override fun engineAliases(): Enumeration<String> {
        log.debug("engineAliases")
        val aliases = Vector<String>()
        aliases.add("Authentication")
        aliases.add("Signature")
        aliases.add("CA")
        aliases.add("Root")
        aliases.add("RRN")
        return aliases.elements()
    }

    override fun engineContainsAlias(alias: String): Boolean {
        log.debug("engineContainsAlias: $alias")
        return when (alias) {
            "Authentication" -> true
            "Signature" -> true
            "Root" -> true
            "CA" -> true
            "RRN" -> true
            else -> false
        }
    }

    override fun engineSize(): Int {
        return 2
    }

    override fun engineIsKeyEntry(alias: String): Boolean {
        log.debug("engineIsKeyEntry: $alias")
        return "Authentication" == alias || "Signature" == alias
    }

    override fun engineIsCertificateEntry(alias: String): Boolean {
        log.debug("engineIsCertificateEntry: $alias")
        return when (alias) {
            "Root" -> true
            "CA" -> true
            "RRN" -> true
            else -> false
        }
    }

    @Throws(IOException::class, NoSuchAlgorithmException::class, CertificateException::class)
    override fun engineStore(param: LoadStoreParameter) {
        log.debug("engineStore")
        super.engineStore(param)
    }

    @Throws(KeyStoreException::class, NoSuchAlgorithmException::class, UnrecoverableEntryException::class)
    override fun engineGetEntry(alias: String, protParam: ProtectionParameter): KeyStore.Entry {
        log.debug("engineGetEntry: $alias")
        if ("Authentication" == alias || "Signature" == alias) {
            val privateKey = engineGetKey(alias, null) as PrivateKey
            val chain = engineGetCertificateChain(alias)
            return KeyStore.PrivateKeyEntry(
                privateKey,
                chain
            )
        }
        if ("CA" == alias || "Root" == alias || "RRN" == alias) {
            val certificate = engineGetCertificate(alias)
            return TrustedCertificateEntry(
                certificate
            )
        }
        return super.engineGetEntry(alias, protParam)
    }

    @Throws(KeyStoreException::class)
    override fun engineSetEntry(
        alias: String, entry: KeyStore.Entry,
        protParam: ProtectionParameter
    ) {
        log.debug("engineSetEntry: $alias")
        super.engineSetEntry(alias, entry, protParam)
    }

    override fun engineEntryInstanceOf(
        alias: String,
        entryClass: Class<out KeyStore.Entry>
    ): Boolean {
        log.debug("engineEntryInstanceOf: $alias")
        return super.engineEntryInstanceOf(alias, entryClass)
    }

    override fun engineGetCertificateAlias(cert: Certificate): String? = null

    @Throws(IOException::class, NoSuchAlgorithmException::class, CertificateException::class)
    override fun engineStore(stream: OutputStream, password: CharArray) {
    }

    @Throws(IOException::class, NoSuchAlgorithmException::class, CertificateException::class)
    override fun engineLoad(stream: InputStream, password: CharArray) {
    }

    @Throws(IOException::class, NoSuchAlgorithmException::class, CertificateException::class)
    override fun engineLoad(param: LoadStoreParameter) {
        log.debug("engineLoad")

        authnCertificateChain = null
        signCertificateChain = null
        rrnCertificateChain = null
        authnCertificate = null
        signCertificate = null
        citizenCaCertificate = null
        rootCaCertificate = null
        rrnCertificate = null
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}
