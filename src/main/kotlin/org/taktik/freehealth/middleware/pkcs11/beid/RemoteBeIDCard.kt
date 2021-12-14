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
import be.fedict.commons.eid.client.spi.UserCancelledException
import org.slf4j.LoggerFactory
import org.taktik.freehealth.middleware.pkcs11.remote.CardConnection
import java.io.ByteArrayInputStream
import java.io.IOException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*
import javax.smartcardio.CardException

/**
 * One BeIDCard instance represents one Belgian Electronic Identity Card,
 * physically present in a remote virtual terminal. It exposes
 * the publicly accessible features of the BELPIC applet on the card's chip:
 *
 *  * Reading Certificates and Certificate Chains
 *  * Signing of digests non-repudiation and authentication purposes
 *  * Verification and Alteration of the PIN code
 *  * Reading random bytes from the on-board random generator
 *  * Creating text message transaction signatures on specialized readers
 *  * PIN unblocking using PUK codes
 *
 *
 *
 * BeIDCard instances rely on an instance of BeIDCardUI to support user
 * interaction, such as obtaining PIN and PUK codes for authentication, signing,
 * verifying, changing PIN codes, and for notifying the user of the progress of
 * such operations on a Secure Pinpad Device. A default implementation is
 * available as DefaultBeIDCardUI, and unless replaced by an explicit call to
 * setUI() will automatically be used (when present in the class path).
 *
 *
 * BeIDCard instances automatically detect CCID features in the underlying
 * CardTerminal, and will choose the most secure path where several are
 * available, for example, when needing to acquire PIN codes from the user, and
 * the card is in a CCID-compliant Secure Pinpad Reader the PIN entry features
 * of the reader will be used instead of the corresponding "obtain.." feature
 * from the active BeIDCardUI. In that case, the corresponding "advise.." method
 * of the active BeIDCardUI will be called instead, to advise the user to attend
 * to the SPR.
 *
 *
 * To receive notifications of the progress of lengthy operations such as
 * reading 'files' (certificates, photo,..) or signing (which may be lengthy
 * because of user PIN interaction), register an instance of BeIDCardListener
 * using addCardListener(). This is useful, for example, for providing progress
 * indication to the user.
 *
 *
 * For detailed progress and error/debug logging, provide an instance of
 * be.fedict.commons.eid.spi.Logger to BeIDCard's constructor (the default
 * VoidLogger discards all logging and debug messages). You are advised to
 * provide some form of logging facility, for all but the most trivial
 * applications.
 *
 * @author Frank Cornelis
 * @author Frank Marien
 */
class RemoteBeIDCard(private val card: CardConnection) {
    private var certificateFactory = CertificateFactory.getInstance("X.509");
    private val log= LoggerFactory.getLogger(this::class.java)

    /**
     * close this BeIDCard, when you are done with it, to release any underlying
     * resources. All subsequent calls will fail.
     *
     * @return this BeIDCard instance, to allow method chaining
     */
    fun close(): RemoteBeIDCard {
        log.debug("closing eID card")
        try {
            card.disconnect()
        } catch (e: CardException) {
            log.error(
                "could not disconnect the card: "
                    + e.message
            )
        }
        return this
    }

    /**
     * Reads a certain certificate from the card. Which certificate to read is
     * determined by the FileType param. Applicable FileTypes are
     * AuthentificationCertificate, NonRepudiationCertificate, CACertificate,
     * RootCertificate and RRNCertificate.
     *
     * @param fileType
     * @return the certificate requested
     * @throws CertificateException
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     */
    @Throws(CertificateException::class, CardException::class, IOException::class, InterruptedException::class)
    fun getCertificate(fileName: String): X509Certificate =
        certificateFactory.generateCertificate(ByteArrayInputStream(card.readFile(fileName))) as X509Certificate

