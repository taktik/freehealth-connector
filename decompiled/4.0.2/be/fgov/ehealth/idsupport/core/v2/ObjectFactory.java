package be.fgov.ehealth.idsupport.core.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _LegalContext_QNAME = new QName("urn:be:fgov:ehealth:idsupport:core:v2", "LegalContext");

   public ObjectFactory() {
   }

   public IdentificationData createIdentificationData() {
      return new IdentificationData();
   }

   public ProviderInfo createProviderInfo() {
      return new ProviderInfo();
   }

   public ProviderInfoDetailType createProviderInfoDetailType() {
      return new ProviderInfoDetailType();
   }

   public ValidationResult createValidationResult() {
      return new ValidationResult();
   }

   public ValidationInfoType createValidationInfoType() {
      return new ValidationInfoType();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:idsupport:core:v2",
      name = "LegalContext"
   )
   public JAXBElement<String> createLegalContext(String value) {
      return new JAXBElement(_LegalContext_QNAME, String.class, (Class)null, value);
   }
}
