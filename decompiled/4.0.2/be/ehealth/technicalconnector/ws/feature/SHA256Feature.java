package be.ehealth.technicalconnector.ws.feature;

public class SHA256Feature extends AbstractSigningFeature {
   public SHA256Feature() {
   }

   String getSignatureMethodAlgorithm() {
      return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";
   }

   String getDigestMethodAlgorithm() {
      return "http://www.w3.org/2001/04/xmlenc#sha256";
   }
}
