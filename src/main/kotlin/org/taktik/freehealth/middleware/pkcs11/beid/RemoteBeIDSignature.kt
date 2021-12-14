/*
 * Commons eID Project.
 * Copyright (C) 2008-2013 FedICT.
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

import java.security.MessageDigest
import be.fedict.commons.eid.jca.BeIDPrivateKey
import org.apache.commons.logging.LogFactory
import java.io.ByteArrayOutputStream
import java.security.InvalidKeyException
import java.util.HashMap
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.security.InvalidParameterException
import java.security.PublicKey
import java.security.Signature
import java.security.SignatureException
import java.security.SignatureSpi

/**
 * eID based JCA [Signature] implementation. Supports the following
 * signature algorithms:
 *
 *  * `SHA1withRSA`
 *  * `SHA224withRSA`
 *  * `SHA256withRSA`
 *  * `SHA384withRSA`
 *  * `SHA512withRSA`
 *  * `NONEwithRSA`, used for mutual TLS authentication.
 *  * `RIPEMD128withRSA`
 *  * `RIPEMD160withRSA`
 *  * `RIPEMD256withRSA`
 *  * `SHA1withRSAandMGF1`, supported by recent eID cards.
 *  * `SHA256withRSAandMGF1`, supported by recent eID cards.
 *
 *
 *
 * Some of the more exotic digest algorithms like SHA-224 and RIPEMDxxx will
 * require an additional security provider like BouncyCastle.
 *
 * @author Frank Cornelis
 */
class RemoteBeIDSignature(private val signatureAlgorithm: String) : SignatureSpi() {
    private val messageDigest: MessageDigest? = digestAlgos[signatureAlgorithm]?.let { MessageDigest.getInstance(it) }
    private var precomputedDigestOutputStream: ByteArrayOutputStream? = if (messageDigest == null) ByteArrayOutputStream() else null
    private var privateKey: RemoteBeIDPrivateKey? = null
    private var verifySignature: Signature? = null

    companion object {
        private val LOG = LogFactory.getLog(RemoteBeIDSignature::class.java)
        private val digestAlgos = mapOf(
            "SHA1withRSA" to "SHA-1",
            "SHA224withRSA" to "SHA-224",
            "SHA256withRSA" to "SHA-256",
            "SHA384withRSA" to "SHA-384",
            "SHA512withRSA" to "SHA-512",
            "NONEwithRSA" to null,
            "RIPEMD128withRSA" to "RIPEMD128",
            "RIPEMD160withRSA" to "RIPEMD160",
            "RIPEMD256withRSA" to "RIPEMD256",
            "SHA1withRSAandMGF1" to "SHA-1",
            "SHA256withRSAandMGF1" to "SHA-256"
        )
    }

    @Throws(InvalidKeyException::class)
    override fun engineInitVerify(publicKey: PublicKey) {
        LOG.debug("engineInitVerify")
        try {
            (verifySignature ?: Signature.getInstance(signatureAlgorithm).also { verifySignature = it}).initVerify(publicKey)
        } catch (nsaex: NoSuchAlgorithmException) {
            throw InvalidKeyException("no such algo: ${nsaex.message}", nsaex)
        }
    }

    @Throws(InvalidKeyException::class)
    override fun engineInitSign(privateKey: PrivateKey) {
        LOG.debug("engineInitSign")
        this.privateKey = privateKey as? RemoteBeIDPrivateKey ?: throw InvalidKeyException()
        messageDigest?.reset()
    }

    @Throws(SignatureException::class)
    override fun engineUpdate(b: Byte) {
        messageDigest?.update(b)
        verifySignature?.update(b)
    }

    @Throws(SignatureException::class)
    override fun engineUpdate(b: ByteArray, off: Int, len: Int) {
        messageDigest?.update(b, off, len)
        precomputedDigestOutputStream?.write(b, off, len)
        verifySignature?.update(b, off, len)
    }

    @Throws(SignatureException::class)
    override fun engineSign(): ByteArray? {
        LOG.debug("engineSign")
        val digestValue: ByteArray?
        var digestAlgo: String?
        if (null != messageDigest) {
            digestValue = messageDigest.digest()
            digestAlgo = messageDigest.algorithm ?: "NONE"
            if (signatureAlgorithm.endsWith("andMGF1")) {
                digestAlgo += "-PSS"
            }
        } else if (null != precomputedDigestOutputStream) {
            digestValue = precomputedDigestOutputStream?.toByteArray()
            digestAlgo = "NONE"
        } else {
            throw SignatureException()
        }
        return privateKey?.sign(digestValue, digestAlgo)
    }

    @Throws(SignatureException::class)
    override fun engineVerify(sigBytes: ByteArray): Boolean {
        LOG.debug("engineVerify")
        return verifySignature?.verify(sigBytes) ?: throw SignatureException("initVerify required")
    }

    override fun engineSetParameter(param: String, value: Any) {}
    override fun engineGetParameter(param: String): Any? { return null }

    init {
        if (!digestAlgos.containsKey(signatureAlgorithm)) {
            LOG.error("no such algo: $signatureAlgorithm")
            throw NoSuchAlgorithmException(signatureAlgorithm)
        }
    }
}
