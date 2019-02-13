package org.taktik.connector.technical.service.etee;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.ConfigurationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionItem;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.KeyStoreManager;
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
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collection;
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
   private static final String DEFAULT_CERT_CHECKER_CLASS = "org.taktik.connector.technical.service.etee.impl.CryptoImpl";
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
   private static ConfigurableFactoryHelper<Crypto> helper = new ConfigurableFactoryHelper("crypto.classname", "org.taktik.connector.technical.service.etee.impl.CryptoImpl");

   public static Crypto getCrypto(Credential encryption, Map<String, PrivateKey> decryptionKeys, String oCSPPolicy) throws TechnicalConnectorException {
      Map<String, Object> configParameters = new HashMap();
      configParameters.put("datasealer.credential", encryption);
      configParameters.put("dataunsealer.pkmap", decryptionKeys);
      configParameters.put("cryptolib.ocsp.policy", OCSPPolicy.valueOf(oCSPPolicy));
      Map<SigningOption, Object> signingOptions = new HashMap();
      signingOptions.put(SigningOption.SIGNING_TIME_EXPIRATION, configuration.getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_EXPIRATION", Integer.valueOf(5)));
      signingOptions.put(SigningOption.CLOCK_SKEW, configuration.getLongProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.CLOCK_SKEW", 300000L));
      signingOptions.put(SigningOption.SIGNING_TIME_TRUST_IMPLICIT, configuration.getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.SigningOption.SIGNING_TIME_TRUST_IMPLICIT", Boolean.FALSE));
      signingOptions.put(SigningOption.TSA_TRUST_STORE, getKeyStore("timestamp.signature.keystore.path", "timestamp.signature.keystore.pwd"));
      signingOptions.put(SigningOption.TSA_CERT_STORE, generateCertStore("be.fgov.ehealth.etee.crypto.policies.SigningOption.TSA_CERT_STORE"));
      configParameters.put("cryptolib.signing.optionmap", signingOptions);
      configParameters.put("cryptolib.ocsp.optionmap", getOCSPOptions());
      return helper.getImplementation(configParameters, false);
   }

   public static Map<OCSPOption, Object> getOCSPOptions() throws TechnicalConnectorException {
      return CryptoFactory.OCSPOptionHolder.ocspOptionMap;
   }

   public static void resetOCSPOptions() {
      CryptoFactory.OCSPOptionHolder.init();
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

            try {
               KeyStoreManager ocspKeyStoreManager = new KeyStoreManager(keystorePath, pwd);
               keystore = ocspKeyStoreManager.getKeyStore();
            } catch (TechnicalConnectorException var7) {
               LOG.info("Unable to load keystore.", var7);
            }
         }

         if (keystore == null) {
            keystore = KeyStore.getInstance("JKS");
            keystore.load((InputStream)null, password.toCharArray());
         }

         return keystore;
      } catch (Exception var8) {
         throw new ConfigurationException(var8);
      }
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

            try {
               Enumeration enumeration = store.aliases();

               while(enumeration.hasMoreElements()) {
                  certsAndCrls.add(store.getCertificate((String)enumeration.nextElement()));
               }

               LOG.info("Added truststore in CertStore.");
            } catch (KeyStoreException var30) {
               LOG.warn("Unable to add truststore to CertStore", var30);
            }
         }

         java.security.cert.CertificateFactory factory = java.security.cert.CertificateFactory.getInstance("X.509");
         Iterator i$ = configuration.getMatchingProperties(baseKey + ".CERT").iterator();

         String crlLocation;
         InputStream stream;
         while(i$.hasNext()) {
            crlLocation = (String)i$.next();
            stream = null;

            try {
               stream = ConnectorIOUtils.getResourceAsStream(crlLocation);
               certsAndCrls.add(factory.generateCertificate(stream));
               LOG.info("Added " + crlLocation + " as CERT in CertStore.");
            } catch (Exception var28) {
               LOG.error(var28.getClass().getName() + ":" + var28.getMessage(), var28);
            } finally {
               ConnectorIOUtils.closeQuietly((Object)stream);
            }
         }

         i$ = configuration.getMatchingProperties(baseKey + ".CRL").iterator();

         while(i$.hasNext()) {
            crlLocation = (String)i$.next();
            stream = null;

            try {
               stream = ConnectorIOUtils.getResourceAsStream(crlLocation);
               certsAndCrls.add(factory.generateCRL(stream));
               LOG.info("Added " + crlLocation + " as CRL in CertStore.");
            } catch (Exception var26) {
               LOG.error(var26.getClass().getName() + ":" + var26.getMessage(), var26);
            } finally {
               ConnectorIOUtils.closeQuietly((Object)stream);
            }
         }

         return CertStore.getInstance("Collection", new CollectionCertStoreParameters(certsAndCrls));
      } catch (CertificateException var31) {
         LOG.error(var31.getClass().getName() + ":" + var31.getMessage(), var31);
      } catch (InvalidAlgorithmParameterException var32) {
         LOG.error(var32.getClass().getName() + ":" + var32.getMessage(), var32);
      } catch (NoSuchAlgorithmException var33) {
         LOG.error(var33.getClass().getName() + ":" + var33.getMessage(), var33);
      }

      return null;
   }

   private static class OCSPOptionHolder {
      private static Map<OCSPOption, Object> ocspOptionMap;

      public static void init() {
         ocspOptionMap = new HashMap();
         ocspOptionMap.put(OCSPOption.OCSP_URI, CryptoFactory.configuration.getProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.OCSP_URI"));
         KeyStore trustStore = CryptoFactory.getCaCertificateStore();
         ocspOptionMap.put(OCSPOption.TRUST_STORE, trustStore);
         ocspOptionMap.put(OCSPOption.CERT_STORE, CryptoFactory.generateCertStore("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CERT_STORE", trustStore));
         ocspOptionMap.put(OCSPOption.INJECT_RESPONSE, CryptoFactory.configuration.getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.INJECT_RESPONSE", Boolean.FALSE));
         ocspOptionMap.put(OCSPOption.CLOCK_SKEW, CryptoFactory.configuration.getLongProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CLOCK_SKEW", 300000L));
         ocspOptionMap.put(OCSPOption.CONNECTION_TIMEOUT, CryptoFactory.configuration.getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_TIMEOUT", Integer.valueOf(3000)));
         ocspOptionMap.put(OCSPOption.READ_TIMEOUT, CryptoFactory.configuration.getIntegerProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.READ_TIMEOUT", Integer.valueOf(3000)));
         ocspOptionMap.put(OCSPOption.CONNECTION_USER_INTERACTION, CryptoFactory.configuration.getBooleanProperty("be.fgov.ehealth.etee.crypto.policies.OCSPOption.CONNECTION_USER_INTERACTION", Boolean.FALSE));
      }

      public static Map<OCSPOption, Object> getOcspOptionMap() {
         return ocspOptionMap;
      }

      static {
         init();
      }
   }
}
