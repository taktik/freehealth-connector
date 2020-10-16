package org.taktik.connector.technical.validator.impl

import org.taktik.connector.technical.exception.InvalidTimeStampException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConfigurableImplementation
import org.taktik.connector.technical.utils.ConnectorCryptoUtils
import org.taktik.connector.technical.validator.TimeStampValidator
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.MessageDigest
import java.security.cert.X509Certificate
import java.util.ArrayList
import java.util.Collections
import org.apache.commons.lang.Validate
import org.bouncycastle.asn1.cms.Attribute
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers
import org.bouncycastle.cert.X509CertificateHolder
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator
import org.bouncycastle.cms.SignerInformationVerifier
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider
import org.bouncycastle.tsp.TimeStampToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeStampValidatorImpl : TimeStampValidator, ConfigurableImplementation {
    private var keyStore: KeyStore? = null
    private var aliases: MutableList<String>? = null

    @Throws(InvalidTimeStampException::class, TechnicalConnectorException::class)
    override fun validateTimeStampToken(bs: ByteArray, tsToken: TimeStampToken) {
        val calculatedDigest = ConnectorCryptoUtils.calculateDigest(tsToken.timeStampInfo.messageImprintAlgOID.id, bs)
        val tokenDigestValue = tsToken.timeStampInfo.messageImprintDigest
        if (!MessageDigest.isEqual(calculatedDigest, tokenDigestValue)) {
            throw InvalidTimeStampException("Response for different message imprint digest.")
        } else {
            val scV1 = tsToken.signedAttributes.get(PKCSObjectIdentifiers.id_aa_signingCertificate)
            val scV2 = tsToken.signedAttributes.get(PKCSObjectIdentifiers.id_aa_signingCertificateV2)
            if (scV1 == null && scV2 == null) {
                throw InvalidTimeStampException("no signing certificate attribute present.", null as Exception?)
            } else if (scV1 != null && scV2 != null) {
                throw InvalidTimeStampException("Conflicting signing certificate attributes present.")
            } else {
                this.validateTimeStampToken(tsToken)
            }
        }
    }

    @Throws(InvalidTimeStampException::class, TechnicalConnectorException::class)
    override fun validateTimeStampToken(tsToken: TimeStampToken) {
        Validate.notNull(this.keyStore, "keyStore is not correctly initialised.")
        Validate.notNull(this.aliases, "aliases is not correctly initialised.")
        Validate.notNull(tsToken, "Parameter tsToken value is not nullable.")
        if (tsToken.timeStampInfo != null) {
            LOG.debug("Validating TimeStampToken with SerialNumber [" + tsToken.timeStampInfo.serialNumber + "]")
        }

        var signatureValid = false
        var lastException: Exception? = null

        for (alias in this.aliases!!) {
            try {
                val ttsaCert = this.keyStore!!.getCertificate(alias) as X509Certificate
                LOG.debug("Trying to validate timestamp against certificate with alias [" + alias + "] : [" + ttsaCert.subjectX500Principal.getName("RFC1779") + "]")
                val tokenSigner = X509CertificateHolder(ttsaCert.encoded)
                val verifier = BcRSASignerInfoVerifierBuilder(DefaultCMSSignatureAlgorithmNameGenerator(), DefaultSignatureAlgorithmIdentifierFinder(), DefaultDigestAlgorithmIdentifierFinder(), BcDigestCalculatorProvider()).build(tokenSigner)
                tsToken.validate(verifier)
                signatureValid = true
                break
            } catch (var9: Exception) {
                lastException = var9
                LOG.debug("TimeStampToken not valid with certificate-alias [" + alias + "]: " + var9.message)
            }
        }

        if (!signatureValid) {
            throw InvalidTimeStampException("timestamp is not valid ", lastException)
        } else {
            LOG.debug("timestampToken is valid")
        }
    }

    private fun getAliases(): List<String> {
        try {
            return this.keyStore?.aliases()?.toList() ?: listOf()
        } catch (var2: KeyStoreException) {
            return listOf()
        }

    }

    @Throws(TechnicalConnectorException::class)
    override fun initialize(parameterMap: Map<String, Any>) {
        this.setKeyStore(parameterMap["timestampvalidator.keystore"] as KeyStore?)
        this.aliases = ArrayList()
        val aliasList = this.getAliases()
        if (aliasList != null) {
            this.aliases!!.addAll(aliasList.sortedByDescending { it })
        }
    }

    override fun setKeyStore(keyStore: KeyStore?) {
        this.keyStore = keyStore
    }

    override fun setAliases(aliases: MutableList<String>) {
        this.aliases = aliases
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(TimeStampValidatorImpl::class.java)
    }
}
