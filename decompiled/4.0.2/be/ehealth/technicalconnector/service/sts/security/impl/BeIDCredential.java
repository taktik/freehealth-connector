package be.ehealth.technicalconnector.service.sts.security.impl;

import be.ehealth.technicalconnector.beid.BeIDFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.CredentialException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.security.auth.x500.X500Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeIDCredential extends AbstractExtendedCredential {
   public static final String PROP_USE_CACHE = "be.ehealth.technicalconnector.service.sts.security.impl.beidcredential.cache";
   public static final String EID_AUTH_ALIAS = "Authentication";
   public static final String EID_SIGN_ALIAS = "Signature";
   private static final Logger LOG = LoggerFactory.getLogger(BeIDCredential.class);
   private String eidAlias;
   private boolean useCache;
   private String cacheKey;
   private KeyStore keyStore;

   private BeIDCredential(String alias, boolean useCache, String cacheKey) {
      this.eidAlias = alias;
      this.useCache = useCache;
      this.cacheKey = cacheKey;
   }

   public static BeIDCredential getInstance(String scope, String aliasName) {
      String cacheKey = scope + "-" + aliasName;
      boolean useCache = ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.technicalconnector.service.sts.security.impl.beidcredential.cache", Boolean.FALSE);
      return new BeIDCredential(aliasName, useCache, cacheKey);
   }

   public PrivateKey getPrivateKey() {
      try {
         String alias = this.getAlias();
         if (!this.getKeyStore().isKeyEntry(alias)) {
            LOG.error("No Private key '{}' in the keystore", alias);
         }

         return (PrivateKey)this.getKeyStore().getKey(alias, (char[])null);
      } catch (Exception var2) {
         LOG.error(var2.getMessage());
         throw new CredentialException(var2);
      }
   }

   public PublicKey getPublicKey() {
      X509Certificate cert = this.getCertificate();
      if (cert == null) {
         LOG.error("Unable to read the certificate of the EID");
         return null;
      } else {
         return cert.getPublicKey();
      }
   }

   public X509Certificate getCertificate() {
      X509Certificate certificate = null;

      try {
         String alias = this.getAlias();
         certificate = (X509Certificate)this.getKeyStore().getCertificate(alias);
      } catch (Exception var3) {
         LOG.warn("getCertificate()", var3);
      }

      return certificate;
   }

   private String getAlias() {
      String eidAutAlias = null;

      try {
         Enumeration<String> aliases = this.getKeyStore().aliases();

         while(aliases.hasMoreElements()) {
            String alias = (String)aliases.nextElement();
            if (this.getKeyStore().isKeyEntry(alias) && alias.equalsIgnoreCase(this.eidAlias)) {
               eidAutAlias = alias;
               break;
            }
         }
      } catch (Exception var4) {
         this.keyStore = null;
         LOG.warn("Unable to deterimine alias", var4);
      }

      return eidAutAlias;
   }

   public String getIssuer() {
      X509Certificate cert = this.getCertificate();
      if (cert == null) {
         LOG.error("Unable to read the certificate of the EID");
         return null;
      } else {
         X500Principal subject = cert.getIssuerX500Principal();
         if (subject == null) {
            LOG.error("Unable to read the SubjectDN of the EID");
            return null;
         } else {
            String issuer = subject.getName("RFC1779");
            this.logDebug("getIssuer: (RFC1779)" + issuer);
            return issuer;
         }
      }
   }

   public String getIssuerQualifier() {
      X509Certificate cert = this.getCertificate();
      if (cert == null) {
         LOG.error("Unable to read the certificate of the EID");
         return null;
      } else {
         X500Principal issuerX500Principal = cert.getIssuerX500Principal();
         if (issuerX500Principal == null) {
            LOG.error("Unable to read the certificate/IssuerX500Principal of the EID");
            return null;
         } else {
            String issuer = issuerX500Principal.getName("RFC1779");
            this.logDebug("getIssuerQualifier: (RFC1779)" + issuer);
            return issuer;
         }
      }
   }

   public KeyStore getKeyStore() throws TechnicalConnectorException {
      if (this.keyStore == null) {
         this.keyStore = BeIDFactory.getKeyStore(this.cacheKey, this.useCache);
      }

      return this.keyStore;
   }

   public String getProviderName() {
      return this.keyStore.getProvider().getName();
   }

   private void logDebug(String message) {
      if (LOG.isDebugEnabled()) {
         LOG.debug(message);
      }

   }

   public Certificate[] getCertificateChain() {
      try {
         return this.getKeyStore().getCertificateChain(this.getAlias());
      } catch (KeyStoreException var2) {
         LOG.error(var2.getMessage());
         throw new CredentialException(var2);
      } catch (TechnicalConnectorException var3) {
         LOG.error(var3.getMessage());
         throw new CredentialException(var3);
      }
   }
}
