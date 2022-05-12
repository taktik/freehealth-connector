package be.ehealth.business.mycarenetcommons.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Element;

public class SignHelper {
   private static final String CIN_NAMESPACE = "urn:be:cin:encrypted";
   private static final String ENCRYPTED_KNOWN_CONTENT = "EncryptedKnownContent";
   private SigningOptions signingOptions;

   public byte[] sign(byte[] contentToSign, String detailId) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap();
      List<String> tranforms = new ArrayList();
      tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
      options.put("transformerList", tranforms);
      options.put("baseURI", detailId);
      return SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).sign(SessionUtil.getEncryptionCredential(), contentToSign, options);
   }

   SignHelper(SigningOptions signingOptions) {
      this.signingOptions = signingOptions;
   }

   public static SignHelperBuilder builder() {
      return new SignHelperBuilder();
   }

   public static class SignHelperBuilder {
      private SigningOptions signingOptions;

      SignHelperBuilder() {
      }

      public SignHelperBuilder signingOptions(SigningOptions signingOptions) {
         this.signingOptions = signingOptions;
         return this;
      }

      public SignHelper build() {
         return new SignHelper(this.signingOptions);
      }

      public String toString() {
         return "SignHelper.SignHelperBuilder(signingOptions=" + this.signingOptions + ")";
      }
   }

   public static class DefaultSigningOptions implements SigningOptions {
      public DefaultSigningOptions() {
      }

      public Map<String, Object> create(String detailId) {
         Map<String, Object> options = new HashMap();
         List<String> tranforms = new ArrayList();
         tranforms.add("http://www.w3.org/2000/09/xmldsig#base64");
         tranforms.add("http://www.w3.org/2001/10/xml-exc-c14n#");
         options.put("transformerList", tranforms);
         options.put("baseURI", detailId);
         return options;
      }
   }

   public static class EncapsulatedSigningOptions implements SigningOptions {
      public EncapsulatedSigningOptions() {
      }

      public Map<String, Object> create(String detailId) {
         Map<String, Object> options = (new DefaultSigningOptions()).create(detailId);
         options.put("encapsulate", true);
         options.put("encapsulate-transformer", (signature) -> {
            Element result = signature.getOwnerDocument().createElementNS("urn:be:cin:encrypted", "Xades");
            result.setTextContent(Base64.encodeBase64String(ConnectorXmlUtils.toByteArray(signature)));
            return result;
         });
         return options;
      }
   }

   interface SigningOptions {
      Map<String, Object> create(String var1);
   }
}
