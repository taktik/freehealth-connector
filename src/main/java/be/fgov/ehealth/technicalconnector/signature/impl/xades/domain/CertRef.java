package be.fgov.ehealth.technicalconnector.signature.impl.xades.domain;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.apache.xml.security.utils.RFC2253Parser;
import org.etsi.uri._01903.v1_3.CertIDType;
import org.w3._2000._09.xmldsig.X509IssuerSerialType;

class CertRef extends Ref {
   private X509Certificate cert;

   CertRef(X509Certificate cert) {
      this.cert = cert;
   }

   byte[] getEncoded() throws Ref.EncodingException {
      try {
         return this.cert.getEncoded();
      } catch (CertificateEncodingException var2) {
         throw new Ref.EncodingException(var2);
      }
   }

   public CertIDType convertToCertID() {
      CertIDType certId = new CertIDType();
      certId.setCertDigest(this.getDigestAlgAndValue());
      X509IssuerSerialType x509IssuerSerial = new X509IssuerSerialType();
      x509IssuerSerial.setX509IssuerName(RFC2253Parser.normalize(this.cert.getIssuerX500Principal().getName()));
      x509IssuerSerial.setX509SerialNumber(this.cert.getSerialNumber());
      certId.setIssuerSerial(x509IssuerSerial);
      return certId;
   }
}
