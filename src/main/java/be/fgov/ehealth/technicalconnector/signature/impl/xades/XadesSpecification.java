package be.fgov.ehealth.technicalconnector.signature.impl.xades;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.SignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import java.util.Map;
import org.apache.xml.security.signature.XMLSignature;
import org.w3c.dom.Element;

public interface XadesSpecification {
   String XMLNS_XADES_1_3_2 = "http://uri.etsi.org/01903/v1.3.2#";

   void addOptionalBeforeSignatureParts(SignedPropertiesBuilder unsignedProps, XMLSignature xmlSignature, Credential credential, String uuid, Map<String, Object> options) throws TechnicalConnectorException;

   void addOptionalAfterSignatureParts(UnsignedPropertiesBuilder unsignedProps, XMLSignature xmlSig, Credential credential, String uuid, Map<String, Object> options) throws TechnicalConnectorException;

   void verify(SignatureVerificationResult var1, Element var2);
}
