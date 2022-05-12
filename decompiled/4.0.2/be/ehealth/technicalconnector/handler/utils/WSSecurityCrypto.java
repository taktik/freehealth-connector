package be.ehealth.technicalconnector.handler.utils;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.regex.Pattern;
import javax.security.auth.callback.CallbackHandler;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.crypto.CryptoType;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.common.ext.WSSecurityException.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSSecurityCrypto implements Crypto {
   private static final Logger LOG = LoggerFactory.getLogger(WSSecurityCrypto.class);
   private final X509Certificate certificate;
   private final PrivateKey privateKey;
   private String trustProvider;

   public WSSecurityCrypto(Credential cred) throws TechnicalConnectorException {
      this(cred.getPrivateKey(), cred.getCertificate());
   }

   public WSSecurityCrypto(PrivateKey privateKey, X509Certificate certificate) {
      this.certificate = certificate;
      this.privateKey = privateKey;
   }

   public X509Certificate[] getX509Certificates(CryptoType cryptoType) throws WSSecurityException {
      LOG.debug("getX509Certificates");
      X509Certificate[] certificates = new X509Certificate[]{this.certificate};
      return certificates;
   }

   public String getCryptoProvider() {
      return this.getClass().getName();
   }

   public byte[] getBytesFromCertificates(X509Certificate[] certs) throws WSSecurityException {
      return (byte[])throwWSSecurityException("getBytesFromCertificates");
   }

   public CertificateFactory getCertificateFactory() throws WSSecurityException {
      return (CertificateFactory)throwWSSecurityException("getCertificateFactory");
   }

   public X509Certificate[] getCertificatesFromBytes(byte[] data) throws WSSecurityException {
      return (X509Certificate[])throwWSSecurityException("getCertificatesFromBytes");
   }

   public String getDefaultX509Identifier() throws WSSecurityException {
      return (String)throwWSSecurityException("getDefaultX509Identifier");
   }

   public PrivateKey getPrivateKey(X509Certificate certificate, CallbackHandler callbackHandler) throws WSSecurityException {
      return this.privateKey;
   }

   public PrivateKey getPrivateKey(PublicKey publicKey, CallbackHandler callbackHandler) throws WSSecurityException {
      return null;
   }

   public PrivateKey getPrivateKey(String identifier, String password) throws WSSecurityException {
      return this.privateKey;
   }

   public void verifyTrust(X509Certificate[] x509Certificates, boolean b, Collection<Pattern> collection, Collection<Pattern> collection1) throws WSSecurityException {
   }

   public byte[] getSKIBytesFromCert(X509Certificate cert) throws WSSecurityException {
      return (byte[])throwWSSecurityException("getSKIBytesFromCert");
   }

   public void verifyTrust(PublicKey publicKey) throws WSSecurityException {
      throwWSSecurityException("verifyTrust");
   }

   public String getX509Identifier(X509Certificate cert) throws WSSecurityException {
      return (String)throwWSSecurityException("getX509Identifier");
   }

   public X509Certificate loadCertificate(InputStream in) throws WSSecurityException {
      return (X509Certificate)throwWSSecurityException("loadCertificate");
   }

   public void setCryptoProvider(String provider) {
      throw new UnsupportedOperationException("Unsupported method setCryptoProvider");
   }

   public String getTrustProvider() {
      return this.trustProvider;
   }

   public void setTrustProvider(String trustProvider) {
      this.trustProvider = trustProvider;
   }

   public void setDefaultX509Identifier(String identifier) {
      throw new UnsupportedOperationException("Unsupported method setDefaultX509Identifier");
   }

   public void setCertificateFactory(CertificateFactory certificateFactory) {
      throw new UnsupportedOperationException("Unsupported method setCertificateFactory");
   }

   private static <T> T throwWSSecurityException(String operation) throws WSSecurityException {
      throw new WSSecurityException(ErrorCode.FAILURE, "Unsupported method {}", new Object[]{operation});
   }
}
