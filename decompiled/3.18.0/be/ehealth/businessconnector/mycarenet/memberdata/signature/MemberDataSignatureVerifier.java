package be.ehealth.businessconnector.mycarenet.memberdata.signature;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.DomUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MemberDataSignatureVerifier {
   private SignatureBuilder signatureBuilder;

   public MemberDataSignatureVerifier() throws TechnicalConnectorException {
      this.signatureBuilder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
   }

   public Map<String, SignatureVerificationResult> verifyAll(byte[] signedByteArray, Map<String, Object> options) throws TechnicalConnectorException {
      Map<String, SignatureVerificationResult> signatureVerificationResults = new HashMap();
      Document signedContent = ConnectorXmlUtils.toDocument(signedByteArray);
      NodeList signatureList = DomUtils.getMatchingChilds(signedContent, "http://www.w3.org/2000/09/xmldsig#", "Signature");
      if (signatureList.getLength() > 0) {
         for(int i = 0; i < signatureList.getLength(); ++i) {
            Node parentNode = signatureList.item(i).getParentNode();
            String id = ((Element)parentNode).getAttribute("ID");
            if (StringUtils.isEmpty(id)) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"No ID found for the parent of the Signature element"});
            }

            SignatureVerificationResult signatureVerificationResult = this.signatureBuilder.verify(ConnectorXmlUtils.toByteArray(parentNode), options);
            signatureVerificationResults.put(id, signatureVerificationResult);
         }
      }

      return signatureVerificationResults;
   }
}
