package org.taktik.connector.technical.service.etee.domain;

import be.fgov.ehealth.etee.crypto.encrypt.EncryptionTokenFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

public class EncryptionToken implements Serializable {
   private static final long serialVersionUID = 1L;
   private be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken etk;

   public EncryptionToken(be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken etk) {
      this.etk = etk;
   }

   public EncryptionToken(byte[] etkBytes) throws GeneralSecurityException {
      this.etk = EncryptionTokenFactory.getInstance().create(etkBytes);
   }

   public EncryptionToken(File file) throws GeneralSecurityException, IOException {
      this.etk = EncryptionTokenFactory.getInstance().create(file);
   }

   public EncryptionToken(InputStream is) throws GeneralSecurityException, IOException {
      this.etk = EncryptionTokenFactory.getInstance().create(is);
   }

   public EncryptionToken(String encryptionTokenBase64) throws GeneralSecurityException {
      this.etk = EncryptionTokenFactory.getInstance().create(encryptionTokenBase64);
   }

   public final byte[] getEncoded() {
      return this.etk.getEncoded();
   }

   public final byte[] getBase64Encoded() {
      return this.etk.getBase64Encoded();
   }

   public final X509Certificate getCertificate() {
      return this.etk.getCertificate();
   }

   public final X509Certificate getAuthenticationCertificate() {
      return this.etk.getAuthenticationCertificate();
   }

   public be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken getEtk() {
      return this.etk;
   }
}
