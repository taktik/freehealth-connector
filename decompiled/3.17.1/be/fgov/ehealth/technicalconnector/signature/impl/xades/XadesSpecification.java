package be.fgov.ehealth.technicalconnector.signature.impl.xades;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.SignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import java.util.Map;
import org.apache.xml.security.signature.XMLSignature;
import org.w3c.dom.Element;

public interface XadesSpecification {
   String XMLNS_XADES_1_3_2 = "http://uri.etsi.org/01903/v1.3.2#";

   void addOptionalBeforeSignatureParts(SignedPropertiesBuilder var1, XMLSignature var2, Credential var3, String var4, Map<String, Object> var5) throws TechnicalConnectorException;

   void addOptionalAfterSignatureParts(UnsignedPropertiesBuilder var1, XMLSignature var2, String var3, Map<String, Object> var4) throws TechnicalConnectorException;

   void verify(SignatureVerificationResult var1, Element var2);
}
