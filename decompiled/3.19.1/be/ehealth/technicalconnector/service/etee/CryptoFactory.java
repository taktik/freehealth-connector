package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
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
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CryptoFactory {
   private static final Logger LOG = LoggerFactory.getLogger(CryptoFactory.class);
   public static final String PROPS_CRYPTO_CLASS = "crypto.classname";
   private static final String DEFAULT_CERT_CHECKER_CLASS = "be.ehealth.technicalconnector.service.etee.impl.CryptoImpl";
   private static final String TIMESTAMP_SIGNATURE_KEYSTORE_PWD = "timestamp.signature.keystore.pwd";
   private static final String TIMESTAMP_SIGNATURE_KEYSTORE_PATH = "timestamp.signature.keystore.path";
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
   private static final String PROP_CAKEYSTORE_PATH = "CAKEYSTORE_LOCATION";
   private static final String PROP_CAKEYSTORE_PASSWORD = "CAKEYSTORE_PASSWORD";
   private static final String PROP_KEYSTORE_DIR = "KEYSTORE_DIR";
   private static Configuration configuration = ConfigFactory.getConfigValidator();
   private static ConfigurableFactoryHelper<Crypto> helper = new ConfigurableFactoryHelper("crypto.classname", "be.ehealth.technicalconnector.service.etee.impl.CryptoImpl");

   private CryptoFactory() {
   }

   public static Crypto getCrypto(Credential encryption, Map<String, PrivateKey> decryptionKeys, String oCSPPolicy) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap();
      configParameters.put("datasealer.credential", encryption);
      configParameters.put("dataunsealer.pkmap", decryptionKeys);
      configParameters.put("cryptolib.ocsp.policy", OCSPPolicy.valueOf(oCSPPolicy));
      Map<SigningOption, Object> signingOptions = new EnumMap(SigningOption.class);
      signingOptions.put(SigningOption.SIGNING_TIME_EXPIRATION, configuration.getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_EXPIRATION", 5));
      signingOptions.put(SigningOption.CLOCK_SKEW, configuration.getLongProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.CLOCK_SKEW", 300000L));
      signingOptions.put(SigningOption.SIGNING_TIME_TRUST_IMPLICIT, configuration.getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_TRUST_IMPLICIT", Boolean.FALSE));
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
         char[] pwd = configuration.getProperty(password, "").toCharArray();
         String path = configuration.getProperty(key, "");
         if (StringUtils.isNotBlank(path)) {
            String keystorePath = configuration.getProperty("KEYSTORE_DIR", "") + path;
            keystore = loadKeyStore(keystore, pwd, keystorePath);
         }

         if (keystore == null) {
            keystore = KeyStore.getInstance("JKS");
            keystore.load((InputStream)null, password.toCharArray());
         }

         if (LOG.isDebugEnabled()) {
            Enumeration<String> aliases = keystore.aliases();
            LOG.debug("Current keystore [{}] content is: ", key);

            while(aliases.hasMoreElements()) {
               String alias = (String)aliases.nextElement();
               LOG.debug(" .[{}] {} ", alias, ((X509Certificate)keystore.getCertificate(alias)).getSubjectX500Principal().getName("RFC1779"));
            }
         }

         return keystore;
      } catch (Exception var7) {
         throw new ConfigurationException(var7);
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
         KeyStore[] arr$ = stores;
         int len$ = stores.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            KeyStore store = arr$[i$];
            process(certsAndCrls, store);
         }

         java.security.cert.CertificateFactory factory = java.security.cert.CertificateFactory.getInstance("X.509");
         Iterator i$ = configuration.getMatchingProperties(baseKey + ".CERT").iterator();

         String crlLocation;
         while(i$.hasNext()) {
            crlLocation = (String)i$.next();
            processCERT(certsAndCrls, factory, crlLocation);
         }

         i$ = configuration.getMatchingProperties(baseKey + ".CRL").iterator();

         while(i$.hasNext()) {
            crlLocation = (String)i$.next();
            processCRL(certsAndCrls, factory, crlLocation);
         }

         return CertStore.getInstance("Collection", new CollectionCertStoreParameters(certsAndCrls));
      } catch (CertificateException var7) {
         LOG.error(var7.getClass().getName() + ":" + var7.getMessage(), var7);
      } catch (InvalidAlgorithmParameterException var8) {
         LOG.error(var8.getClass().getName() + ":" + var8.getMessage(), var8);
      } catch (NoSuchAlgorithmException var9) {
         LOG.error(var9.getClass().getName() + ":" + var9.getMessage(), var9);
      }

      return null;
   }

   private static void processCRL(Collection certsAndCrls, java.security.cert.CertificateFactory factory, String crlLocation) {
      InputStream stream = null;

      try {
         stream = ConnectorIOUtils.getResourceAsStream(crlLocation);
         certsAndCrls.add(factory.generateCRL(stream));
         LOG.info("Added {} as CRL in CertStore.", crlLocation);
      } catch (Exception var8) {
         LOG.error(var8.getClass().getName() + ":" + var8.getMessage(), var8);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)stream);
      }

   }

   private static void processCERT(Collection certsAndCrls, java.security.cert.CertificateFactory factory, String certLocation) {
      InputStream stream = null;

      try {
         stream = ConnectorIOUtils.getResourceAsStream(certLocation);
         certsAndCrls.add(factory.generateCertificate(stream));
         LOG.info("Added " + certLocation + " as CERT in CertStore.");
      } catch (Exception var8) {
         LOG.error(var8.getClass().getName() + ":" + var8.getMessage(), var8);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)stream);
      }

   }

   private static void process(Collection certsAndCrls, KeyStore store) {
      try {
         Certificate cert;
         for(Enumeration enumeration = store.aliases(); enumeration.hasMoreElements(); certsAndCrls.add(cert)) {
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

      public static synchronized Map<OCSPOption, Object> load() {
         if (ocspOptionMap == null) {
            Map<OCSPOption, Object> map = new EnumMap(OCSPOption.class);
            map.put(OCSPOption.OCSP_URI, CryptoFactory.configuration.getProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.OCSP_URI"));
            KeyStore trustStore = CryptoFactory.getCaCertificateStore();
            map.put(OCSPOption.TRUST_STORE, trustStore);
            map.put(OCSPOption.CERT_STORE, CryptoFactory.generateCertStore("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CERT_STORE", trustStore));
            map.put(OCSPOption.INJECT_RESPONSE, CryptoFactory.configuration.getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.INJECT_RESPONSE", Boolean.FALSE));
            map.put(OCSPOption.CLOCK_SKEW, CryptoFactory.configuration.getLongProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CLOCK_SKEW", 300000L));
            map.put(OCSPOption.CONNECTION_TIMEOUT, CryptoFactory.configuration.getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_TIMEOUT", 3000));
            map.put(OCSPOption.READ_TIMEOUT, CryptoFactory.configuration.getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.READ_TIMEOUT", 3000));
            map.put(OCSPOption.CONNECTION_USER_INTERACTION, CryptoFactory.configuration.getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_USER_INTERACTION", Boolean.FALSE));
            ocspOptionMap = Collections.unmodifiableMap(map);
         }

         return ocspOptionMap;
      }

      public static synchronized void invalidate() {
         ocspOptionMap = null;
      }
   }
}
