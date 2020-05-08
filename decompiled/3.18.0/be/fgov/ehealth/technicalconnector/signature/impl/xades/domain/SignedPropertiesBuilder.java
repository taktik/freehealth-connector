package be.fgov.ehealth.technicalconnector.signature.impl.xades.domain;

import be.ehealth.technicalconnector.utils.MarshallerHelper;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.etsi.uri._01903.v1_3.SignedProperties;
import org.etsi.uri._01903.v1_3.SignedSignatureProperties;
import org.etsi.uri._01903.v1_3.SigningCertificate;
import org.joda.time.DateTime;
import org.w3c.dom.Document;

public class SignedPropertiesBuilder {
   private static MarshallerHelper<SignedProperties, SignedProperties> marshaller = new MarshallerHelper(SignedProperties.class, SignedProperties.class);
   private DateTime signingTime;
   private String id;
   private List<CertRef> signingCertRefs = new ArrayList();

   public void setSigningTime(DateTime signingTime) {
      this.signingTime = signingTime;
   }

   public String getId() {
      return "xmldsig-" + this.id + "-xades-signedprops";
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setSigningCert(X509Certificate cert) {
      this.signingCertRefs.add(new CertRef(cert));
   }

   public SignedProperties build() {
      SignedProperties signedProperties = new SignedProperties();
      signedProperties.setId(this.getId());
      SignedSignatureProperties signedSignatureProperties = new SignedSignatureProperties();
      SigningCertificate signingCert = new SigningCertificate();
      Iterator i$ = this.signingCertRefs.iterator();

      while(i$.hasNext()) {
         CertRef signingCertRef = (CertRef)i$.next();
         signingCert.getCerts().add(signingCertRef.convertToCertID());
      }

      signedSignatureProperties.setSigningCertificate(signingCert);
      signedSignatureProperties.setSigningTime(this.signingTime);
      signedProperties.setSignedSignatureProperties(signedSignatureProperties);
      return signedProperties;
   }

   public Document buildAsDocument() {
      return marshaller.toDocument(this.build());
   }
}
