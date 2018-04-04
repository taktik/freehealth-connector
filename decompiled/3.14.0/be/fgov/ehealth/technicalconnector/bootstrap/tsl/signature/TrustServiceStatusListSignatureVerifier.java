package be.fgov.ehealth.technicalconnector.bootstrap.tsl.signature;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.ConfigurationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.security.KeyStore;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TrustServiceStatusListSignatureVerifier {
   public static final String PROP_TSL_STOREPWD = "be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.pwd";
   public static final String PROP_TSL_STORELOCATION = "be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.location";
   public static final String PROP_TSL_STORETYPE = "be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.type";
   private static final Logger LOG = LoggerFactory.getLogger(TrustServiceStatusListSignatureVerifier.class);
   private static CertStore tsloStore;
   private static final String OID_TSL_SIGNING = "0.4.0.2231.3.0";

   private TrustServiceStatusListSignatureVerifier() {
      throw new UnsupportedOperationException();
   }

   public static boolean isValid(String xml) {
      try {
         Map<String, Object> options = new HashMap();
         SignatureVerificationResult signatureResult = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).verify(xml.getBytes("UTF-8"), options);
         if (!signatureResult.isValid()) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SIGNATURE_VALIDATION, new Object[]{ArrayUtils.toString(signatureResult.getErrors().toArray())});
         }

         X509Certificate cert = signatureResult.getSigningCert();
         if (cert.getExtendedKeyUsage().contains("0.4.0.2231.3.0")) {
            LOG.debug("ExtendedKeyUsage correct. OID 0.4.0.2231.3.0 found.");
            dumpTsloStore();
            return match(baseOnCert(cert)) || match(basedOnPublicKey(cert));
         }
      } catch (Exception var4) {
         LOG.error("Unable to verify signature Reason:" + var4.getMessage(), var4);
      }

      return false;
   }

   private static void dumpTsloStore() {
      if (LOG.isDebugEnabled()) {
         try {
            LOG.debug("Content of TSLO store");
            Collection<? extends Certificate> tsloCerts = tsloStore.getCertificates(new X509CertSelector());
            Iterator i$ = tsloCerts.iterator();

            while(i$.hasNext()) {
               Certificate tsloCert = (Certificate)i$.next();
               X509Certificate x509 = (X509Certificate)tsloCert;
               LOG.debug(" - " + x509.getSubjectX500Principal().getName("RFC1779"));
            }
         } catch (Exception var4) {
            LOG.debug("Unable to print content of TSLO Store", var4);
         }
      }

   }

   private static boolean match(X509CertSelector selector) throws TechnicalConnectorException {
      try {
         return !tsloStore.getCertificates(selector).isEmpty();
      } catch (CertStoreException var2) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var2, new Object[]{"Unable to select certificates."});
      }
   }

   private static X509CertSelector baseOnCert(X509Certificate cert) {
      LOG.debug("Matching based on cert [" + cert.getSubjectX500Principal().getName("RFC1779") + "]");
      X509CertSelector selector = new X509CertSelector();
      selector.setCertificate(cert);
      return selector;
   }

   private static X509CertSelector basedOnPublicKey(X509Certificate cert) {
      LOG.debug("Matching based on PublicKey [" + cert.getSubjectX500Principal().getName("RFC1779") + "]");
      X509CertSelector selector = new X509CertSelector();
      selector.setSubjectPublicKey(cert.getPublicKey());
      return selector;
   }

   private static CertStore getCertStore() throws Exception {
      ArrayList certsAndCrls = new ArrayList();

      try {
         ConfigValidator config = ConfigFactory.getConfigValidator();
         KeyStore tslStore = KeyStore.getInstance(config.getProperty("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.type", "JKS"));
         tslStore.load(ConnectorIOUtils.getResourceAsStream(config.getProperty("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.location")), config.getProperty("be.fgov.ehealth.technicalconnector.bootstrap.tsl.keystore.pwd", "").toCharArray());
         Enumeration aliases = tslStore.aliases();

         while(aliases.hasMoreElements()) {
            String alias = (String)aliases.nextElement();
            X509Certificate cert = (X509Certificate)tslStore.getCertificate(alias);
            LOG.debug("Adding " + cert.getSubjectX500Principal().getName("RFC1779"));
            certsAndCrls.add(cert);
         }
      } catch (Exception var6) {
         LOG.error("Error while loading keystore", var6);
      }

      return CertStore.getInstance("Collection", new CollectionCertStoreParameters(certsAndCrls));
   }

   public static void reloadCertStore() {
      try {
         tsloStore = getCertStore();
      } catch (Exception var1) {
         throw new ConfigurationException(var1);
      }
   }

   static {
      reloadCertStore();
   }
}
