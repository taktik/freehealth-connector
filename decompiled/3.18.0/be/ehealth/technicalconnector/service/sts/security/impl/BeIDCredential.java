package be.ehealth.technicalconnector.service.sts.security.impl;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.CredentialException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.KeyStoreFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeIDCredential extends AbstractExtendedCredential implements SessionServiceWithCache {
   private static final Logger LOG = LoggerFactory.getLogger(BeIDCredential.class);
   private static Cache<String, BeIDCredential> instancesMap;
   private static ConfigValidator config;
   public static final String PROP_USE_CACHE = "be.ehealth.technicalconnector.service.sts.security.impl.beidcredential.cache";
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

   public static BeIDCredential getInstance(String scope, String aliasName) {
      String key = scope + "-" + aliasName;
      boolean useCache = config.getBooleanProperty("be.ehealth.technicalconnector.service.sts.security.impl.beidcredential.cache", Boolean.FALSE);
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
         if (!this.certCache.containsKey(alias)) {
            this.certCache.put(alias, (X509Certificate)this.getKeyStore().getCertificate(alias));
         }

         certificate = (X509Certificate)this.certCache.get(alias);
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
      instancesMap = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY, "beid-credential", CacheInformation.ExpiryType.NONE, (Duration)null);
      config = ConfigFactory.getConfigValidator();
      OID_LASTNAME = BCStyle.SURNAME.getId();
      OID_GIVENNAME = BCStyle.GIVENNAME.getId();
      OID_SERIALNUMBER = BCStyle.SN.getId();
   }
}
