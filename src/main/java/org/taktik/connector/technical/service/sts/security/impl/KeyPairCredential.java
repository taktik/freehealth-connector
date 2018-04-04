package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class KeyPairCredential extends AbstractExtendedCredential {
   private PrivateKey privateKey;
   private X509Certificate certificate;

   public KeyPairCredential(PrivateKey privateKey, X509Certificate certificate) {
      this.privateKey = privateKey;
      this.certificate = certificate;
   }

   public String getIssuer() {
      return this.certificate.getSubjectX500Principal().getName("RFC1779");
   }

   public String getIssuerQualifier() {
      return this.certificate.getIssuerX500Principal().getName("RFC1779");
   }

   public PublicKey getPublicKey() {
      return this.certificate.getPublicKey();
   }

   public PrivateKey getPrivateKey() {
      return this.privateKey;
   }

   public X509Certificate getCertificate() {
      return this.certificate;
   }

   public String getProviderName() {
      throw new UnsupportedOperationException("getProviderName is not supported.");
   }

   public Certificate[] getCertificateChain() {
      return new Certificate[]{this.certificate};
   }

   public KeyStore getKeyStore() throws TechnicalConnectorException {
      throw new UnsupportedOperationException("getKeyStore is not supported.");
   }
}
