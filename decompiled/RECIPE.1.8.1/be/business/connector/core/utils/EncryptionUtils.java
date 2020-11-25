package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealerBuilder;
import be.fgov.ehealth.etee.crypto.decrypt.UnsealedData;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealerBuilder;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.etee.crypto.policies.EncryptionCredentials;
import be.fgov.ehealth.etee.crypto.policies.EncryptionPolicy;
import be.fgov.ehealth.etee.crypto.policies.OCSPPolicy;
import be.fgov.ehealth.etee.crypto.policies.SigningCredential;
import be.fgov.ehealth.etee.crypto.policies.SigningPolicy;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

public class EncryptionUtils {
   private static final String KEYSTORE_DIR = "KEYSTORE_DIR";
   private static final String TSA_KEYSTORE_TYPE = "timestamp.signature.keystore.type";
   private static final String TSA_KEYSTORE_FILE = "timestamp.signature.keystore.path";
   private static final String TSA_KEYSTORE_PASSWORD = "timestamp.signature.keystore.pwd";
   private static final char[] DEFAULT_PASSWORD = "hello!".toCharArray();
   public static final String AUTHENTICATION_ALIAS = "authentication";
   private static final String AUTHENTICATION_FALLBACK_ALIAS = "authentication_fallback";
   public static final String PROP_KEYSTORE_FILE = "sessionmanager.holderofkey.keystore";
   private static final String PROP_OLD_KEYSTORE_FILE = "OLD_KEYSTORE_FILE";
   private static final String PKCS12 = "PKCS12";
   public static final String PROP_KEYSTORE_PASSWORD = "KEYSTORE_PASSWORD";
   private static final String PROP_OLD_KEYSTORE_PASSWORD = "OLD_KEYSTORE_PASSWORD";
   public static final String PROP_KEYSTORE_P12_FOLDER = "KEYSTORE_DIR";
   private static final String PROP_KEYSTORE_OLD_P12_FOLDER = "KEYSTORE_AUTH_OLD_P12_FOLDER";
   private static final String PERSONAL_KEYSTORE = "PERSONAL_KEYSTORE";
   private static final String PROP_KEYSTORE_MANDATE_FILE = "KEYSTORE_FILE_MANDATE_ORGANISATION";
   private static final String PROP_KEYSTORE_MANDATE_PASSWORD = "KEYSTORE_PASSWORD_MANDATE_ORGANISATION";
   private static final String PROP_KEYSTORE_RIZIV_KBO = "PROP_KEYSTORE_RIZIV_KBO";
   private static final String PROP_OLD_KEYSTORE_RIZIV_KBO = "PROP_OLD_KEYSTORE_RIZIV_KBO";
   private static final String LOCAL_CA_CERTIFICATES_STORE_FILE = "CAKEYSTORE_LOCATION";
   private static final String LOCAL_CA_CERTIFICATES_STORE_TYPE = "LOCAL_CA_CERTIFICATES_STORE_TYPE";
   private static final String LOCAL_CA_CERTIFICATES_STORE_PASSWORD = "CAKEYSTORE_PASSWORD";
   private static final String SYMM_KEY_PROPERTY = "symmKey";
   private KeyStore keyStore;
   private KeyStore tsaKeyStore;
   private KeyStore mandateKeyStore;
   private KeyStore oldKeyStore;
   private List<String> tsaStoreAliases;
   private PropertyHandler propertyHandler = null;
   private static final Logger LOG = Logger.getLogger(EncryptionUtils.class);
   private static EncryptionUtils instance = null;
   private String systemKeystorePath;
   private String oldSystemKeystorePath;
   private String caCertificateKeystoreLocation;
   private final String encryptionSignature = "ENCRYPTI0NS1GNATURE";

   public EncryptionUtils(PropertyHandler propertyHandler) {
      this.propertyHandler = propertyHandler;
      instance = this;
   }

   public static EncryptionUtils getInstance() {
      return instance;
   }

