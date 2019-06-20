package be.fgov.ehealth.technicalconnector.ra.utils;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import org.apache.commons.lang.Validate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class KeyStoreManager {
   private static final String PROVIDER = "BC";
   private KeyStore store;

   public KeyStoreManager() {
      this("PKCS12");
   }

   public KeyStoreManager(String storeType) {
      try {
         this.store = KeyStore.getInstance(storeType, "BC");
         this.store.load((InputStream)null, "".toCharArray());
      } catch (Exception var3) {
         throw new IllegalArgumentException(var3);
      }
   }

   public synchronized void load(char[] passwd, byte[] content) {
      Validate.notNull(content);
      Validate.isTrue(content.length > 0);
      ByteArrayInputStream bais = null;

      try {
         bais = new ByteArrayInputStream(content);
         this.store.load(bais, passwd);
      } catch (Exception var8) {
         throw new IllegalArgumentException(var8);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)bais);
      }

   }

   public void addAuthenticationChain(char[] passwd, X509Certificate... chain) throws TechnicalConnectorException {
      this.addChainToEntry("authentication", passwd, chain);
   }

   private void addChainToEntry(String alias, char[] passwd, X509Certificate... chain) throws TechnicalConnectorException {
      try {
         Key key = this.store.getKey(alias, passwd);
         this.store.setKeyEntry(alias, key, passwd, chain);
         this.addCertificates(chain);
      } catch (Exception var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var5, new Object[]{var5.getMessage()});
      }
   }

   private void addCertificates(X509Certificate[] certs) throws TechnicalConnectorException {
      X509Certificate[] arr$ = certs;
      int len$ = certs.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         X509Certificate cert = arr$[i$];
         this.addCertificate(cert);
      }

   }

   private void addCertificate(X509Certificate cert) throws TechnicalConnectorException {
      try {
         String alias = cert.getSubjectX500Principal().getName("RFC2253");
         this.store.setCertificateEntry(alias, cert);
      } catch (KeyStoreException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var3, new Object[]{var3.getMessage()});
      }
   }

   public void addEncryptionToken(KeyPair entry, char[] passwd, X509Certificate etkCert) throws TechnicalConnectorException {
      try {
         this.store.setKeyEntry(etkCert.getSerialNumber().toString(10), entry.getPrivate(), passwd, new Certificate[]{etkCert});
      } catch (Exception var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var5, new Object[]{var5.getMessage()});
      }
   }

   private void addKey(KeyPair entry, char[] passwd, String alias, Certificate... chain) throws TechnicalConnectorException {
      try {
         this.store.setKeyEntry(alias, entry.getPrivate(), passwd, chain);
      } catch (Exception var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var6, new Object[]{var6.getMessage()});
      }
   }

   public void addAuthenticationKeyPair(KeyPair entry, char[] passwd) throws TechnicalConnectorException {
      this.addKey(entry, passwd, "authentication", CertificateUtils.generateCert(entry));
   }

   public void store(String location, char[] passwd) throws TechnicalConnectorException {
      FileOutputStream fos = null;

      try {
         File out = new File(location);
         if (out.exists() && !out.delete()) {
            throw new IOException("Unable to delete file. [" + location + "]");
         }

         if (!out.createNewFile()) {
            throw new IOException("Unable to create new file. [" + location + "]");
         }

         fos = new FileOutputStream(out);
         this.store.store(fos, passwd);
      } catch (Exception var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var8, new Object[]{var8.getMessage()});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }

   }

   static {
      Security.addProvider(new BouncyCastleProvider());
   }
}
