package be.fgov.ehealth.technicalconnector.signature.impl.xades.domain;

import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import org.apache.xml.security.utils.RFC2253Parser;
import org.etsi.uri._01903.v1_3.CRLIdentifierType;
import org.etsi.uri._01903.v1_3.CRLRefType;
import org.etsi.uri._01903.v1_3.EncapsulatedPKIData;
import org.joda.time.DateTime;

class CrlRef extends Ref {
   private X509CRL crl;

   CrlRef(X509CRL crl) {
      this.crl = crl;
   }

   byte[] getEncoded() throws Ref.EncodingException {
      try {
         return this.crl.getEncoded();
      } catch (CRLException var2) {
         throw new Ref.EncodingException(var2);
      }
   }

   private String getIssuerName() {
      return RFC2253Parser.normalize(this.crl.getIssuerDN().getName());
   }

   private DateTime getIssueTime() {
      return new DateTime(this.crl.getThisUpdate().getTime());
   }

   private BigInteger getIssuerNumber() {
      return BigInteger.valueOf((long)this.crl.getVersion());
   }

   public CRLRefType convertToXadesCRLRef() {
      CRLRefType refType = new CRLRefType();
      refType.setDigestAlgAndValue(this.getDigestAlgAndValue());
      CRLIdentifierType crlIdentifier = new CRLIdentifierType();
      crlIdentifier.setIssuer(this.getIssuerName());
      crlIdentifier.setIssueTime(this.getIssueTime());
      crlIdentifier.setNumber(this.getIssuerNumber());
      refType.setCRLIdentifier(crlIdentifier);
      return refType;
   }

   public EncapsulatedPKIData convertToXadesEncapsulatedPKIData() {
      EncapsulatedPKIData data = new EncapsulatedPKIData();

      try {
         data.setValue(this.crl.getEncoded());
         return data;
      } catch (CRLException var3) {
         throw new IllegalArgumentException(var3);
      }
   }
}