   public Key generateSecretKey() throws IntegrationModuleException {
      try {
         if (this.propertyHandler.hasProperty("symmKey")) {
            String base64key = this.propertyHandler.getProperty("symmKey");
            DESedeKeySpec keyspec = new DESedeKeySpec(Base64.decode(base64key));
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = keyfactory.generateSecret(keyspec);
            return key;
         } else {
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            return keyGen.generateKey();
         }
      } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException var5) {
         throw new IntegrationModuleException(var5);
      }
   }

   public static byte[] unsealWithSymmKey(Key symmKey, byte[] objectToUnseal) {
      try {
         Cipher cipher = Cipher.getInstance("DESede");
         cipher.init(2, symmKey);
         return cipher.doFinal(objectToUnseal);
      } catch (Exception var3) {
         LOG.error("unsealWithSymmKey Error", var3);
         return null;
      }
   }

   public KeyStore getKeyStore() throws IntegrationModuleException {
      if (this.keyStore == null) {
         try {
            InputStream stream = this.getSystemKeystoreStream();
            String pwd = this.getSystemKeystorePassword();

            try {
               this.keyStore = KeyManager.getKeyStore(stream, "PKCS12", pwd.toCharArray());
            } catch (IOException var9) {
               if (var9.getCause() instanceof BadPaddingException) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.password"), var9);
               }

               throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"), var9);
            }

            LOG.debug("Iterating over keyStore.aliases()");
            Enumeration<String> aliases = this.keyStore.aliases();
            ArrayList keyEntries = new ArrayList();

            String alias;
            while(aliases.hasMoreElements()) {
               alias = (String)aliases.nextElement();
               LOG.debug("Checking key : " + alias);
               if (this.keyStore.isKeyEntry(alias)) {
                  keyEntries.add(alias);
               }
            }

            LOG.debug("Deleting entries of keyStore.aliases()");
            Iterator var6 = keyEntries.iterator();

            while(var6.hasNext()) {
               alias = (String)var6.next();
               Key key = this.keyStore.getKey(alias, pwd.toCharArray());
               Certificate[] chain = this.keyStore.getCertificateChain(alias);
               this.keyStore.deleteEntry(alias);
               this.keyStore.setKeyEntry(alias, key, DEFAULT_PASSWORD, chain);
            }

            this.dumpKeyStore(this.keyStore);
         } catch (Throwable var10) {
            LOG.error("Invalid keystore configuration", var10);
            Exceptionutils.errorHandler(var10, "error.keystore");
         }
      }

      return this.keyStore;
   }

   public KeyStore getTSAKeyStore() throws IntegrationModuleException {
      if (this.tsaKeyStore == null) {
         try {
            InputStream stream = null;
            char[] pwd = null;
            stream = this.getTSAKeystoreStream();
            char[] pwd = this.propertyHandler.getProperty("timestamp.signature.keystore.pwd").toCharArray();

            try {
               this.tsaKeyStore = KeyManager.getKeyStore(stream, this.propertyHandler.getProperty("timestamp.signature.keystore.type"), pwd);
            } catch (IOException var5) {
               if (var5.getCause() instanceof BadPaddingException) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.tsaStore.password"), var5);
               }

               throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"), var5);
            }

            LOG.debug("Iterating over tsaKeyStore.aliases()");
            Enumeration<String> aliases = this.tsaKeyStore.aliases();
            this.tsaStoreAliases = new ArrayList();

            while(aliases.hasMoreElements()) {
               String alias = (String)aliases.nextElement();
               LOG.debug("Checking key : " + alias);
               this.tsaStoreAliases.add(alias);
            }
         } catch (Throwable var6) {
            LOG.error("Invalid keystore configuration", var6);
            Exceptionutils.errorHandler(var6, "error.tsaStore");
         }
      }

      return this.tsaKeyStore;
   }

   public List<String> getTsaStoreAliases() {
      return this.tsaStoreAliases;
   }

   private InputStream getSystemKeystoreStream() throws IOException, IntegrationModuleException {
      return IOUtils.getResourceAsStream(this.getSystemKeystorePath());
   }

   public String getSystemKeystorePath() throws IntegrationModuleException {
      if (!StringUtils.isBlank(this.systemKeystorePath)) {
         return this.systemKeystorePath;
      } else {
         String keyStoreDir;
         String keyStoreFile;
         if (this.propertyHandler.hasProperty("KEYSTORE_DIR") && this.propertyHandler.hasProperty("PROP_KEYSTORE_RIZIV_KBO")) {
            keyStoreDir = this.propertyHandler.getProperty("KEYSTORE_DIR");
            keyStoreFile = this.propertyHandler.getProperty("PROP_KEYSTORE_RIZIV_KBO");
            List<String> files = IOUtils.getP12FileList(keyStoreDir, keyStoreFile);
            String file = (String)files.get(0);
            return file;
         } else if (this.propertyHandler.hasProperty("sessionmanager.holderofkey.keystore") && this.propertyHandler.hasProperty("KEYSTORE_DIR")) {
            keyStoreDir = this.propertyHandler.getProperty("KEYSTORE_DIR");
            keyStoreFile = this.propertyHandler.getProperty("sessionmanager.holderofkey.keystore");
            String keyStoreFilePath = "";
            if (StringUtils.endsWith(keyStoreDir, "/")) {
               keyStoreFilePath = keyStoreDir + keyStoreFile;
            } else {
               keyStoreFilePath = keyStoreDir + "/" + keyStoreFile;
            }

            return keyStoreFilePath;
         } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.file"));
         }
      }
   }

   private InputStream getTSAKeystoreStream() throws IOException, IntegrationModuleException {
      InputStream stream = null;
      String tSAKeyStoreDir = this.propertyHandler.getProperty("KEYSTORE_DIR");
      String tSAKeyStoreFile = this.propertyHandler.getProperty("timestamp.signature.keystore.path");
      String keyStoreFilePath = "";
      if (StringUtils.endsWith(tSAKeyStoreDir, "/")) {
         keyStoreFilePath = tSAKeyStoreDir + tSAKeyStoreFile;
      } else {
         keyStoreFilePath = tSAKeyStoreDir + "/" + tSAKeyStoreFile;
      }

      stream = IOUtils.getResourceAsStream(keyStoreFilePath);
      return stream;
   }

   public KeyStore getOldKeyStore() throws IntegrationModuleException {
      if (this.oldKeyStore == null) {
         try {
            InputStream stream = this.getOldSystemKeystoreStream();
            String pwd = this.getOldSystemKeystorePassword();
            if (stream != null) {
               try {
                  this.oldKeyStore = KeyManager.getKeyStore(stream, "PKCS12", pwd.toCharArray());
               } catch (IOException var7) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.password"), var7);
               }

               Enumeration aliases = this.oldKeyStore.aliases();

               while(aliases.hasMoreElements()) {
                  String alias = (String)aliases.nextElement();
                  LOG.debug("Checking key : " + alias);
                  if (this.oldKeyStore.isKeyEntry(alias)) {
                     Key key = this.oldKeyStore.getKey(alias, pwd.toCharArray());
                     Certificate[] chain = this.oldKeyStore.getCertificateChain(alias);
                     this.oldKeyStore.deleteEntry(alias);
                     this.oldKeyStore.setKeyEntry(alias, key, DEFAULT_PASSWORD, chain);
                  }
               }

               this.dumpKeyStore(this.oldKeyStore);
            }
         } catch (Exception var8) {
            LOG.error("Invalid old keystore configuration", var8);
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore"), var8);
         }
      }

      return this.oldKeyStore;
   }

   public String getOldSystemKeystorePath() {
      if (!StringUtils.isEmpty(this.oldSystemKeystorePath)) {
         return this.oldSystemKeystorePath;
      } else if (this.propertyHandler.hasProperty("KEYSTORE_AUTH_OLD_P12_FOLDER") && this.propertyHandler.hasProperty("PROP_OLD_KEYSTORE_RIZIV_KBO")) {
         String directory = this.propertyHandler.getProperty("KEYSTORE_AUTH_OLD_P12_FOLDER");
         String riziv = this.propertyHandler.getProperty("PROP_OLD_KEYSTORE_RIZIV_KBO");
         List<String> files = IOUtils.getP12FileList(directory, riziv);
         return (String)files.get(0);
      } else {
         return this.propertyHandler.hasProperty("OLD_KEYSTORE_FILE") ? this.propertyHandler.getProperty("OLD_KEYSTORE_FILE") : null;
      }
   }

   private InputStream getOldSystemKeystoreStream() throws IOException {
      String path = this.getOldSystemKeystorePath();
      return StringUtils.isBlank(path) ? null : IOUtils.getResourceAsStream(path);
   }

   public KeyStore getMandateOrganisationKeyStore() {
      if (this.mandateKeyStore == null) {
         try {
            InputStream stream = null;
            char[] pwd = null;
            if (!this.propertyHandler.hasProperty("KEYSTORE_FILE_MANDATE_ORGANISATION")) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.file"));
            }

            stream = IOUtils.getResourceAsStream(this.propertyHandler.getProperty("KEYSTORE_FILE_MANDATE_ORGANISATION"));
            if (!this.propertyHandler.hasProperty("KEYSTORE_PASSWORD_MANDATE_ORGANISATION")) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.password"));
            }

            char[] pwd = this.propertyHandler.getProperty("KEYSTORE_PASSWORD_MANDATE_ORGANISATION").toCharArray();
            this.mandateKeyStore = KeyManager.getKeyStore(stream, "PKCS12", pwd);
            Enumeration aliases = this.mandateKeyStore.aliases();

            while(aliases.hasMoreElements()) {
               String alias = (String)aliases.nextElement();
               LOG.debug("Checking key : " + alias);
               if (this.mandateKeyStore.isKeyEntry(alias)) {
                  Key key = this.mandateKeyStore.getKey(alias, pwd);
                  Certificate[] chain = this.mandateKeyStore.getCertificateChain(alias);
                  this.mandateKeyStore.deleteEntry(alias);
                  this.mandateKeyStore.setKeyEntry(alias, key, DEFAULT_PASSWORD, chain);
               }
            }

            this.dumpKeyStore(this.mandateKeyStore);
         } catch (Exception var7) {
            LOG.error("Invalid keystore configuration", var7);
            throw new IllegalArgumentException(I18nHelper.getLabel("error.keystore"), var7);
         }
      }

      return this.mandateKeyStore;
   }

   public void clearKeystore() {
      this.keyStore = null;
   }

   public void clearOldKeystore() {
      this.oldKeyStore = null;
   }

   public void clearMandateKeystore() {
      this.mandateKeyStore = null;
   }

   private void dumpKeyStore(KeyStore keyStore) {
      try {
         LOG.debug("Current Keystore content : ================");
         Enumeration aliases = keyStore.aliases();

         while(aliases.hasMoreElements()) {
            String alias = (String)aliases.nextElement();
            StringBuffer sb = new StringBuffer();
            sb.append("alias=" + alias + " : ");
            sb.append("isCertificateEntry=" + keyStore.isCertificateEntry(alias) + ",");
            sb.append("isKey=" + keyStore.isKeyEntry(alias) + ",");
            LOG.debug(sb);
         }

         LOG.debug("========================================");
      } catch (KeyStoreException var5) {
         LOG.error("KeyStoreException", var5);
      }

   }

   public static String getThumbPrint(X509Certificate cert) throws NoSuchAlgorithmException, CertificateEncodingException {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] der = cert.getEncoded();
      md.update(der);
      byte[] digest = md.digest();
      return hexify(digest);
   }

   public static String hexify(byte[] bytes) {
      char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      StringBuffer buf = new StringBuffer(bytes.length * 2);

      for(int i = 0; i < bytes.length; ++i) {
         buf.append(hexDigits[(bytes[i] & 240) >> 4]);
         buf.append(hexDigits[bytes[i] & 15]);
      }

      return buf.toString();
   }

   public DataSealer initSealing() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException, IntegrationModuleException {
      Security.addProvider(new BouncyCastleProvider());
      PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(this.getKeyStore(), "authentication", DEFAULT_PASSWORD);
      PrivateKey clientAuthenticationKey = keyAndCerts.getPrivateKey();
      X509Certificate clientAuthCertificate = this.getCertificate();
      LOG.debug("Encryption initialized for SubjectDN: " + clientAuthCertificate.getSubjectDN());
      LOG.debug("Encryption initialized for SerialNumber: " + clientAuthCertificate.getSerialNumber());
      LOG.debug("Encryption initialized for ThumbPrint: " + getThumbPrint(clientAuthCertificate));
      SigningCredential signingCredential = SigningCredential.create(clientAuthenticationKey, new X509Certificate[]{clientAuthCertificate});
      DataSealer dataSealer = DataSealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(SigningPolicy.EHEALTH_CERT, signingCredential).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT).addSecretKeyPolicy(EncryptionPolicy.UNKNOWN_RECIPIENT).build();
      return dataSealer;
   }

   public DataSealer initOldSealing() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IntegrationModuleException {
      Security.addProvider(new BouncyCastleProvider());
      PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(this.getOldKeyStore(), "authentication", DEFAULT_PASSWORD);
      PrivateKey clientAuthenticationKey = keyAndCerts.getPrivateKey();
      X509Certificate clientAuthCertificate = this.getOldCertificate();
      LOG.debug("Encryption initialized for :" + clientAuthCertificate.getSubjectDN());
      SigningCredential signingCredential = SigningCredential.create(clientAuthenticationKey, new X509Certificate[]{clientAuthCertificate});
      DataSealer dataSealer = DataSealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(SigningPolicy.EHEALTH_CERT, signingCredential).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT).addSecretKeyPolicy(EncryptionPolicy.UNKNOWN_RECIPIENT).build();
      return dataSealer;
   }

   public DataUnsealer initUnsealing() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, IntegrationModuleException {
      Security.addProvider(new BouncyCastleProvider());
      KeyStore caCertificatesKeystore = KeyManager.getKeyStore(this.getCaCertificateKeystoreIs(), this.propertyHandler.getProperty("LOCAL_CA_CERTIFICATES_STORE_TYPE"), this.propertyHandler.getProperty("CAKEYSTORE_PASSWORD").toCharArray());
      Map<String, PrivateKey> clientDecryptionKeys = KeyManager.getDecryptionKeys(this.getKeyStore(), DEFAULT_PASSWORD);
      Iterator var4 = clientDecryptionKeys.keySet().iterator();

      while(var4.hasNext()) {
         String key = (String)var4.next();
         LOG.debug("Key Available for decryption : " + key);
      }

      DataUnsealer dataUnsealer = DataUnsealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(caCertificatesKeystore, new SigningPolicy[]{SigningPolicy.EHEALTH_CERT, SigningPolicy.EID}).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT, EncryptionCredentials.from(clientDecryptionKeys)).build();
      return dataUnsealer;
   }

   public DataUnsealer initOldUnSealing() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, IntegrationModuleException {
      Security.addProvider(new BouncyCastleProvider());
      KeyStore caCertificatesKeystore = KeyManager.getKeyStore(this.getCaCertificateKeystoreIs(), this.propertyHandler.getProperty("LOCAL_CA_CERTIFICATES_STORE_TYPE"), this.propertyHandler.getProperty("CAKEYSTORE_PASSWORD").toCharArray());
      Map<String, PrivateKey> clientDecryptionKeys = KeyManager.getDecryptionKeys(this.getOldKeyStore(), DEFAULT_PASSWORD);
      Iterator var4 = clientDecryptionKeys.keySet().iterator();

      while(var4.hasNext()) {
         String key = (String)var4.next();
         LOG.debug("Key Available for decryption : " + key);
      }

      DataUnsealer dataUnsealer = DataUnsealerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.NONE).addSigningPolicy(caCertificatesKeystore, new SigningPolicy[]{SigningPolicy.EHEALTH_CERT, SigningPolicy.EID}).addPublicKeyPolicy(EncryptionPolicy.KNOWN_RECIPIENT, EncryptionCredentials.from(clientDecryptionKeys)).build();
      return dataUnsealer;
   }

   public void unlockPersonalKey(String niss, String pwd) throws IntegrationModuleException {
      String file = null;
      if (StringUtils.isEmpty(pwd)) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.personal.keystore.password.null"));
      } else {
         if (this.propertyHandler.hasProperty("PERSONAL_KEYSTORE")) {
            file = this.propertyHandler.getProperty("PERSONAL_KEYSTORE");
         } else {
            if (!this.propertyHandler.hasProperty("KEYSTORE_DIR")) {
               LOG.error("Undefined property KEYSTORE_DIR");
               throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.personal.keystore"));
            }

            List<String> files = IOUtils.getP12FileList(this.propertyHandler.getProperty("KEYSTORE_DIR"), niss);
            if (files.size() == 0) {
               LOG.error("No P12 file found marching pattern '*" + niss + "*.p12' in folder " + this.propertyHandler.getProperty("KEYSTORE_DIR"));
               throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.personal.keystore.pattern", new Object[]{niss}));
            }

            if (files.size() > 1) {
               LOG.error("Too many files matching pattern '*" + niss + "*.p12', please clean folder : " + this.propertyHandler.getProperty("KEYSTORE_DIR"));
               throw new IntegrationModuleException(I18nHelper.getLabel("error.property.found.to.much.keystores"));
            }

            file = (String)files.get(0);
         }

         try {
            InputStream stream = IOUtils.getResourceAsStream(file);
            KeyStore personalKeyStore = KeyManager.getKeyStore(stream, "PKCS12", pwd.toCharArray());
            X509Certificate certificate = (X509Certificate)personalKeyStore.getCertificate("authentication");
            if (certificate != null) {
               try {
                  certificate.checkValidity();
               } catch (CertificateExpiredException var12) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.expired.personal.certificate"), var12);
               } catch (CertificateNotYetValidException var13) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.personal.certificate"), var13);
               }

               Enumeration aliases = personalKeyStore.aliases();

               while(aliases.hasMoreElements()) {
                  String alias = (String)aliases.nextElement();
                  LOG.debug("Importing : " + alias);
                  Certificate[] chain = personalKeyStore.getCertificateChain(alias);
                  Key key = personalKeyStore.getKey(alias, pwd.toCharArray());
                  if (key != null) {
                     String tAlias = "authentication".equals(alias) ? "authentication_fallback" : alias;
                     this.getKeyStore().setKeyEntry(tAlias, key, DEFAULT_PASSWORD, chain);
                  }
               }

            } else {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.notfound.personal.certificate"));
            }
         } catch (IOException var14) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.password"), var14);
         } catch (KeyStoreException var15) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), var15);
         } catch (NoSuchAlgorithmException var16) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), var16);
         } catch (CertificateException var17) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), var17);
         } catch (UnrecoverableKeyException var18) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.fallback "), var18);
         }
      }
   }

   public byte[] unsealingData(CryptoResult<UnsealedData> rslt) throws IOException {
      byte[] unsealedData = null;
      if (rslt.hasData()) {
         InputStream unsealedDataStream;
         if (!rslt.hasErrors()) {
            LOG.debug("from author: " + ((UnsealedData)rslt.getData()).getAuthenticationCert().getSubjectDN());
            unsealedDataStream = ((UnsealedData)rslt.getData()).getContent();
            unsealedData = IOUtils.getBytes(unsealedDataStream);
            LOG.debug("unsealed data: " + new String(unsealedData));
         } else {
            Iterator var4 = rslt.getErrors().iterator();

            while(var4.hasNext()) {
               NotificationError error = (NotificationError)var4.next();
               LOG.error("error: " + error);
            }

            var4 = rslt.getWarnings().iterator();

            while(var4.hasNext()) {
               NotificationWarning warning = (NotificationWarning)var4.next();
               LOG.warn("failure: " + warning);
            }

            if (rslt.getFatal() != null) {
               LOG.fatal("Fatal: " + rslt.getFatal().getErrorMessage());
            }

            unsealedDataStream = ((UnsealedData)rslt.getData()).getContent();
            unsealedData = IOUtils.getBytes(unsealedDataStream);
            LOG.debug("unsealed data: " + new String(unsealedData));
            if (!rslt.getErrors().contains(NotificationError.INNER_CERTIFICATE_EXPECTED_BUT_NOT_PRESENT)) {
               LOG.debug("author certificate: " + ((UnsealedData)rslt.getData()).getAuthenticationCert());
            }
         }
      } else {
         LOG.debug("the msg could not be unsealed, because:" + rslt.getFatal());
      }

      return unsealedData;
   }

   private InputStream getCaCertificateKeystoreIs() throws IntegrationModuleException {
      try {
         return IOUtils.getResourceAsStream(this.getCaCertificateKeystoreLocation());
      } catch (IOException var2) {
         throw new IntegrationModuleException("error.loading.cakeystore", var2);
      }
   }

   private String getCaCertificateKeystoreLocation() {
      if (this.caCertificateKeystoreLocation == null) {
         this.caCertificateKeystoreLocation = this.propertyHandler.getProperty("KEYSTORE_DIR") + this.propertyHandler.getProperty("CAKEYSTORE_LOCATION");
      }

      return this.caCertificateKeystoreLocation;
   }

   private String getCaCertificateKeystorePwd() throws IntegrationModuleException {
      if (this.propertyHandler.hasProperty("CAKEYSTORE_PASSWORD")) {
         return this.propertyHandler.getProperty("CAKEYSTORE_PASSWORD");
      } else {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.password"));
      }
   }

   public KeyStore loadCaKeystore() throws IntegrationModuleException {
      return this.loadKeyStore(this.getCaCertificateKeystoreIs(), this.getCaCertificateKeystorePwd().toCharArray());
   }

   public KeyStore loadKeyStore(InputStream stream, char[] pwd) throws IntegrationModuleException {
      KeyStore keyStore = null;

      try {
         keyStore = KeyManager.getKeyStore(stream, "JKS", pwd);
      } catch (IOException var5) {
         if (var5.getCause() instanceof BadPaddingException) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.keystore.password"), var5);
         }

         throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"), var5);
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6);
      }

      return keyStore;
   }

   public X509Certificate getCertificate() throws IntegrationModuleException {
      try {
         return (X509Certificate)this.getKeyStore().getCertificate("authentication");
      } catch (KeyStoreException var2) {
         LOG.error("KeyStoreException", var2);
         return null;
      }
   }

   public X509Certificate getOldCertificate() throws IntegrationModuleException {
      try {
         return (X509Certificate)this.getOldKeyStore().getCertificate("authentication");
      } catch (KeyStoreException var2) {
         LOG.error("KeyStoreException", var2);
         return null;
      }
   }

   private PrivateKey getPrivateKey(KeyStore key, String privateKeyAlias, char[] privateKeyPassword) {
      try {
         PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(key, privateKeyAlias, privateKeyPassword);
         return keyAndCerts.getPrivateKey();
      } catch (UnrecoverableKeyException var5) {
         LOG.error("UnrecoverableKeyException", var5);
         return null;
      }
   }

   private PublicKey getPublicKey(KeyStore key, String privateKeyAlias, char[] privateKeyPassword) {
      try {
         PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(key, privateKeyAlias, privateKeyPassword);
         return keyAndCerts.getCertificate().getPublicKey();
      } catch (UnrecoverableKeyException var5) {
         LOG.error("UnrecoverableKeyException", var5);
         return null;
      }
   }

   public PrivateKey getPrivateKey() throws IntegrationModuleException {
      return this.getPrivateKey(this.getKeyStore(), "authentication", DEFAULT_PASSWORD);
   }

   public PrivateKey getOldPrivateKey() throws IntegrationModuleException {
      return this.getPrivateKey(this.getOldKeyStore(), "authentication", DEFAULT_PASSWORD);
   }

   public PublicKey getPublicKey() throws IntegrationModuleException {
      return this.getPublicKey(this.getKeyStore(), "authentication", DEFAULT_PASSWORD);
   }

   public PublicKey getOldPublicKey() throws IntegrationModuleException {
      return this.getPublicKey(this.getOldKeyStore(), "authentication", DEFAULT_PASSWORD);
   }

   public PrivateKey getMandatePrivateKey() {
      return this.getPrivateKey(this.getMandateOrganisationKeyStore(), "authentication", DEFAULT_PASSWORD);
   }

   public PublicKey getMandatePublicKey() {
      return this.getPublicKey(this.getMandateOrganisationKeyStore(), "authentication", DEFAULT_PASSWORD);
   }

   public PrivateKey getFallbackPrivateKey() throws IntegrationModuleException {
      return this.getPrivateKey(this.getKeyStore(), "authentication_fallback", DEFAULT_PASSWORD);
   }

   public PublicKey getFallbackPublicKey() throws IntegrationModuleException {
      return this.getPublicKey(this.getKeyStore(), "authentication_fallback", DEFAULT_PASSWORD);
   }

   public X509Certificate getFallbackCertificate() throws IntegrationModuleException {
      try {
         return (X509Certificate)this.getKeyStore().getCertificate("authentication_fallback");
      } catch (KeyStoreException var2) {
         LOG.error("KeyStoreException", var2);
         return null;
      }
   }

   public X509Certificate getMandateCertificate() {
      try {
         return (X509Certificate)this.getMandateOrganisationKeyStore().getCertificate("authentication");
      } catch (KeyStoreException var2) {
         LOG.error("KeyStoreException", var2);
         return null;
      }
   }

   public void verifyDecryption(EncryptionToken myETK) throws IntegrationModuleException {
      boolean found = false;

      try {
         Enumeration aliases = this.getKeyStore().aliases();

         while(aliases.hasMoreElements()) {
            try {
               String alias = (String)aliases.nextElement();
               LOG.debug("verifyDecryption : " + alias);
               PrivateKeyEntry keyAndCerts = KeyManager.getKeyAndCertificates(this.getKeyStore(), alias, DEFAULT_PASSWORD);
               myETK.getCertificate().verify(keyAndCerts.getCertificate().getPublicKey());
               found = true;
            } catch (UnrecoverableKeyException var6) {
            } catch (NoSuchAlgorithmException var7) {
            } catch (InvalidKeyException var8) {
            } catch (CertificateException var9) {
            } catch (NoSuchProviderException var10) {
            } catch (SignatureException var11) {
            }
         }
      } catch (KeyStoreException var12) {
      }

      if (!found) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.decryption.key"));
      }
   }

   public void setSystemKeystorePassword(String systemKeystorePassword) {
      if (StringUtils.isNotBlank(systemKeystorePassword)) {
         this.propertyHandler.getProperties().put("KEYSTORE_PASSWORD", systemKeystorePassword);
      }

   }

   public String getSystemKeystorePassword() throws IntegrationModuleException {
      if (this.propertyHandler.hasProperty("KEYSTORE_PASSWORD")) {
         return this.propertyHandler.getProperty("KEYSTORE_PASSWORD");
      } else {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.property.not.found.keystore.password"));
      }
   }

   public void setSystemKeystorePath(String systemKeystorePath) {
      if (StringUtils.isNotBlank(systemKeystorePath)) {
         this.systemKeystorePath = systemKeystorePath;
      }

   }

   public void setSystemKeystoreDirectory(String systemKeystoreDirectory) {
      if (StringUtils.isNotBlank(systemKeystoreDirectory)) {
         this.propertyHandler.getProperties().put("KEYSTORE_DIR", systemKeystoreDirectory);
      }

   }

   public void setSystemKeystoreRiziv(String systemKeystoreRiziv) {
      if (StringUtils.isNotBlank(systemKeystoreRiziv)) {
         this.propertyHandler.getProperties().put("PROP_KEYSTORE_RIZIV_KBO", systemKeystoreRiziv);
      }

   }

   public void setOldSystemKeystorePath(String oldSystemKeystorePath) {
      if (StringUtils.isNotBlank(oldSystemKeystorePath)) {
         this.oldSystemKeystorePath = oldSystemKeystorePath;
      }

   }

   public void setOldSystemKeystorePassword(String oldSystemKeystorePassword) {
      if (StringUtils.isNotBlank(oldSystemKeystorePassword)) {
         this.propertyHandler.setProperty("OLD_KEYSTORE_PASSWORD", oldSystemKeystorePassword);
      }

   }

   public String getOldSystemKeystorePassword() {
      return this.propertyHandler.getProperty("OLD_KEYSTORE_PASSWORD");
   }

   public void setOldSystemKeystoreDirectory(String oldSystemKeystoreDirectory) {
      if (StringUtils.isNotBlank(oldSystemKeystoreDirectory)) {
         this.propertyHandler.getProperties().put("KEYSTORE_AUTH_OLD_P12_FOLDER", oldSystemKeystoreDirectory);
      }

   }

   public String getOldSystemKeystoreDirectory() {
      return this.propertyHandler.getProperty("KEYSTORE_AUTH_OLD_P12_FOLDER");
   }

   public void setOldSystemKeystoreRiziv(String oldSystemKeystoreRiziv) {
      if (StringUtils.isNotBlank(oldSystemKeystoreRiziv)) {
         this.propertyHandler.getProperties().put("PROP_OLD_KEYSTORE_RIZIV_KBO", oldSystemKeystoreRiziv);
      }

   }

   public String getOldSystemKeystoreRiziv() {
      return this.propertyHandler.getProperty("PROP_OLD_KEYSTORE_RIZIV_KBO");
   }

   public byte[] queueEncrypt(byte[] data, PublicKey publicKey) throws IntegrationModuleException {
      String s = new String(data);
      s = s + "ENCRYPTI0NS1GNATURE";
      data = s.getBytes();

      try {
         String key = publicKey.toString().substring(0, 16);
         SecretKeySpec symKey = new SecretKeySpec(key.getBytes(), "AES");
         Cipher cipher = Cipher.getInstance("AES");
         int rest = false;
         int length = false;
         byte[] newbuf = null;
         byte[] newbuf;
         if (cipher.getBlockSize() > 0) {
            int rest = data.length % cipher.getBlockSize();
            int length = data.length + (cipher.getBlockSize() - rest);
            newbuf = new byte[length];
            System.arraycopy(data, 0, newbuf, 0, data.length);
         } else {
            newbuf = new byte[data.length];
            System.arraycopy(data, 0, newbuf, 0, data.length);
         }

         cipher.init(1, symKey);
         byte[] cipherData = cipher.doFinal(newbuf);
         return Base64.encode(cipherData);
      } catch (Throwable var11) {
         Exceptionutils.errorHandler(var11);
         return null;
      }
   }

   public String queueDecrypt(byte[] data, PublicKey publicKey) throws IntegrationModuleException {
      try {
         LOG.debug("**************** SIZE BYTES ********** " + data.length);
         byte[] decodeData = Base64.decode(data);
         String key = publicKey.toString().substring(0, 16);
         SecretKeySpec symKey = new SecretKeySpec(key.getBytes(), "AES");
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(2, symKey);
         byte[] cipherData = cipher.doFinal(decodeData);
         LOG.debug("************** cipherData ************* " + Arrays.toString(cipherData));
         String s = new String(cipherData);
         LOG.debug("******************** GOING OUT: " + s + " **********************");
         int i = s.lastIndexOf("ENCRYPTI0NS1GNATURE");
         s = s.substring(0, i);
         LOG.debug("******************** GOING OUT SUBSTRING2: " + s + " **********************");
         return s;
      } catch (Throwable var10) {
         Exceptionutils.errorHandler(var10);
         return null;
      }
   }
}
