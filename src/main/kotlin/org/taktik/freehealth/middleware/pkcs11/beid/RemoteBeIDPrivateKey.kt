/*
 * Commons eID Project.
 * Copyright (C) 2008-2013 FedICT.
 * Copyright (C) 2015-2016 e-Contract.be BVBA.
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
import be.fedict.commons.eid.client.impl.BeIDDigest
import java.math.BigInteger
import java.security.PrivateKey
import java.security.SignatureException
import java.security.interfaces.RSAPrivateKey

/**
 * eID based JCA private key. Should not be used directly, but via the
 * [BeIDKeyStore].
 *
 * @author Frank Cornelis
 * @see BeIDKeyStore
 */
class RemoteBeIDPrivateKey(
    private val certificateFileType: FileType,
    private val beIDCard: RemoteBeIDCard
) : PrivateKey {
    companion object {
        private const val serialVersionUID = 1L
        private val beIDDigests = mapOf(
            "SHA-1" to BeIDDigest.SHA_1,
            "SHA-224" to BeIDDigest.SHA_224,
            "SHA-256" to BeIDDigest.SHA_256,
            "SHA-384" to BeIDDigest.SHA_384,
            "SHA-512" to BeIDDigest.SHA_512,
            "NONE" to BeIDDigest.NONE,
            "RIPEMD128" to BeIDDigest.RIPEMD_128,
            "RIPEMD160" to BeIDDigest.RIPEMD_160,
            "RIPEMD256" to BeIDDigest.RIPEMD_256,
            "SHA-1-PSS" to BeIDDigest.SHA_1_PSS,
            "SHA-256-PSS" to BeIDDigest.SHA_256_PSS
        )
    }

    override fun getAlgorithm(): String {
        return "RSA"
    }

    override fun getFormat(): String? {
        return null
    }

    override fun getEncoded(): ByteArray? {
        return null
    }

    @Throws(SignatureException::class)
    fun sign(digestValue: ByteArray?, digestAlgo: String): ByteArray {
        val beIDDigest = beIDDigests[digestAlgo] ?: throw SignatureException("unsupported algo: $digestAlgo")
        return beIDCard.sign(digestValue!!, beIDDigest, certificateFileType, false)
    }

}