    /**
     * Returns the X509 authentication certificate. This is a convenience method
     * for `getCertificate(FileType.AuthentificationCertificate)`
     *
     * @return the X509 Authentication Certificate from the card.
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val authenticationCertificate: X509Certificate
        get() = getCertificate("Authentication")

    /**
     * Returns the X509 non-repudiation certificate. This is a convencience
     * method for
     * `getCertificate(FileType.NonRepudiationCertificate)`
     *
     * @return
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val signingCertificate: X509Certificate
        get() = getCertificate("Signature")

    /**
     * Returns the citizen CA certificate. This is a convenience method for
     * `getCertificate(FileType.CACertificate)`
     *
     * @return
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val cACertificate: X509Certificate
        get() = getCertificate("CA")

    /**
     * Returns the Root CA certificate.
     *
     * @return the Root CA X509 certificate.
     * @throws CertificateException
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     */
    @get:Throws(
        CertificateException::class,
        CardException::class,
        IOException::class,
        InterruptedException::class
    )
    val rootCACertificate: X509Certificate
        get() = getCertificate("Root")

    /**
     * Returns the national registration certificate. This is a convencience
     * method for `getCertificate(FileType.RRNCertificate)`
     *
     * @return
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val rRNCertificate: X509Certificate
        get() = getCertificate("RRN")

    /**
     * Returns the entire certificate chain for a given file type. Of course,
     * only file types corresponding with a certificate are accepted. Which
     * certificate's chain to return is determined by the FileType param.
     * Applicable FileTypes are AuthentificationCertificate,
     * NonRepudiationCertificate, CACertificate, and RRNCertificate.
     *
     * @param fileType
     * which certificate's chain to return
     * @return the certificate's chain up to and including the Belgian Root Cert
     * @throws CertificateException
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     */
    @Throws(CertificateException::class, CardException::class, IOException::class, InterruptedException::class)
    fun getCertificateChain(fileName: String) = listOfNotNull(
        certificateFactory.generateCertificate(ByteArrayInputStream(card.readFile(fileName))) as X509Certificate,
        if (fileName == "Authentication" || fileName == "Signature") { certificateFactory.generateCertificate(ByteArrayInputStream(card.readFile("CA"))) as X509Certificate } else null,
        certificateFactory.generateCertificate(ByteArrayInputStream(card.readFile("Root"))) as X509Certificate
    )

