package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.CredentialException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.KeyStoreFactory;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionServiceWithCache;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeIDCredential extends AbstractExtendedCredential implements SessionServiceWithCache {
   private static final Logger LOG = LoggerFactory.getLogger(BeIDCredential.class);
   private static Cache<String, BeIDCredential> instancesMap;
   private static ConfigValidator config;
   public static final String PROP_USE_CACHE = "org.taktik.connector.technical.service.sts.security.impl.beidcredential.cache";
   /** @deprecated */
   @Deprecated
   public static final String OID_LASTNAME;
   /** @deprecated */
   @Deprecated
   public static final String OID_GIVENNAME;
   /** @deprecated */
   @Deprecated
   public static final String OID_SERIALNUMBER;
   public static final String EID_AUTH_ALIAS = "Authentication";
   public static final String EID_SIGN_ALIAS = "Signature";
   private String eidAlias;
   private KeyStore keyStore;
   private Map<String, X509Certificate> certCache = new HashMap();

   private BeIDCredential(String alias) {
      this.eidAlias = alias;
      Session.getInstance().registerSessionService(this);
   }

   public static BeIDCredential getInstance(String scope, String aliasName) throws TechnicalConnectorException {
      String key = scope + "-" + aliasName;
      boolean useCache = config.getBooleanProperty("org.taktik.connector.technical.service.sts.security.impl.beidcredential.cache", Boolean.FALSE).booleanValue();
      if (useCache && instancesMap.containsKey(key)) {
         LOG.debug("Returning cached instance.");
         return (BeIDCredential)instancesMap.get(key);
      } else {
         BeIDCredential newInstance = new BeIDCredential(aliasName);
         if (useCache) {
            instancesMap.put(key, newInstance);
         }

         return newInstance;
      }
   }

   public PrivateKey getPrivateKey() {
      try {
         if (!this.getKeyStore().isKeyEntry(this.getAlias())) {
            LOG.error("No Private key '" + this.getAlias() + "' in the keystore");
         }

         return (PrivateKey)this.getKeyStore().getKey(this.getAlias(), (char[])null);
      } catch (Exception var2) {
         LOG.error(var2.getMessage());
         throw new CredentialException(var2);
      }
   }

   public PublicKey getPublicKey() {
      if (this.getCertificate() == null) {
         LOG.error("Unable to read the certificate of the EID");
         return null;
      } else {
         return this.getCertificate().getPublicKey();
      }
   }

   public X509Certificate getCertificate() {
      X509Certificate certificate = null;

      try {
         if (!this.certCache.containsKey(this.getAlias())) {
            this.certCache.put(this.getAlias(), (X509Certificate)this.getKeyStore().getCertificate(this.getAlias()));
         }

         certificate = (X509Certificate)this.certCache.get(this.getAlias());
      } catch (Exception var3) {
         LOG.warn("getCertificate()", var3);
      }

      return certificate;
   }

   private String getAlias() {
      String eidAutAlias = null;

      try {
         Enumeration aliases = this.getKeyStore().aliases();

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
      if (this.getCertificate() == null) {
         LOG.error("Unable to read the certificate of the EID");
         return null;
      } else if (this.getCertificate().getSubjectDN() == null) {
         LOG.error("Unable to read the SubjectDN of the EID");
         return null;
      } else {
         X509Certificate cert = this.getCertificate();
         this.logDebug("getIssuer: (RFC1779)" + cert.getSubjectX500Principal().getName("RFC1779"));
         return cert.getSubjectX500Principal().getName("RFC1779");
      }
   }

   public String getIssuerQualifier() {
      if (this.getCertificate() == null) {
         LOG.error("Unable to read the certificate of the EID");
         return null;
      } else if (this.getCertificate().getIssuerX500Principal() == null) {
         LOG.error("Unable to read the certificate/IssuerX500Principal of the EID");
         return null;
      } else {
         X509Certificate cert = this.getCertificate();
         this.logDebug("getIssuerQualifier: (RFC1779)" + cert.getIssuerX500Principal().getName("RFC1779"));
         return cert.getIssuerX500Principal().getName("RFC1779");
      }
   }

   public KeyStore getKeyStore() throws TechnicalConnectorException {
      if (this.keyStore == null) {
         this.keyStore = KeyStoreFactory.getKeyStore();
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

   public void flushCache() {
      this.certCache.clear();
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

   static {
      instancesMap = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY);
      config = ConfigFactory.getConfigValidator();
      OID_LASTNAME = BCStyle.SURNAME.getId();
      OID_GIVENNAME = BCStyle.GIVENNAME.getId();
      OID_SERIALNUMBER = BCStyle.SN.getId();
   }
}
