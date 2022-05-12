package be.ehealth.technicalconnector.service.sts.security;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class KeyStoreInfo {
   private static final String PROP_KEYSTORE_DIR = "KEYSTORE_DIR";
   private static final String JKS = "JKS";
   private static final String PKCS12 = "pkcs12";
   private String keystorePath;
   private String keystoreType;
   private char[] keystorePassword;
   private String alias;
   private char[] privateKeyPassword;
   private ConfigValidator config;

   public KeyStoreInfo(String alias, char[] privateKeyPwd) throws TechnicalConnectorException {
      this((String)null, (String)null, (char[])null, alias, privateKeyPwd);
   }

   public KeyStoreInfo(String pathKeystore, char[] pwdKeystore, String alias, char[] privateKeyPwd) throws TechnicalConnectorException {
      this(pathKeystore, "pkcs12", pwdKeystore, alias, privateKeyPwd);
      if (pathKeystore.toLowerCase().contains(".jks")) {
         this.keystoreType = "JKS";
      }

   }

   public KeyStoreInfo(String pathKeystore, String keystoreType, char[] pwdKeystore, String alias, char[] privateKeyPwd) throws TechnicalConnectorException {
      this.config = ConfigFactory.getConfigValidator();
      Validate.notEmpty(alias);
      Validate.notNull(privateKeyPwd);
      if (StringUtils.isNotEmpty(pathKeystore)) {
         Validate.notEmpty(keystoreType);
         Validate.notNull(pwdKeystore);
      }

      this.keystorePath = this.getKeystoreLoc(pathKeystore);
      this.keystoreType = keystoreType;
      this.keystorePassword = ArrayUtils.clone(pwdKeystore);
      this.alias = alias;
      this.privateKeyPassword = ArrayUtils.clone(privateKeyPwd);
   }

   private String getKeystoreLoc(String pathKeystore) {
      String propLocation = this.config.getProperty("KEYSTORE_DIR", "");
      return StringUtils.isEmpty(propLocation) ? pathKeystore : this.config.getProperty("KEYSTORE_DIR") + pathKeystore;
   }

   public final String getKeystorePath() {
      return this.keystorePath;
   }

   public final String getKeystoreType() {
      return this.keystoreType;
   }

   public final char[] getKeystorePassword() {
      return ArrayUtils.clone(this.keystorePassword);
   }

   public final String getAlias() {
      return this.alias;
   }

   public final char[] getPrivateKeyPassword() {
      return ArrayUtils.clone(this.privateKeyPassword);
   }
}
