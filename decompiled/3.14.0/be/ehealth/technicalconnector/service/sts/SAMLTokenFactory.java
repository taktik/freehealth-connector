package be.ehealth.technicalconnector.service.sts;

import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.security.impl.SAMLHolderOfKeyToken;
import be.ehealth.technicalconnector.service.sts.security.impl.SAMLSenderVouchesCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class SAMLTokenFactory {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLTokenFactory.class);
   private static final String XMLNS_SAML_1_0_ASS = "urn:oasis:names:tc:SAML:1.0:assertion";

   private SAMLTokenFactory() {
   }

   public static SAMLTokenFactory getInstance() {
      return SAMLTokenFactory.SAMLTokenFactorySingleton.INSTANCE.getSAMLTokenFactory();
   }

   public SAMLToken createSamlToken(Element assertion, Credential credential) {
      NodeList authenticationStatements = assertion.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "AuthenticationStatement");

      for(int i = 0; i < authenticationStatements.getLength(); ++i) {
         Element authenticationStatement = (Element)authenticationStatements.item(i);
         NodeList confirmationMethodsNodeList = authenticationStatement.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "ConfirmationMethod");

         for(int j = 0; j < confirmationMethodsNodeList.getLength(); ++j) {
            Element confirmationMethodEl = (Element)confirmationMethodsNodeList.item(j);
            String confirmationMethod = confirmationMethodEl.getTextContent();
            LOG.debug("ConfirmationMethod " + confirmationMethod + " found.");
            if ("urn:oasis:names:tc:SAML:1.0:cm:holder-of-key".equals(confirmationMethod)) {
               return new SAMLHolderOfKeyToken(assertion, credential);
            }

            if ("urn:oasis:names:tc:SAML:1.0:cm:sender-vouches".equals(confirmationMethod)) {
               return new SAMLSenderVouchesCredential(assertion, credential);
            }

            LOG.debug("Unsupported configurtionMethod [" + confirmationMethod + "]");
         }
      }

      LOG.debug("Unable to determine confirmationMethod.");
      return new SAMLHolderOfKeyToken(assertion, credential);
   }

   // $FF: synthetic method
   SAMLTokenFactory(SAMLTokenFactory.SyntheticClass_1 x0) {
      this();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static enum SAMLTokenFactorySingleton {
      INSTANCE;

      private SAMLTokenFactory instance = new SAMLTokenFactory();

      public SAMLTokenFactory getSAMLTokenFactory() {
         return this.instance;
      }
   }
}
