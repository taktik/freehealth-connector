package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.ConfigurationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.KeyStoreManager;
import be.fgov.ehealth.etee.crypto.policies.OCSPOption;
import be.fgov.ehealth.etee.crypto.policies.OCSPPolicy;
import be.fgov.ehealth.etee.crypto.policies.SigningOption;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CryptoFactory {
   public static final String PROPS_CRYPTO_CLASS = "crypto.classname";
   public static final String SIGNING_TIME_EXPIRATION = "be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_EXPIRATION";
   public static final String SIGNING_CLOCK_SKEW = "be.fgov.ehealth.etee.crypto.policies.SigningOption.CLOCK_SKEW";
   public static final String SIGNING_TIME_TRUST_IMPLICIT = "be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_TRUST_IMPLICIT";
   public static final String SIGNING_TSA_CERT_STORE = "be.fgov.ehealth.etee.crypto.policies.SigningOption.TSA_CERT_STORE";
   public static final String OCSP_URI = "be.fgov.ehealth.etee.crypto.policies.OCSPOption.OCSP_URI";
   public static final String OCSP_INJECT_RESPONSE = "be.fgov.ehealth.etee.crypto.policies.OCSPOption.INJECT_RESPONSE";
   public static final String OCSP_CLOCK_SKEW = "be.fgov.ehealth.etee.crypto.policies.OCSPOption.CLOCK_SKEW";
   public static final String OCSP_CONNECTION_TIMEOUT = "be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_TIMEOUT";
   public static final String OCSP_CERT_STORE = "be.fgov.ehealth.etee.crypto.policies.OCSPOption.CERT_STORE";
   public static final String OCSP_READ_TIMEOUT = "be.fgov.ehealth.etee.crypto.policies.OCSPOption.READ_TIMEOUT";
   public static final String OCSP_CONNECTION_USER_INTERACTION = "be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_USER_INTERACTION";
   private static final Logger LOG = LoggerFactory.getLogger(CryptoFactory.class);
   private static final String DEFAULT_CERT_CHECKER_CLASS = "be.ehealth.technicalconnector.service.etee.impl.CryptoImpl";
   private static final String TIMESTAMP_SIGNATURE_KEYSTORE_PWD = "timestamp.signature.keystore.pwd";
   private static final String TIMESTAMP_SIGNATURE_KEYSTORE_PATH = "timestamp.signature.keystore.path";
   private static final String PROP_CAKEYSTORE_PATH = "CAKEYSTORE_LOCATION";
   private static final String PROP_CAKEYSTORE_PASSWORD = "CAKEYSTORE_PASSWORD";
   private static final String PROP_KEYSTORE_DIR = "KEYSTORE_DIR";
   private static final ConfigurableFactoryHelper<Crypto> helper = new ConfigurableFactoryHelper("crypto.classname", "be.ehealth.technicalconnector.service.etee.impl.CryptoImpl");

   private CryptoFactory() {
   }

   public static Crypto getCrypto(Credential encryption, Map<String, PrivateKey> decryptionKeys, String oCSPPolicy) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap();
      configParameters.put("datasealer.credential", encryption);
      configParameters.put("dataunsealer.pkmap", decryptionKeys);
      configParameters.put("cryptolib.ocsp.policy", OCSPPolicy.valueOf(oCSPPolicy));
      Map<SigningOption, Object> signingOptions = new EnumMap(SigningOption.class);
      signingOptions.put(SigningOption.SIGNING_TIME_EXPIRATION, ConfigFactory.getConfigValidator().getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_EXPIRATION", 5));
      signingOptions.put(SigningOption.CLOCK_SKEW, ConfigFactory.getConfigValidator().getLongProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.CLOCK_SKEW", 300000L));
      signingOptions.put(SigningOption.SIGNING_TIME_TRUST_IMPLICIT, ConfigFactory.getConfigValidator().getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_TRUST_IMPLICIT", Boolean.FALSE));
      signingOptions.put(SigningOption.TSA_TRUST_STORE, getKeyStore("timestamp.signature.keystore.path", "timestamp.signature.keystore.pwd"));
      signingOptions.put(SigningOption.TSA_CERT_STORE, generateCertStore("be.fgov.ehealth.etee.crypto.policies.SigningOption.TSA_CERT_STORE"));
      configParameters.put("cryptolib.signing.optionmap", signingOptions);
      configParameters.put("cryptolib.ocsp.optionmap", getOCSPOptions());
      return (Crypto)helper.getImplementation(configParameters);
   }

   public static Map<OCSPOption, Object> getOCSPOptions() {
      return CryptoFactory.OCSPOptionHolder.load();
   }

   public static void resetOCSPOptions() {
      CryptoFactory.OCSPOptionHolder.invalidate();
      CryptoFactory.OCSPOptionHolder.load();
   }

   public static KeyStore getCaCertificateStore() {
      return getKeyStore("CAKEYSTORE_LOCATION", "CAKEYSTORE_PASSWORD");
   }

   private static KeyStore getKeyStore(String key, String password) {
      try {
         KeyStore keystore = null;
         char[] pwd = ConfigFactory.getConfigValidator().getProperty(password, "").toCharArray();
         String path = ConfigFactory.getConfigValidator().getProperty(key, "");
         if (StringUtils.isNotBlank(path)) {
            String keystorePath = ConfigFactory.getConfigValidator().getProperty("KEYSTORE_DIR", "") + path;
            keystore = loadKeyStore(keystore, pwd, keystorePath);
         }

         if (keystore == null) {
            keystore = KeyStore.getInstance("JKS");
            keystore.load((InputStream)null, password.toCharArray());
         }

         LOG.debug("Current keystore [{}] content is: ", key);
         dump(keystore);
         return keystore;
      } catch (Exception var6) {
         throw new ConfigurationException(var6);
      }
   }

   private static void dump(KeyStore keystore) throws KeyStoreException {
      if (LOG.isDebugEnabled()) {
         Enumeration<String> aliases = keystore.aliases();
         List<String> aliasList = new ArrayList();

         while(aliases.hasMoreElements()) {
            aliasList.add(aliases.nextElement());
         }

         Collections.sort(aliasList);
         Iterator var3 = aliasList.iterator();

         while(var3.hasNext()) {
            String alias = (String)var3.next();
            LOG.debug(" .[{}] {} ", alias, ((X509Certificate)keystore.getCertificate(alias)).getSubjectX500Principal().getName("RFC1779"));
         }
      }

   }

   private static KeyStore loadKeyStore(KeyStore keystore, char[] pwd, String keystorePath) {
      try {
         KeyStoreManager ocspKeyStoreManager = new KeyStoreManager(keystorePath, pwd);
         keystore = ocspKeyStoreManager.getKeyStore();
      } catch (TechnicalConnectorException var4) {
         LOG.info("Unable to load keystore.", var4);
      }

      return keystore;
   }

   public static Crypto getCrypto(Credential encryption, Map<String, PrivateKey> decryptionKeys) throws TechnicalConnectorException {
      return getCrypto(encryption, decryptionKeys, "NONE");
   }

   public static Crypto getCryptoFromSession() throws TechnicalConnectorException {
      SessionItem session = Session.getInstance().getSession();
      return getCrypto(session.getEncryptionCredential(), session.getEncryptionPrivateKeys());
   }

   private static CertStore generateCertStore(String baseKey, KeyStore... stores) {
      try {
         Collection certsAndCrls = new ArrayList();
         KeyStore[] var3 = stores;
         int var4 = stores.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            KeyStore store = var3[var5];
            process(certsAndCrls, store);
         }

         CertificateFactory factory = CertificateFactory.getInstance("X.509");
         Iterator var9 = ConfigFactory.getConfigValidator().getMatchingProperties(baseKey + ".CERT").iterator();

         String crlLocation;
         while(var9.hasNext()) {
            crlLocation = (String)var9.next();
            processCERT(certsAndCrls, factory, crlLocation);
         }

         var9 = ConfigFactory.getConfigValidator().getMatchingProperties(baseKey + ".CRL").iterator();

         while(var9.hasNext()) {
            crlLocation = (String)var9.next();
            processCRL(certsAndCrls, factory, crlLocation);
         }

         return CertStore.getInstance("Collection", new CollectionCertStoreParameters(certsAndCrls));
      } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | CertificateException var7) {
         LOG.error(var7.getClass().getName() + ":" + var7.getMessage(), var7);
         return null;
      }
   }

   private static void processCRL(Collection certsAndCrls, CertificateFactory factory, String crlLocation) {
      try {
         InputStream stream = ConnectorIOUtils.getResourceAsStream(crlLocation);
         Throwable var4 = null;

         try {
            certsAndCrls.add(factory.generateCRL(stream));
            LOG.info("Added {} as CRL in CertStore.", crlLocation);
         } catch (Throwable var14) {
            var4 = var14;
            throw var14;
         } finally {
            if (stream != null) {
               if (var4 != null) {
                  try {
                     stream.close();
                  } catch (Throwable var13) {
                     var4.addSuppressed(var13);
                  }
               } else {
                  stream.close();
               }
            }

         }
      } catch (Exception var16) {
         LOG.error(var16.getClass().getName() + ":" + var16.getMessage(), var16);
      }

   }

   private static void processCERT(Collection certsAndCrls, CertificateFactory factory, String certLocation) {
      try {
         InputStream stream = ConnectorIOUtils.getResourceAsStream(certLocation);
         Throwable var4 = null;

         try {
            certsAndCrls.add(factory.generateCertificate(stream));
            LOG.info("Added " + certLocation + " as CERT in CertStore.");
         } catch (Throwable var14) {
            var4 = var14;
            throw var14;
         } finally {
            if (stream != null) {
               if (var4 != null) {
                  try {
                     stream.close();
                  } catch (Throwable var13) {
                     var4.addSuppressed(var13);
                  }
               } else {
                  stream.close();
               }
            }

         }
      } catch (Exception var16) {
         LOG.error(var16.getClass().getName() + ":" + var16.getMessage(), var16);
      }

   }

   private static void process(Collection certsAndCrls, KeyStore store) {
      try {
         Certificate cert;
         for(Enumeration<String> enumeration = store.aliases(); enumeration.hasMoreElements(); certsAndCrls.add(cert)) {
            cert = store.getCertificate((String)enumeration.nextElement());
            if (LOG.isDebugEnabled() && cert instanceof X509Certificate) {
               LOG.debug("Adding certificate {}", ((X509Certificate)cert).getSubjectX500Principal().getName("RFC1779"));
            }
         }

         LOG.info("Added truststore in CertStore.");
      } catch (KeyStoreException var4) {
         LOG.warn("Unable to add truststore to CertStore", var4);
      }

   }

   private static class OCSPOptionHolder {
      private static Map<OCSPOption, Object> ocspOptionMap;

      private OCSPOptionHolder() {
      }

      public static synchronized Map<OCSPOption, Object> load() {
         if (ocspOptionMap == null) {
            Map<OCSPOption, Object> map = new EnumMap(OCSPOption.class);
            map.put(OCSPOption.OCSP_URI, ConfigFactory.getConfigValidator().getProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.OCSP_URI"));
            KeyStore trustStore = CryptoFactory.getCaCertificateStore();
            map.put(OCSPOption.TRUST_STORE, trustStore);
            map.put(OCSPOption.CERT_STORE, CryptoFactory.generateCertStore("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CERT_STORE", trustStore));
            map.put(OCSPOption.INJECT_RESPONSE, ConfigFactory.getConfigValidator().getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.INJECT_RESPONSE", Boolean.FALSE));
            map.put(OCSPOption.CLOCK_SKEW, ConfigFactory.getConfigValidator().getLongProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CLOCK_SKEW", 300000L));
            map.put(OCSPOption.CONNECTION_TIMEOUT, ConfigFactory.getConfigValidator().getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_TIMEOUT", 3000));
            map.put(OCSPOption.READ_TIMEOUT, ConfigFactory.getConfigValidator().getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.READ_TIMEOUT", 3000));
            map.put(OCSPOption.CONNECTION_USER_INTERACTION, ConfigFactory.getConfigValidator().getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_USER_INTERACTION", Boolean.FALSE));
            ocspOptionMap = Collections.unmodifiableMap(map);
         }

         return ocspOptionMap;
      }

      public static synchronized void invalidate() {
         ocspOptionMap = null;
      }
   }
}
