package be.fgov.ehealth.technicalconnector.signature.impl.xades.domain;

import org.taktik.connector.technical.utils.ConnectorCryptoUtils;
import org.apache.xml.security.algorithms.JCEMapper;
import org.etsi.uri._01903.v1_3.DigestAlgAndValueType;
import org.w3._2000._09.xmldsig.DigestMethod;

abstract class Ref {
   private static final String DIGEST_ALGO = "http://www.w3.org/2001/04/xmlenc#sha256";

   public String getDigestAlgUri() {
      return "http://www.w3.org/2001/04/xmlenc#sha256";
   }

   abstract byte[] getEncoded() throws Ref.EncodingException;

   public byte[] getDigestValue() {
      try {
         return ConnectorCryptoUtils.calculateDigest(JCEMapper.translateURItoJCEID("http://www.w3.org/2001/04/xmlenc#sha256"), this.getEncoded());
      } catch (Exception var2) {
         throw new IllegalArgumentException(var2);
      }
   }

   public DigestMethod getDigestMethod() {
      DigestMethod method = new DigestMethod();
      method.setAlgorithm(this.getDigestAlgUri());
      return method;
   }

   public DigestAlgAndValueType getDigestAlgAndValue() {
      DigestAlgAndValueType digestAlgAndValue = new DigestAlgAndValueType();
      digestAlgAndValue.setDigestMethod(this.getDigestMethod());
      digestAlgAndValue.setDigestValue(this.getDigestValue());
      return digestAlgAndValue;
   }

   static class EncodingException extends Exception {
      EncodingException(Exception e) {
         super(e);
      }
   }
}
