package be.fgov.ehealth.technicalconnector.signature.impl.xades.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.SignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import java.util.Map;
import org.apache.xml.security.signature.XMLSignature;
import org.joda.time.DateTime;
import org.w3c.dom.Element;

public class XadesBesSpecification implements be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesSpecification {
   public void addOptionalBeforeSignatureParts(SignedPropertiesBuilder signedProps, XMLSignature sig, Credential signing, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
   }

   public void addOptionalAfterSignatureParts(UnsignedPropertiesBuilder unsignedProps, XMLSignature sig, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
   }

   public void verify(SignatureVerificationResult result, Element sigElement) {
      this.verifySigningTime(result);
   }

   private void verifySigningTime(SignatureVerificationResult result) {
      result.setSigningTime(new DateTime());
      XadesVerificationHelper.verifyValiditySigningCert(result.getSigningTime(), result);
   }
}
