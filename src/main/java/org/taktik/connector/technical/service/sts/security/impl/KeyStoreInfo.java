package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public class KeyStoreInfo {
   private static final String JKS = "JKS";
   private static final String PKCS12 = "pkcs12";
   private String keystorePath;
   private String keystoreType;
   private char[] keystorePassword;
   private String alias;
   private char[] privateKeyPassword;
   private static ConfigValidator config = ConfigFactory.getConfigValidator();

   public KeyStoreInfo(String alias, char[] privateKeyPwd) {
      this(null, null, null, alias, privateKeyPwd);
   }

   public KeyStoreInfo(String pathKeystore, char[] pwdKeystore, String alias, char[] privateKeyPwd) {
      this(pathKeystore, "pkcs12", pwdKeystore, alias, privateKeyPwd);
      if (pathKeystore.toLowerCase().contains(".jks")) {
         this.keystoreType = "JKS";
      }

   }

   public KeyStoreInfo(String pathKeystore, String keystoreType, char[] pwdKeystore, String alias, char[] privateKeyPwd) {
      Validate.notEmpty(alias);
      Validate.notNull(privateKeyPwd);
      if (StringUtils.isNotEmpty(pathKeystore)) {
         Validate.notEmpty(keystoreType);
         Validate.notNull(pwdKeystore);
      }

      this.keystorePath = pathKeystore;
      this.keystoreType = keystoreType;
      this.keystorePassword = ArrayUtils.clone(pwdKeystore);
      this.alias = alias;
      this.privateKeyPassword = ArrayUtils.clone(privateKeyPwd);
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
