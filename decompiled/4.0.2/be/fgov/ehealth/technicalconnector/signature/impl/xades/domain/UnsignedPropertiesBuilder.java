package be.fgov.ehealth.technicalconnector.signature.impl.xades.domain;

import be.ehealth.technicalconnector.utils.MarshallerHelper;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.etsi.uri._01903.v1_3.CRLRefsType;
import org.etsi.uri._01903.v1_3.CRLValuesType;
import org.etsi.uri._01903.v1_3.CompleteCertificateRefsType;
import org.etsi.uri._01903.v1_3.CompleteRevocationRefsType;
import org.etsi.uri._01903.v1_3.EncapsulatedPKIData;
import org.etsi.uri._01903.v1_3.OCSPRefsType;
import org.etsi.uri._01903.v1_3.OCSPValuesType;
import org.etsi.uri._01903.v1_3.RevocationValuesType;
import org.etsi.uri._01903.v1_3.SigningCertificate;
import org.etsi.uri._01903.v1_3.UnsignedProperties;
import org.etsi.uri._01903.v1_3.UnsignedSignatureProperties;
import org.etsi.uri._01903.v1_3.XAdESTimeStampType;
import org.w3._2000._09.xmldsig.CanonicalizationMethod;
import org.w3c.dom.Document;

public class UnsignedPropertiesBuilder {
   private static MarshallerHelper<UnsignedProperties, UnsignedProperties> marshaller = new MarshallerHelper(UnsignedProperties.class, UnsignedProperties.class);
   private String id;
   private List<XAdESTimeStampType> signatureTimestamps = new ArrayList();
   private List<CertRef> completeCertRefs = new ArrayList();
   private List<CrlRef> crlRefs = new ArrayList();
   private List<OcspRef> ocspRefs = new ArrayList();

   public UnsignedPropertiesBuilder() {
   }

   public String getId() {
      return "xmldsig-" + this.id + "-xades-unsignedprops";
   }

   private String getTimestampId() {
      return "time-stamp-" + this.id;
   }

   private String getEncapsulatedTimeStampID() {
      return "time-stamp-token-" + this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void addSignatureTimestamp(byte[] tsToken, String c14nMethod) {
      XAdESTimeStampType timestamp = new XAdESTimeStampType();
      CanonicalizationMethod method = new CanonicalizationMethod();
      method.setAlgorithm(c14nMethod);
      timestamp.setCanonicalizationMethod(method);
      EncapsulatedPKIData encapsulatedTS = new EncapsulatedPKIData();
      encapsulatedTS.setValue(tsToken);
      encapsulatedTS.setId(this.getEncapsulatedTimeStampID());
      timestamp.getEncapsulatedTimeStampsAndXMLTimeStamps().add(encapsulatedTS);
      timestamp.setId(this.getTimestampId());
      this.signatureTimestamps.add(timestamp);
   }

   public void addCertificate(X509Certificate cert) {
      this.completeCertRefs.add(new CertRef(cert));
   }

   public void addOCSPRef(byte[] oscpEncoded) {
      this.ocspRefs.add(new OcspRef(oscpEncoded));
   }

   public void addCrlRef(X509CRL crl) {
      this.crlRefs.add(new CrlRef(crl));
   }

   public UnsignedProperties build() {
      if (this.completeCertRefs.isEmpty() && this.signatureTimestamps.isEmpty() && this.crlRefs.isEmpty() && this.ocspRefs.isEmpty()) {
         return null;
      } else {
         UnsignedSignatureProperties unsignedSignatureProperties = new UnsignedSignatureProperties();
         unsignedSignatureProperties.getSignatureTimeStamps().addAll(this.signatureTimestamps);
         unsignedSignatureProperties.setCompleteCertificateRefs(this.generateCompleteCertRefs());
         unsignedSignatureProperties.setRevocationValues(this.generateRevocationValues());
         unsignedSignatureProperties.setCompleteRevocationRefs(this.generateCompleteRevocationRefs());
         UnsignedProperties unsignedProperties = new UnsignedProperties();
         unsignedProperties.setId(this.getId());
         unsignedProperties.setUnsignedSignatureProperties(unsignedSignatureProperties);
         return unsignedProperties;
      }
   }

   private CompleteCertificateRefsType generateCompleteCertRefs() {
      if (this.completeCertRefs.isEmpty()) {
         return null;
      } else {
         SigningCertificate completeSigningCertRefs = new SigningCertificate();
         Iterator var2 = this.completeCertRefs.iterator();

         while(var2.hasNext()) {
            CertRef ref = (CertRef)var2.next();
            completeSigningCertRefs.getCerts().add(ref.convertToCertID());
         }

         CompleteCertificateRefsType result = new CompleteCertificateRefsType();
         result.setCertRefs(completeSigningCertRefs);
         return result;
      }
   }

   private CompleteRevocationRefsType generateCompleteRevocationRefs() {
      if (this.ocspRefs.isEmpty() && this.crlRefs.isEmpty()) {
         return null;
      } else {
         CompleteRevocationRefsType result = new CompleteRevocationRefsType();
         Iterator var3;
         if (!this.ocspRefs.isEmpty()) {
            OCSPRefsType ocspRefsType = new OCSPRefsType();
            var3 = this.ocspRefs.iterator();

            while(var3.hasNext()) {
               OcspRef ref = (OcspRef)var3.next();
               ocspRefsType.getOCSPReves().add(ref.convertToXadesOCSPRef());
            }

            result.setOCSPRefs(ocspRefsType);
         }

         if (!this.crlRefs.isEmpty()) {
            CRLRefsType crlRefType = new CRLRefsType();
            var3 = this.crlRefs.iterator();

            while(var3.hasNext()) {
               CrlRef ref = (CrlRef)var3.next();
               crlRefType.getCRLReves().add(ref.convertToXadesCRLRef());
            }

            result.setCRLRefs(crlRefType);
         }

         return result;
      }
   }

   private RevocationValuesType generateRevocationValues() {
      if (this.ocspRefs.isEmpty() && this.crlRefs.isEmpty()) {
         return null;
      } else {
         RevocationValuesType result = new RevocationValuesType();
         Iterator var3;
         if (!this.ocspRefs.isEmpty()) {
            OCSPValuesType ocspValueType = new OCSPValuesType();
            var3 = this.ocspRefs.iterator();

            while(var3.hasNext()) {
               OcspRef ref = (OcspRef)var3.next();
               ocspValueType.getEncapsulatedOCSPValues().add(ref.convertToXadesEncapsulatedPKIData());
            }

            result.setOCSPValues(ocspValueType);
         }

         if (!this.crlRefs.isEmpty()) {
            CRLValuesType crlValueType = new CRLValuesType();
            var3 = this.crlRefs.iterator();

            while(var3.hasNext()) {
               CrlRef ref = (CrlRef)var3.next();
               crlValueType.getEncapsulatedCRLValues().add(ref.convertToXadesEncapsulatedPKIData());
            }

            result.setCRLValues(crlValueType);
         }

         return result;
      }
   }

   public Document buildAsDocument() {
      UnsignedProperties unsignedProperties = this.build();
      return unsignedProperties == null ? null : marshaller.toDocument(unsignedProperties);
   }
}
