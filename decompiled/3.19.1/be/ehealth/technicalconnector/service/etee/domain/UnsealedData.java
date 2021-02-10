package be.ehealth.technicalconnector.service.etee.domain;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;

public class UnsealedData {
   private InputStream content;
   private X509Certificate authenticationCert;
   private X509Certificate signatureCert;
   private byte[] signature;
   private Date signingTime;

   public InputStream getContent() {
      return this.content;
   }

   public byte[] getContentAsByte() throws TechnicalConnectorException {
      try {
         return IOUtils.toByteArray(this.content);
      } catch (IOException var2) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, var2, new Object[0]);
      }
   }

   public void setContent(InputStream content) {
      this.content = content;
   }

   public X509Certificate getAuthenticationCert() {
      return this.authenticationCert;
   }

   public void setAuthenticationCert(X509Certificate authenticationCert) {
      this.authenticationCert = authenticationCert;
   }

   public byte[] getSignature() {
      return ArrayUtils.clone(this.signature);
   }

   public void setSignature(byte[] signature) {
      this.signature = signature != null ? (byte[])signature.clone() : null;
   }

   public Date getSigningTime() {
      return this.signingTime != null ? new Date(this.signingTime.getTime()) : null;
   }

   public void setSigningTime(Date signingTime) {
      if (signingTime != null) {
         this.signingTime = new Date(signingTime.getTime());
      } else {
         this.signingTime = null;
      }

   }

   public X509Certificate getSignatureCert() {
      return this.signatureCert;
   }

   public void setSignatureCert(X509Certificate signatureCert) {
      this.signatureCert = signatureCert;
   }
}
