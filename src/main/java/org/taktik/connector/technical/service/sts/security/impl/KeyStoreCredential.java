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
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyStoreCredential extends AbstractExtendedCredential {
   private static final Logger LOG = LoggerFactory.getLogger(KeyStoreCredential.class);
   private KeyStore keyStore;
   private String alias;
   private UUID keystoreId;
   private char[] password;

   public KeyStoreCredential(UUID keystoreId, KeyStore keyStore, String alias, String password, String quality) throws TechnicalConnectorException {
      super(quality);

      this.keystoreId = keystoreId;
      this.password = password == null ? ArrayUtils.EMPTY_CHAR_ARRAY : password.toCharArray();
      this.alias = alias;
      this.keyStore = keyStore;
   }

   public UUID getKeystoreId() {
      return keystoreId;
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
         return (PrivateKey)this.keyStore.getKey(this.alias, this.password);
      } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException ex) {
         LOG.error(ex.getMessage(), ex.getCause());
         return null;
      }
   }

   public X509Certificate getCertificate() {
      try {
         return (X509Certificate)this.keyStore.getCertificate(this.alias);
      } catch (KeyStoreException ex) {
         LOG.error(ex.getMessage(), ex.getCause());
         return null;
      }
   }

   public String getProviderName() {
      return this.keyStore.getProvider().getName();
   }

   public Certificate[] getCertificateChain() {
      try {
         return this.keyStore.getCertificateChain(this.alias);
      } catch (KeyStoreException var2) {
         LOG.error(var2.getMessage());
         throw new CredentialException(var2);
      }
   }

   public KeyStore getKeyStore() throws TechnicalConnectorException {
      return this.keyStore;
   }

   public char[] getPassword() {
      return password;
   }

}