    /**
     * Returns the X509 authentication certificate chain. (Authentication ->
     * Citizen CA -> Root) This is a convenience method for
     * `getCertificateChain(FileType.AuthentificationCertificate)`
     *
     * @return the authentication certificate chain
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val authenticationCertificateChain: List<X509Certificate>
        get() = getCertificateChain("Authentication")

    /**
     * Returns the X509 non-repudiation certificate chain. (Non-Repudiation ->
     * Citizen CA -> Root) This is a convenience method for
     * `getCertificateChain(FileType.NonRepudiationCertificate)`
     *
     * @return the non-repudiation certificate chain
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val signingCertificateChain: List<X509Certificate>
        get() = getCertificateChain("Signature")

    /**
     * Returns the Citizen CA X509 certificate chain. (Citizen CA -> Root) This
     * is a convenience method for
     * `getCertificateChain(FileType.CACertificate)`
     *
     * @return the citizen ca certificate chain
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val cACertificateChain: List<X509Certificate>
        get() = getCertificateChain("CA")

    /**
     * Returns the national registry X509 certificate chain. (National Registry
     * -> Root) This is a convenience method for
     * `getCertificateChain(FileType.RRNCertificate)`
     *
     * @return the national registry certificate chain
     * @throws CardException
     * @throws IOException
     * @throws CertificateException
     * @throws InterruptedException
     */
    @get:Throws(
        CardException::class,
        IOException::class,
        CertificateException::class,
        InterruptedException::class
    )
    val rRNCertificateChain: List<X509Certificate>
        get() = getCertificateChain("RRN")
    /**
     * Sign a given digest value.
     *
     * @param digestValue
     * the digest value to be signed.
     * @param digestAlgo
     * the algorithm used to calculate the given digest value.
     * @param fileType
     * the certificate's file type.
     * @param requireSecureReader
     * `true` if a secure pinpad reader is required.
     * @param applicationName
     * the optional application name.
     * @return
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     * @throws UserCancelledException
     */
    /**
     * Sign a given digest value.
     *
     * @param digestValue
     * the digest value to be signed.
     * @param digestAlgo
     * the algorithm used to calculate the given digest value.
     * @param fileType
     * the certificate's file type.
     * @param requireSecureReader
     * `true` if a secure pinpad reader is required.
     * @return
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     * @throws UserCancelledException
     */
    @JvmOverloads
    @Throws(
        CardException::class,
        IOException::class,
        InterruptedException::class,
        UserCancelledException::class
    )
    fun sign(
        digestValue: ByteArray, digestAlgo: BeIDDigest,
        fileType: FileType, requireSecureReader: Boolean
    ): ByteArray = card.sign(digestValue, digestAlgo, fileType, requireSecureReader)
    /**
     * Create an authentication signature.
     *
     * @param toBeSigned
     * the data to be signed
     * @param requireSecureReader
     * whether to require a secure pinpad reader to obtain the
     * citizen's PIN if false, the current BeIDCardUI will be used in
     * the absence of a secure pinpad reader. If true, an exception
     * will be thrown unless a SPR is available
     * @param applicationName
     * the optional application name.
     * @return a SHA-1 digest of the input data signed by the citizen's
     * authentication key
     * @throws NoSuchAlgorithmException
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     * @throws UserCancelledException
     */
    /**
     * Create an authentication signature.
     *
     * @param toBeSigned
     * the data to be signed
     * @param requireSecureReader
     * whether to require a secure pinpad reader to obtain the
     * citizen's PIN if false, the current BeIDCardUI will be used in
     * the absence of a secure pinpad reader. If true, an exception
     * will be thrown unless a SPR is available
     * @return a SHA-1 digest of the input data signed by the citizen's
     * authentication key
     * @throws NoSuchAlgorithmException
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     * @throws UserCancelledException
     */
    @JvmOverloads
    @Throws(
        NoSuchAlgorithmException::class,
        CardException::class,
        IOException::class,
        InterruptedException::class,
        UserCancelledException::class
    )
    fun signAuthn(
        toBeSigned: ByteArray?,
        requireSecureReader: Boolean, applicationName: String? = null
    ): ByteArray {
        val messageDigest = BeIDDigest.SHA_1
            .messageDigestInstance
        val digest = messageDigest.digest(toBeSigned)
        return sign(
            digest, BeIDDigest.SHA_1,
            FileType.AuthentificationCertificate, requireSecureReader
        )
    }

    /**
     * Returns random data generated by the eID card itself.
     *
     * @param size
     * the size of the requested random data.
     * @return size bytes of random data
     * @throws CardException
     */
    @Throws(CardException::class)
    fun getChallenge(size: Int) = card.getChallenge(size)

    /**
     * Create a text message transaction signature. The FedICT eID aware secure
     * pinpad readers can visualize such type of text message transactions on
     * their hardware display.
     *
     * @param transactionMessage
     * the transaction message to be signed.
     * @param requireSecureReader
     * @return
     * @throws CardException
     * @throws IOException
     * @throws InterruptedException
     * @throws UserCancelledException
     */
    @Throws(
        CardException::class,
        IOException::class,
        InterruptedException::class,
        UserCancelledException::class
    )
    fun signTransactionMessage(
        transactionMessage: String,
        requireSecureReader: Boolean
    ): ByteArray {
        return sign(
            transactionMessage.toByteArray(),
            BeIDDigest.PLAIN_TEXT,
            FileType.AuthentificationCertificate, requireSecureReader
        )
    }

    /**
     * Discard the citizen's PIN code from the PIN cache. Any subsequent
     * Authentication signatures will require PIN entry. (non-repudation
     * signatures are automatically protected)
     *
     * @throws Exception
     * @return this BeIDCard instance, to allow method chaining
     */
    @Throws(Exception::class)
    fun logoff() = card.logoff()

}
