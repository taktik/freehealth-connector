package org.taktik.freehealth.middleware.pkcs11.beid

import java.lang.IllegalArgumentException
import java.security.NoSuchAlgorithmException
import java.security.Provider

class RemoteBeIDProvider(beIDCard: RemoteBeIDCard?) : Provider("BeIDProvider", 1.0, "BeID Provider") {

    /**
     * Inner class used by [BeIDProvider].
     *
     * @author Frank Cornelis
     */
    private class BeIDService(
        provider: Provider?, type: String?,
        algorithm: String, className: String?,
        val beIDCard: RemoteBeIDCard?,
        attributes: Map<String, String>? = null
    ) : Service(provider, type, algorithm, className, null, attributes) {
        @Throws(NoSuchAlgorithmException::class)
        override fun newInstance(constructorParameter: Any?): Any {
            return if (super.getType() == "KeyStore") {
                beIDCard?.let { RemoteBeIDKeyStoreSpi(it) } ?: throw IllegalArgumentException("Card has to be provided for Keystore Service");
            } else if (super.getType() == "Signature") {
                RemoteBeIDSignature(algorithm)
            } else if (super.getType() == "KeyManagerFactory") {
                beIDCard?.let { RemoteBeIDKeyManagerFactory(it) } ?: throw IllegalArgumentException("Card has to be provided for KeyManagerFactory Service")
            } else super.newInstance(constructorParameter)
        }
    }

    init {
        val signatureServiceAttributes= mapOf("SupportedKeyClasses" to RemoteBeIDPrivateKey::class.java.name)

        if (beIDCard != null) {
            putService(BeIDService(this, "KeyStore", "BeID", RemoteBeIDKeyStoreSpi::class.java.name, beIDCard))
            putService(BeIDService(this, "KeyManagerFactory", "BeID", RemoteBeIDKeyManagerFactory::class.java.name, beIDCard))
        }
        putService(BeIDService(this, "Signature", "SHA1withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "SHA224withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "SHA256withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "SHA384withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "SHA512withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "NONEwithRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "RIPEMD128withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "RIPEMD160withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "RIPEMD256withRSA", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "SHA1withRSAandMGF1", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "Signature", "SHA256withRSAandMGF1", RemoteBeIDSignature::class.java.name, beIDCard, signatureServiceAttributes))
        putService(BeIDService(this, "SecureRandom", "BeID", RemoteBeIDSecureRandom::class.java.name, beIDCard))
    }

}
