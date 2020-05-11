package org.taktik.connector.technical.ws.feature;

public class SHA1Feature extends AbstractSigningFeature {
   String getSignatureMethodAlgorithm() {
      return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
   }

   String getDigestMethodAlgorithm() {
      return "http://www.w3.org/2000/09/xmldsig#sha1";
   }
}
