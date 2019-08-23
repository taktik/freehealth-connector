package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.exception.CredentialException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyStoreCredential extends AbstractExtendedCredential {
   private static final Logger LOG = LoggerFactory.getLogger(KeyStoreCredential.class);
   private KeyStore keystore;
   private String alias;
   private char[] pwd;

   public KeyStoreCredential(KeyStore keystore, String alias, String password) throws TechnicalConnectorException {
      this.pwd = password == null ? ArrayUtils.EMPTY_CHAR_ARRAY : password.toCharArray();
      this.alias = alias;
      this.keystore = keystore;
   }

   public String getIssuer() {
      return this.getCertificate().getSubjectX500Principal().getName("RFC1779");
   }

   public String getIssuerQualifier() {
      return this.getCertificate().getIssuerX500Principal().getName("RFC1779");
   }

   public PublicKey getPublicKey() {
      return this.getCertificate().getPublicKey();
   }

   public PrivateKey getPrivateKey() {
      try {
         return (PrivateKey)this.keystore.getKey(this.alias, this.pwd);
      } catch (UnrecoverableKeyException var2) {
         LOG.error(var2.getMessage(), var2.getCause());
         return null;
      } catch (KeyStoreException var3) {
         LOG.error(var3.getMessage(), var3.getCause());
         return null;
      } catch (NoSuchAlgorithmException var4) {
         LOG.error(var4.getMessage(), var4.getCause());
         return null;
      }
   }

   public X509Certificate getCertificate() {
      try {
         return (X509Certificate)this.keystore.getCertificate(this.alias);
      } catch (KeyStoreException var2) {
         LOG.error(var2.getMessage(), var2.getCause());
         return null;
      }
   }

   public String getProviderName() {
      return this.keystore.getProvider().getName();
   }

   public Certificate[] getCertificateChain() {
      try {
         return this.keystore.getCertificateChain(this.alias);
      } catch (KeyStoreException var2) {
         LOG.error(var2.getMessage());
         throw new CredentialException(var2);
      }
   }

   public KeyStore getKeyStore() throws TechnicalConnectorException {
      return this.keystore;
   }

   public char[] getPwd() {
      return pwd;
   }
}
